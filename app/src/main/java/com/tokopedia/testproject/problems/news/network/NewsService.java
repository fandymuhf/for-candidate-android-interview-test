package com.tokopedia.testproject.problems.news.network;

import com.tokopedia.testproject.problems.news.model.NewsResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    @GET("everything")
    Observable<NewsResult> getEverything(@Query("q") String query, @Query("PageSize") int query2, @Query("SortBy") String query3);

    @GET("top-headlines")
    Observable<NewsResult> getTopHeadlines(@Query("country") String query);
}
