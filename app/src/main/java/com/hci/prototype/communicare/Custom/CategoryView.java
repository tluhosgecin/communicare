package com.hci.prototype.communicare.Custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.hci.prototype.communicare.Adapter.CategoryAdapter;

public class CategoryView extends ViewPager
{
    public CategoryView(Context context)
    {
        super(context);

        setAdapter(new CategoryAdapter(context));
    }

    public CategoryView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        setAdapter(new CategoryAdapter(context));
    }
}