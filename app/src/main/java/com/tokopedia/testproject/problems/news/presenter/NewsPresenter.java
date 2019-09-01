package com.tokopedia.testproject.problems.news.presenter;

import com.tokopedia.testproject.problems.news.model.Article;
import com.tokopedia.testproject.problems.news.model.NewsResult;
import com.tokopedia.testproject.problems.news.network.NewsDataSource;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hendry on 27/01/19.
 */
public class NewsPresenter {

    private CompositeDisposable composite = new CompositeDisposable();

    private View view;

    public interface View {
        void onSuccessGetNews(List<Article> articleList);

        void onSuccessGetSlider(List<Article> articleList);

        void onErrorGetNews(Throwable throwable);

        void onErrorGetSlider(Throwable throwable);

        void onCompleteGetNews();

        void onCompleteGetSlider();
    }

    public NewsPresenter(NewsPresenter.View view) {
        this.view = view;
    }

    public void getEverything(String keyword, int pagesize, String sortby) {
        NewsDataSource.getService().getEverything(keyword,pagesize,sortby)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        composite.add(d);
                    }

                    @Override
                    public void onNext(NewsResult newsResult) {
                        view.onSuccessGetNews(newsResult.getArticles());
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorGetNews(e);
                    }

                    @Override
                    public void onComplete() {
                        view.onCompleteGetNews();
                    }
                });
    }

    public void getTopHeadlines(String country) {
        NewsDataSource.getService().getTopHeadlines(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        composite.add(d);
                    }

                    @Override
                    public void onNext(NewsResult newsResult) {
                        view.onSuccessGetSlider(newsResult.getArticles());
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorGetSlider(e);
                    }

                    @Override
                    public void onComplete() {
                        view.onCompleteGetSlider();
                    }
                });
    }

    public void unsubscribe() {
        composite.dispose();
    }
}
