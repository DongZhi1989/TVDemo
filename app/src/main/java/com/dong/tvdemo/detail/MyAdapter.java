package com.dong.tvdemo.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.tvdemo.R;
import com.dong.tvdemo.model.DetailBean;
import com.dong.tvdemo.utils.ImageLoader;

import java.util.List;

/**
 * Author      : <Dr_dong>
 * Date        : 2018/9/11
 * Description :
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final String TAG = MyAdapter.class.getSimpleName();
    // 数据集
    private List<DetailBean.ItemsBean> mDataset;
    private Context mContext;
    private int id;
    private View.OnFocusChangeListener mOnFocusChangeListener;
    private OnBindListener onBindListener;

    public MyAdapter(Context context, List<DetailBean.ItemsBean> dataset) {
        super();
        mContext = context;
        mDataset = dataset;
    }

    public MyAdapter(Context context, List<DetailBean.ItemsBean> dataset, int id) {
        super();
        mContext = context;
        mDataset = dataset;
        this.id = id;
        Log.d(TAG, "mDataset " + mDataset.size());
    }

    public MyAdapter(Context context, List<DetailBean.ItemsBean> dataset, int id, View.OnFocusChangeListener onFocusChangeListener) {
        super();
        mContext = context;
        mDataset = dataset;
        this.id = id;
        this.mOnFocusChangeListener = onFocusChangeListener;
    }

    public void setOnBindListener(OnBindListener onBindListener) {
        this.onBindListener = onBindListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int resId = R.layout.detail_list_item;
        if (this.id > 0) {
            resId = this.id;
        }
        View view = LayoutInflater.from(mContext).inflate(resId, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (mDataset.size() == 0) {
            Log.d(TAG, "mDataset has no data!");
            return;
        }
        viewHolder.mTextDesc.setText(mDataset.get(i).getInfotext());
        viewHolder.mTextTitle.setText(mDataset.get(i).getTitle());
        ImageLoader.display(mDataset.get(i).getPoster(), viewHolder.mPostImg);
        viewHolder.itemView.setTag(i);
        viewHolder.itemView.setOnFocusChangeListener(mOnFocusChangeListener);
        if (onBindListener != null) {
            onBindListener.onBind(viewHolder.itemView, i);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setData(List<DetailBean.ItemsBean> data) {
        this.mDataset = data;
    }

    public interface OnBindListener {
        void onBind(View view, int i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextDesc;
        public ImageView mPostImg;
        public TextView mTextTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextDesc = (TextView) itemView.findViewById(R.id.tv_desc);
            mPostImg = (ImageView) itemView.findViewById(R.id.iv_image);
            mTextTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

}
