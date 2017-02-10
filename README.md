##RecyclerView用法
1、添加依赖

在AS的build.gradle中添加依赖，然后同步一下就可以引入依赖包：
```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:25.1.0'
    compile 'com.android.support:recyclerview-v7:25.1.0'
    testCompile 'junit:junit:4.12'
}
```
2、在xml布局文件中创建一个RecyclerView的布局
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.john.rvspacing.MainActivity">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/grid_rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</RelativeLayout>
```
##RecyclerView自定义行列间距
1、设置宫格布局、瀑布流布局间距
```java
/**
 * 设置RecyclerView GridLayoutManager or StaggeredGridLayoutManager spacing
 * Created by john on 17-1-5.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}
```
2、正确使用姿势
```java
GridLayoutManager layoutManager = new GridLayoutManager(this, COLUMN, LinearLayoutManager.VERTICAL, false);
mGridRv.setLayoutManager(layoutManager);
mGridRv.addItemDecoration(new GridSpacingItemDecoration(COLUMN, getResources().getDimensionPixelSize(R.dimen.padding_middle), true));
mGridRv.setHasFixedSize(true);
mRvGridAdapter = new RvGridAdapter(this);
mRvGridAdapter.setItemList(DataMock.mockItemBean());
mGridRv.setAdapter(mRvGridAdapter);
```
3、设置线性布局间距
```java
/**
 * 设置RecyclerView LinearLayoutManager spacing
 * Created by john on 17-1-5.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }
}
```
4、Demo效果

![这里写图片描述](http://img.blog.csdn.net/20170209191342155?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvam9obldjaGV1bmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

5、[点击下载示例源码](https://github.com/coderJohnZhang/RVSpacing)
