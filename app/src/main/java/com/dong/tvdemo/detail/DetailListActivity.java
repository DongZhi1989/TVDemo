package com.dong.tvdemo.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dong.tvdemo.R;
import com.dong.tvdemo.model.DetailBean;
import com.dong.tvdemo.net.OkGoJsonCallback;
import com.dong.tvdemo.utils.LogUtils;
import com.dong.tvdemo.widget.AutoLayoutManager;
import com.dong.tvdemo.widget.MetroViewBorderImpl;
import com.lzy.okgo.OkGo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Author      : <Dr_dong>
 * Date        : 2018/9/11
 * Description :
 */
public class DetailListActivity extends Activity {

    private static final String TAG = DetailListActivity.class.getSimpleName();
    private static final String URL = "http://gvod.video.51togic" +
            ".com/api/v1/programs?mobile=false&version_code=102&device_id=419000000000000000005cc6d0b7e7e80000000000000000&city=%E5%8C%97%E4%BA%AC&vip=0" +
            "&show_top_recommend=0";
    private final List<DetailBean.ItemsBean> mDetailInfoList = new CopyOnWriteArrayList<>();
    private MetroViewBorderImpl mMetroViewBorderImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaillist);
        mMetroViewBorderImpl = new MetroViewBorderImpl(this);
        mMetroViewBorderImpl.setBackgroundResource(R.drawable.border_color);
        loadRecyclerViewMenuItem();

        OkGo.<DetailBean>get(URL)
                .execute(new OkGoJsonCallback(DetailBean.class) {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response response) {
                        LogUtils.i("===onSuccess===" + response.body().toString());
                        mDetailInfoList.addAll(((DetailBean) response.body()).getItems());
                        loadDataForRecyclerViewGridLayout();
                    }

                    @Override
                    public void netError(com.lzy.okgo.model.Response response) {
                        LogUtils.i("===netError===");
                    }

                    @Override
                    public void serversError(com.lzy.okgo.model.Response response) {
                        LogUtils.i("===serversError===");
                    }
                });
    }

    private void loadRecyclerViewMenuItem() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ry_menu_item);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);
        mMetroViewBorderImpl.attachTo(recyclerView);
        OptionItemAdapter adapter = new OptionItemAdapter(this, R.layout.detail_menu_item);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }

    private void loadDataForRecyclerViewGridLayout() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ry_detail_list);
        GridLayoutManager gridlayoutManager = new AutoLayoutManager(this, 4);
        gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridlayoutManager);
        recyclerView.setFocusable(false);
        mMetroViewBorderImpl.attachTo(recyclerView);
        MyAdapter adapter = new MyAdapter(this, mDetailInfoList, R.layout.detail_list_item);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }

}
