package com.john.rvspacing;

/**
 * 实体Bean
 * Created by john on 17-2-9.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ItemBean implements Parcelable {
    public long itemId = 0L;
    public String itemName = "";
    public static final Creator<ItemBean> CREATOR = new Creator() {
        public ItemBean createFromParcel(Parcel in) {
            return new ItemBean(in);
        }

        public ItemBean[] newArray(int size) {
            return new ItemBean[size];
        }
    };

    public ItemBean() {
    }

    protected ItemBean(Parcel in) {
        this.itemId = in.readLong();
        this.itemName = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.itemId);
        parcel.writeString(this.itemName);
    }
}
