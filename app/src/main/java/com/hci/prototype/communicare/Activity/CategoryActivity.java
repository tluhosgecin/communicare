package com.hci.prototype.communicare.Activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.hci.prototype.communicare.Adapter.CategoryAdapter;
import com.hci.prototype.communicare.R;
import com.hci.prototype.communicare.Custom.CategoryView;

public class CategoryActivity extends AppCompatActivity
{
    private CategoryView    mCategoryView;
    private CategoryAdapter mCategoryAdapter;
    private Button          mButtonBack;
    private ImageButton     mButtonEn;
    private ImageButton     mButtonEe;
    private ImageView       mSelectorLeft;
    private ImageView       mSelectorMiddle;
    private ImageView       mSelectorRight;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);

        mCategoryView    = findViewById(R.id.viewpager_category_pager);
        mCategoryAdapter = (CategoryAdapter) mCategoryView.getAdapter();
        mButtonBack      = findViewById(R.id.button_category_back);
        mButtonEn        = findViewById(R.id.button_category_english);
        mButtonEe        = findViewById(R.id.button_category_eesti);
        mSelectorLeft    = findViewById(R.id.selector_category_left);
        mSelectorMiddle  = findViewById(R.id.selector_category_middle);
        mSelectorRight   = findViewById(R.id.selector_category_right);

        mButtonBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
            }
        });

        mButtonEn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mCategoryAdapter.setLanguage(CategoryAdapter.LANGUAGE_EN);
            }
        });

        mButtonEe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mCategoryAdapter.setLanguage(CategoryAdapter.LANGUAGE_EE);
            }
        });

        mCategoryView.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
                resolveSelectorState(position);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        resolveSelectorState(0);
        resolveButtonsEnabled(true);
    }

    private void resolveButtonsEnabled(boolean value)
    {
        mButtonBack.setEnabled(value);
    }

    private void resolveSelectorState(int position)
    {
        switch (position)
        {
            case 0:
            {
                mSelectorLeft.setBackground(getResources().getDrawable(R.drawable.ly_tab_selected));
                mSelectorMiddle.setBackground(getResources().getDrawable(R.drawable.ly_tab_default));
                mSelectorRight.setBackground(getResources().getDrawable(R.drawable.ly_tab_default));

                break;
            }

            case 1:
            {
                mSelectorLeft.setBackground(getResources().getDrawable(R.drawable.ly_tab_default));
                mSelectorMiddle.setBackground(getResources().getDrawable(R.drawable.ly_tab_selected));
                mSelectorRight.setBackground(getResources().getDrawable(R.drawable.ly_tab_default));

                break;
            }

            case 2:
            {
                mSelectorLeft.setBackground(getResources().getDrawable(R.drawable.ly_tab_default));
                mSelectorMiddle.setBackground(getResources().getDrawable(R.drawable.ly_tab_default));
                mSelectorRight.setBackground(getResources().getDrawable(R.drawable.ly_tab_selected));

                break;
            }
        }
    }
}