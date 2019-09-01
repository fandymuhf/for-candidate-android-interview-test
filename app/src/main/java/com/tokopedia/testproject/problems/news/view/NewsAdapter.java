package com.tokopedia.testproject.problems.news.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tokopedia.testproject.R;
import com.tokopedia.testproject.problems.news.model.Article;
import com.tokopedia.testproject.problems.news.presenter.NewsPresenter;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CustomViewHolder> {
    private static final int VIEW_TYPE_LOADING = 1;
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_RETRY = 2;
    private List<Article> articleList;
    private int intretry = 0;
    private String tempdate="";
    private NewsPresenter newsPresenterRetry;
    private String mykeyword;
    private String mysortby;

    NewsAdapter(List<Article> articleList) {
        setArticleList(articleList);
    }

    void setArticleList(List<Article> articleList) {
        if (articleList == null) {
            this.articleList = new ArrayList<>();
        } else {
            this.articleList = articleList;
        }

    }

    public List<Article> getArticleList() {
        return articleList;
    }

    @Override
    public int getItemViewType(int position) {
        if (intretry == 1)
            return VIEW_TYPE_RETRY;
        else
        if (articleList.get(position) != null)
            return VIEW_TYPE_ITEM;
        else
            return VIEW_TYPE_LOADING;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = null;
        if (viewType == VIEW_TYPE_ITEM) {
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            return new NewsViewHolder(root);
        } else if(viewType == VIEW_TYPE_LOADING) {
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.progressbar, parent, false);
            return new ProgressViewHolder(root);
        }  else { //Error state
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.progressbar, parent, false);
            return new RetryHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            //Get Article

            ((NewsViewHolder) holder).bind(articleList.get(position));

            //System.out.println("test "+position);
        }else if(holder instanceof  ProgressViewHolder){
            //Loading progress
            ((ProgressViewHolder) holder).itemView.findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
            ((ProgressViewHolder) holder).itemView.findViewById(R.id.textViewRetry).setVisibility(View.GONE);
            ((ProgressViewHolder) holder).itemView.findViewById(R.id.buttonretry).setVisibility(View.GONE);

            //System.out.println("test2 "+position);
        } else if(holder instanceof RetryHolder) {
            //Error state and retry connection
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);

            ((RetryHolder) holder).itemView.findViewById(R.id.cardViewLoading).setLayoutParams(params);
            ((RetryHolder) holder).itemView.findViewById(R.id.progressbar).setVisibility(View.GONE);
            ((RetryHolder) holder).itemView.findViewById(R.id.textViewRetry).setVisibility(View.VISIBLE);
            ((RetryHolder) holder).itemView.findViewById(R.id.buttonretry).setVisibility(View.VISIBLE);

            ((RetryHolder) holder).itemView.findViewById(R.id.buttonretry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intretry=0;
                    ((RetryHolder) holder).itemView.findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
                    ((RetryHolder) holder).itemView.findViewById(R.id.textViewRetry).setVisibility(View.GONE);
                    ((RetryHolder) holder).itemView.findViewById(R.id.buttonretry).setVisibility(View.GONE);

                    newsPresenterRetry.getEverything(mykeyword,20, mysortby);
                    newsPresenterRetry.getTopHeadlines("id");

                }
            });
            //System.out.println("test3 "+position);
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    class ProgressViewHolder extends CustomViewHolder {

        ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }

    class RetryHolder extends CustomViewHolder {

        RetryHolder(View itemView) {
            super(itemView);
        }
    }

    class NewsViewHolder extends CustomViewHolder {

        ImageView imageView;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvDate;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);

        }

        void bind(Article article) {
                Glide.with(itemView).load(article.getUrlToImage()).into(imageView);
                tvTitle.setText(article.getTitle());
                tvDescription.setText(article.getDescription());

                String formatStr="yyyy-MM-dd";

                SimpleDateFormat sd = new SimpleDateFormat(formatStr, Locale.getDefault());
                String str = article.getPublishedAt().substring(0,10);

                String[] strDate = str.split(" .[a-zA-Z]");

                ParsePosition pp1 = new ParsePosition(0);

                Date retDate = sd.parse(strDate[0], pp1);

                String pattern = "dd MMMM yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern,Locale.ROOT);
                //System.out.println(retDate);
                String date = simpleDateFormat.format(retDate);

                if(!tempdate.equals(date)){
                    tvDate.setText(date);
                    tempdate=date;
                }else {
                    tvDate.setText("");
                }

        }

    }

    public void addNullData() {
        articleList.add(null);
        notifyItemInserted(articleList.size() - 1);
    }

    public void addNullFirstData() {
        articleList.add(0,null);
        notifyItemInserted(0);
    }

    public void addRetryConnection(NewsPresenter newsPresenter, String keyword, String sortby) {

        articleList.clear();
        notifyDataSetChanged();

        intretry=1;
        articleList.add(null);
        notifyItemInserted(articleList.size() - 1);

        notifyDataSetChanged();
        //System.out.println("mysize: "+(articleList.size()-1));

        newsPresenterRetry = newsPresenter;
        mykeyword = keyword;
        mysortby = sortby;

    }

    public void removeNull() {
        articleList.remove(articleList.size() - 1);
        notifyItemRemoved(articleList.size());
    }

    public void addData(List<Article> integersList) {
        articleList.addAll(integersList);
        notifyDataSetChanged();
    }
}
