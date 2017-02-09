package com.john.rvspacing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final int COLUMN = 4;

    private RecyclerView mGridRv;
    private RvGridAdapter mRvGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridRv = (RecyclerView) findViewById(R.id.grid_rv);

        initGridRv();
    }

    private void initGridRv() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, COLUMN, LinearLayoutManager.VERTICAL, false);
        mGridRv.setLayoutManager(layoutManager);
        mGridRv.addItemDecoration(new GridSpacingItemDecoration(COLUMN, getResources().getDimensionPixelSize(R.dimen.padding_middle), true));
        mGridRv.setHasFixedSize(true);
        mRvGridAdapter = new RvGridAdapter(this);
        mRvGridAdapter.setItemList(DataMock.mockItemBean());
        mGridRv.setAdapter(mRvGridAdapter);
    }
}
