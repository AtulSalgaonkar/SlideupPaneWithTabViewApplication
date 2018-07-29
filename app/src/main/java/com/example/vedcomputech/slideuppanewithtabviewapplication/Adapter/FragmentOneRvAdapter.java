package com.example.vedcomputech.slideuppanewithtabviewapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.vedcomputech.slideuppanewithtabviewapplication.R;
import com.example.vedcomputech.slideuppanewithtabviewapplication.Retrofit.Pojo.DatePojoOne;

import java.util.List;

public class FragmentOneRvAdapter extends RecyclerView.Adapter<FragmentOneRvAdapter.ViewHolder> {

    private List<DatePojoOne> mData;
    private Context mContext;
    private OnLocationClickOne mOnLocationClick;

    public FragmentOneRvAdapter(List<DatePojoOne> mData, Context mContext, FragmentActivity activity) {
        this.mData = mData;
        this.mContext = mContext;
        mOnLocationClick = (OnLocationClickOne) activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.data_one_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DatePojoOne model = mData.get(position);
        holder.mOneAcTv.setText(model.getName());
        holder.mTwoAcTv.setText(model.getEmail());
        holder.mRootView.setOnClickListener(v -> {
            mOnLocationClick.onLocationClickListener(model.getName());
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView mOneAcTv, mTwoAcTv;
        private RelativeLayout mRootView;

        ViewHolder(View itemView) {
            super(itemView);
            mOneAcTv = itemView.findViewById(R.id.textview_one_one);
            mTwoAcTv = itemView.findViewById(R.id.textview_two_one);
            mRootView = itemView.findViewById(R.id.rel_lay_one_one);
        }
    }

    public interface OnLocationClickOne {
        void onLocationClickListener(String name);
    }
}
