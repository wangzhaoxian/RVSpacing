package com.john.rvspacing;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * RecyclerView适配器
 * Created by john on 17-2-9.
 */
public class RvGridAdapter extends RecyclerView.Adapter<RvGridAdapter.ItemHolder> {

    private final LayoutInflater mLayoutInflater;
    private List<ItemBean> mItemBeanList = new ArrayList<>();
    private final Context mContext;

    public RvGridAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setItemList(ItemBean[] itemBeen) {
        mItemBeanList = Arrays.asList(itemBeen);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.rv_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        Resources res = mContext.getResources();
        Object itemObject = getItem(position);
        if (itemObject != null) {
            final ItemBean item = (ItemBean) itemObject;
            holder.itemNameTv.setText(item.itemName);
        }
    }

    @Override
    public int getItemCount() {
        return mItemBeanList.size();
    }

    private Object getItem(int position) {
        return mItemBeanList.get(position);
    }



    class ItemHolder extends RecyclerView.ViewHolder {

        TextView itemNameTv;

        ItemHolder(View itemView) {
            super(itemView);

            itemNameTv = (TextView) itemView.findViewById(R.id.item_name_tv);
        }
    }

}
