package com.john.rvspacing;

/**
 * 模拟数据
 * Created by john on 17-2-9.
 */

public class DataMock {

    private DataMock() {
    }

    public static ItemBean[] mockItemBean() {
        ItemBean[]  item = new ItemBean[50];

        for(int i=0; i<item.length; i++){
            item[i] = new ItemBean();
            item[i].itemId = i;
            item[i].itemName = "A" + i;
        }

        return item;
    }
}
