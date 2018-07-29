package com.example.vedcomputech.slideuppanewithtabviewapplication.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.vedcomputech.slideuppanewithtabviewapplication.Adapter.FragmentOneRvAdapter;
import com.example.vedcomputech.slideuppanewithtabviewapplication.HelperClass.HelperMethods;
import com.example.vedcomputech.slideuppanewithtabviewapplication.R;
import com.example.vedcomputech.slideuppanewithtabviewapplication.Retrofit.Api;
import com.example.vedcomputech.slideuppanewithtabviewapplication.Retrofit.ApiInterface;
import com.example.vedcomputech.slideuppanewithtabviewapplication.Retrofit.Pojo.DatePojoOne;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentOne extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private Context mContext;
    private FragmentOneRvAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RelativeLayout mRootView;
    private AppCompatTextView mDataNullAcTv;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<DatePojoOne> mData = new ArrayList<>();
    private ApiInterface mApiInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        return inflater.inflate(R.layout.fragment_one_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initView(view);
        if (mData != null)
            setData();

    }

    private void initView(View view) {
        mDataNullAcTv = view.findViewById(R.id.data_null_ac_tv);
        mRecyclerView = view.findViewById(R.id.f_one_recycler_view);
        mRootView = view.findViewById(R.id.f_one_root_view);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_ref_lay_one);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter = new FragmentOneRvAdapter(mData, mContext, getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {
        if (mData != null)
            setData();
        else
            stopRefreshOfSwipe();
    }

    private void setData() {
        clearData();
        startRefreshOfSwipe();
        Observable<List<DatePojoOne>> observable = getObservable();
        Observer<List<DatePojoOne>> observer = getObserver();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private Observable<List<DatePojoOne>> getObservable() {
        mApiInterface = Api.getRetrofitOfFileUpload().create(ApiInterface.class);
        return mApiInterface.getRandomDataOne(1);
    }


    public Observer<List<DatePojoOne>> getObserver() {
        return new Observer<List<DatePojoOne>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<DatePojoOne> arr) {
                if (arr != null) {
                    int size = arr.size();
                    if (size > 0) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mData.addAll(arr);
                        mDataNullAcTv.setVisibility(View.GONE);
                        if (mAdapter != null) {
                            mAdapter.notifyDataSetChanged();
                        }
                    } else {
                        mDataNullAcTv.setVisibility(View.VISIBLE);
                        mDataNullAcTv.setText("No Data Available!");
                        mRecyclerView.setVisibility(View.GONE);
                    }
                } else {
                    mDataNullAcTv.setVisibility(View.VISIBLE);
                    mDataNullAcTv.setText("Internal server error!");
                    mRecyclerView.setVisibility(View.GONE);
                }
                stopRefreshOfSwipe();
            }

            @Override
            public void onError(Throwable e) {
                HelperMethods.showLogError("Error: " + e.getMessage());
                mDataNullAcTv.setVisibility(View.VISIBLE);
                mDataNullAcTv.setText("Internal server error!");
                mRecyclerView.setVisibility(View.GONE);
                stopRefreshOfSwipe();
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void clearData() {
        if (mData != null && mAdapter != null) {
            mData.clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    private void stopRefreshOfSwipe() {
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
    }

    private void startRefreshOfSwipe() {
        if (!mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(true);
    }
}
