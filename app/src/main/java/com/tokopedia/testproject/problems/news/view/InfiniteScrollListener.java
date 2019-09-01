package com.tokopedia.testproject.problems.news.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;

public class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private final static int VISIBLE_THRESHOLD = 2;
    private LinearLayoutManager linearLayoutManager;
    private boolean loading; // LOAD MORE Progress dialog
    private OnLoadMoreListener listener;
    private boolean pauseListening = false;


    private boolean END_OF_FEED_ADDED = false;
    private int NUM_LOAD_ITEMS = 10;

    public InfiniteScrollListener(LinearLayoutManager linearLayoutManager, OnLoadMoreListener listener) {
        this.linearLayoutManager = linearLayoutManager;
        this.listener = listener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dx == 0 && dy == 0)
            return;
        int totalItemCount = linearLayoutManager.getItemCount();
        int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
        if (!loading && totalItemCount <= lastVisibleItem + VISIBLE_THRESHOLD && totalItemCount != 0 && !END_OF_FEED_ADDED && !pauseListening) {
            if (listener != null) {
                listener.onLoadMore();
            }
            loading = true;
        }
    }

    public void setLoaded() {
        loading = false;
    }

    public interface OnLoadMoreListener {
        void onSliderClick(BaseSliderView slider);

        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);

        void onLoadMore();
    }

    public void addEndOfRequests() {
        this.END_OF_FEED_ADDED = true;
    }

    public void pauseScrollListener(boolean pauseListening) {
        this.pauseListening = pauseListening;
    }
}
