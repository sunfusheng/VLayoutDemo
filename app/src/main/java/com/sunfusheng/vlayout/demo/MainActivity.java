package com.sunfusheng.vlayout.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.VirtualLayoutManager.LayoutParams;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.sunfusheng.vlayout.demo.util.DensityUtil;
import com.sunfusheng.vlayout.demo.util.ModelUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private VirtualLayoutManager layoutManager;
    private List<DelegateAdapter.Adapter> adapters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new VirtualLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        recyclerView.setAdapter(delegateAdapter);

        addLinearLayoutData(ModelUtil.getSceneryImages());
        addStickyLayoutData("StickyLayout");
        addGridLayoutData(ModelUtil.getAnimalImages());
        addStaggeredGridLayoutData(ModelUtil.getSceneryImages());

        delegateAdapter.setAdapters(adapters);
    }

    private void addLinearLayoutData(List<String> list) {
        LinearLayoutHelper helper = new LinearLayoutHelper();
        helper.setDividerHeight(dip(10));
        helper.setMargin(dip(10), dip(10), dip(10), dip(10));
        ImageAdapter adapter = new ImageAdapter(this, helper, list) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, dip(120));
                holder.itemView.setLayoutParams(layoutParams);
            }
        };
        adapters.add(adapter);
    }

    private void addGridLayoutData(List<String> list) {
        GridLayoutHelper helper = new GridLayoutHelper(2);
        helper.setGap(dip(5));
        helper.setMargin(dip(10), dip(10), dip(10), dip(10));
        ImageAdapter adapter = new ImageAdapter(this, helper, list) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                layoutParams.mAspectRatio = 1.0f;
                holder.itemView.setLayoutParams(layoutParams);
            }
        };
        adapters.add(adapter);
    }

    private void addStaggeredGridLayoutData(List<String> list) {
        StaggeredGridLayoutHelper helper = new StaggeredGridLayoutHelper(2);
        helper.setGap(dip(5));
        helper.setMargin(dip(10), dip(10), dip(10), dip(10));
        ImageAdapter adapter = new ImageAdapter(this, helper, list) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                layoutParams.mAspectRatio = (position % 2 == 0) ? 1.0f : 2.0f;
                holder.itemView.setLayoutParams(layoutParams);
            }
        };
        adapters.add(adapter);
    }

    private void addStickyLayoutData(String text) {
        StickyLayoutHelper helper = new StickyLayoutHelper();
//        helper.setOffset(0);
        TextAdapter adapter = new TextAdapter(this, helper, text);
        adapters.add(adapter);
    }

    // 获取DP的值
    private int dip(int px) {
        return DensityUtil.dip2px(getApplicationContext(), px);
    }

}
