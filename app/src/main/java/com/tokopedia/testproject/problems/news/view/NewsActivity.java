package com.tokopedia.testproject.problems.news.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.tokopedia.testproject.R;
import com.tokopedia.testproject.problems.news.model.Article;
import com.tokopedia.testproject.problems.news.model.DatabaseHelper;
import com.tokopedia.testproject.problems.news.model.MyArticle;
import com.tokopedia.testproject.problems.news.presenter.NewsPresenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,InfiniteScrollListener.OnLoadMoreListener,com.tokopedia.testproject.problems.news.presenter.NewsPresenter.View {

    private NewsPresenter newsPresenter;
    private NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    String mykeyword;
    String mycountry;
    InfiniteScrollListener infiniteScrollListener;
    int jumlahPage = 20;
    String sortby = "publishedAt";
    int waitstate=0;

    EditText inputEditText;

    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;

    private DatabaseHelper db;
    private List<MyArticle> myArticleList= new ArrayList<>();
    private List<MyArticle> myArticleSliderList= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        newsPresenter = new NewsPresenter(this);
        db = new DatabaseHelper(this);
        //db.recreateDB();

        //db.deleteAllArticle();


        //System.out.println(myArticleList.get(0).getTitle());
        //System.out.println(myArticleList.get(0).getDescription());
        //System.out.println(myArticleList.get(0).getUrltoimage());
        //System.out.println(myArticleList.get(0).getPublishedat());

        initNewsAdapter();

        mykeyword = "tokopedia";
        mycountry = "id";

        myArticleList.addAll(db.getArticle(mykeyword));
        myArticleSliderList.addAll(db.getArticleSlider(mycountry));

        newsPresenter.getEverything(mykeyword,jumlahPage,sortby);
        newsPresenter.getTopHeadlines(mycountry);

        inputEditText = findViewById(R.id.textInputSearch);
        Button kliksearch = findViewById(R.id.buttonsearch);

        inputEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    if(waitstate==0)gosearch();
                    inputEditText.setSelection(inputEditText.getText().length());
                    return true;
                }
                return false;
            }
        });

        inputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(waitstate==0)gosearch();
                    inputEditText.setSelection(inputEditText.getText().length());
                    return true;
                }
                return false;
            }
        });

        kliksearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(waitstate==0)gosearch();
                inputEditText.setSelection(inputEditText.getText().length());
            }
        });


    }

    @Override
    protected void onStop() {

        if(sliderLayout!=null)
        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    public void gosearch(){
        waitstate=1;
        mykeyword=inputEditText.getText().toString();
        myArticleList.clear();
        myArticleList.addAll(db.getArticle(mykeyword));

        if(inputEditText.getText().toString().equals("")){
            Toast.makeText(NewsActivity.this, "Search cannot empty", Toast.LENGTH_LONG).show();
            waitstate=0;
        }else{
            initNewsAdapter();
            newsPresenter.getEverything(mykeyword,jumlahPage,sortby);
        }
    }

    public void initNewsAdapter(){
        newsAdapter = new NewsAdapter(null);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        infiniteScrollListener = new InfiniteScrollListener(manager, this);
        infiniteScrollListener.setLoaded();

        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(infiniteScrollListener);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        recyclerView.setAdapter(newsAdapter);

        for(int i=0;i<20;i++)
            newsAdapter.addNullData();
    }

    @Override
    public void onSuccessGetNews(List<Article> articleList) {
        if(newsAdapter!=null) {
            newsAdapter.removeNull();
        }
            newsAdapter.setArticleList(articleList);
            newsAdapter.notifyDataSetChanged();
            db.deleteArticle(mykeyword);
            for(int i=0;i<articleList.size();i++)
            db.insertAllNews(
                    articleList.get(i).getTitle(),
                    articleList.get(i).getDescription(),
                    articleList.get(i).getUrlToImage(),
                    articleList.get(i).getPublishedAt(),
                    mykeyword
            );

    }

    @Override
    public void onSuccessGetSlider(List<Article> articleList) {
        Hash_file_maps = new HashMap<String, String>();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        if(articleList.size()>=5)
        for (int i=0;i<5;i++)
        Hash_file_maps.put(articleList.get(i).getTitle(), articleList.get(i).getUrlToImage());

        for(String name : Hash_file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(5000);
        sliderLayout.addOnPageChangeListener(this);

        db.deleteArticleSlider(mycountry);
        for(int i=0;i<articleList.size();i++)
            db.insertAllSlider(
                    articleList.get(i).getTitle(),
                    articleList.get(i).getDescription(),
                    articleList.get(i).getUrlToImage(),
                    articleList.get(i).getPublishedAt(),
                    mycountry
            );
    }

    @Override
    public void onCompleteGetNews(){
        if(newsAdapter.getItemCount()==0){
            Toast.makeText(this, "No result", Toast.LENGTH_LONG).show();
        }else
        Toast.makeText(this, "Sukses Loading", Toast.LENGTH_LONG).show();
        waitstate=0;
    }

    @Override
    public void onCompleteGetSlider(){
        //Toast.makeText(this, "Sukses Loading", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorGetNews(Throwable throwable) {

        //Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();


        if(db.getArticleCountKeyword(mykeyword)>0){
            if(newsAdapter!=null) {
                newsAdapter.removeNull();
            }
            List<Article> tesarticleArr = new ArrayList<>();
            Article tesarticle = new Article();

            for(int i=0;i<myArticleList.size();i++){
                tesarticle = tesarticle.copy(null,null
                        ,myArticleList.get(i).getTitle(),
                        myArticleList.get(i).getDescription(),
                        null,
                        myArticleList.get(i).getUrltoimage(),
                        myArticleList.get(i).getPublishedat(),
                        null);
                tesarticleArr.add(tesarticle);
            }

            newsAdapter.setArticleList(tesarticleArr);
            newsAdapter.notifyDataSetChanged();

            Toast.makeText(this, "You're in offline mode", Toast.LENGTH_LONG).show();
        }else
        newsAdapter.addRetryConnection(newsPresenter,mykeyword,sortby);
        waitstate=0;
    }

    @Override
    public void onErrorGetSlider(Throwable throwable) {

        //newsAdapter.addRetryConnection(newsPresenter,mykeyword,sortby);
        if(db.getArticleSliderCountKeyword(mycountry)>0){
            Hash_file_maps = new HashMap<String, String>();

            sliderLayout = (SliderLayout)findViewById(R.id.slider);

            List<Article> tesarticleArr = new ArrayList<>();
            Article tesarticle = new Article();

            for(int i=0;i<myArticleSliderList.size();i++){
                tesarticle = tesarticle.copy(null,null
                        ,myArticleSliderList.get(i).getTitle(),
                        myArticleSliderList.get(i).getDescription(),
                        null,
                        myArticleSliderList.get(i).getUrltoimage(),
                        myArticleSliderList.get(i).getPublishedat(),
                        null);
                tesarticleArr.add(tesarticle);
            }

            for (int i=0;i<5;i++)
                Hash_file_maps.put(myArticleSliderList.get(i).getTitle(), myArticleSliderList.get(i).getUrltoimage());

            for(String name : Hash_file_maps.keySet()){

                TextSliderView textSliderView = new TextSliderView(this);
                textSliderView
                        .description(name)
                        .image(Hash_file_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra",name);
                sliderLayout.addSlider(textSliderView);
            }
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setDuration(5000);
            sliderLayout.addOnPageChangeListener(this);
        }else Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();

        waitstate=0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsPresenter.unsubscribe();
    }

    @Override
    public void onLoadMore() {
        if(db.getArticleCountKeyword(mykeyword)>0) {
            newsAdapter.addNullData();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    newsAdapter.removeNull();
                    jumlahPage += 10;

                    newsPresenter.getEverything(mykeyword, jumlahPage, sortby);
                    infiniteScrollListener.setLoaded();
                }
            }, 2000);
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage,int numberid){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"image"+numberid+".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}
