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
import com.example.vedcomputech.slideuppanewithtabviewapplication.Retrofit.Pojo.DatePojoTwo;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

public class FragmentTwoRvAdapter extends RecyclerView.Adapter<FragmentTwoRvAdapter.ViewHolder> {

    private List<DatePojoTwo> mData;
    private Context mContext;
    private OnLocationClickTwo mOnLocationClickTwo;

    public FragmentTwoRvAdapter(List<DatePojoTwo> mData, Context mContext, FragmentActivity activity) {
        this.mData = mData;
        this.mContext = mContext;
        mOnLocationClickTwo = (OnLocationClickTwo) activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.data_two_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DatePojoTwo model = mData.get(position);
        holder.mAcTv.setText(model.getTitle());
        ViewGroup.LayoutParams lp = holder.mAcTv.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams)
                    holder.mAcTv.getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
        }
        holder.mRootView.setOnClickListener(v -> {
            mOnLocationClickTwo.onLocationClickListenerTwo(model.getTitle());
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mRootView;
        private AppCompatTextView mAcTv;

        ViewHolder(View itemView) {
            super(itemView);
            mRootView = itemView.findViewById(R.id.rel_lay_one_two);
            mAcTv = itemView.findViewById(R.id.textview_one_two);
        }
    }

    public interface OnLocationClickTwo {
        void onLocationClickListenerTwo(String name);
    }
}
