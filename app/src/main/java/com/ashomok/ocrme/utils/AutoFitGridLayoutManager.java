package com.ashomok.ocrme.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import static com.ashomok.ocrme.utils.LogUtil.DEV_TAG;

/**
 * Created by iuliia on 12/28/17.
 */

public class AutoFitGridLayoutManager extends GridLayoutManager {

    private static final String TAG = DEV_TAG + AutoFitGridLayoutManager.class.getSimpleName();
    private int columnWidth;
    private boolean columnWidthChanged = true;

    public AutoFitGridLayoutManager(Context context, int columnWidth) {
        super(context, 1);

        setColumnWidth(columnWidth);
    }

    public void setColumnWidth(int newColumnWidth) {
        if (newColumnWidth > 0 && newColumnWidth != columnWidth) {
            columnWidth = newColumnWidth;
            columnWidthChanged = true;
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (columnWidthChanged && columnWidth > 0) {
            int totalSpace;
            if (getOrientation() == VERTICAL) {
                totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
            } else {
                totalSpace = getHeight() - getPaddingTop() - getPaddingBottom();
            }
            int spanCount = Math.max(2, totalSpace / columnWidth); //min 2 allowed
            setSpanCount(spanCount);
            columnWidthChanged = false;
        }
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}