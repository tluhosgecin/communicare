package com.hci.prototype.communicare.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.hci.prototype.communicare.Activity.BodyActivity;
import com.hci.prototype.communicare.Activity.BreathActivity;
import com.hci.prototype.communicare.Activity.WaterClosetActivity;
import com.hci.prototype.communicare.R;

public class CategoryAdapter extends PagerAdapter
{
    public static final int LANGUAGE_EN        = 0;
    public static final int LANGUAGE_EE        = 1;
    public static int       LANGUAGE           = 0;
    public static final int LAYOUT_PAIN        = 0;
    public static final int LAYOUT_BREATH      = 1;
    public static final int LAYOUT_WATERCLOSET = 2;

    private Context     mContext;
    private ViewGroup   mCollection;
    private int[]       mLayout;
    private MediaPlayer mMediaPainEn;
    private MediaPlayer mMediaPainEe;
    private MediaPlayer mMediaBreathEn;
    private MediaPlayer mMediaBreathEe;
    private MediaPlayer mMediaWaterClosetEn;
    private MediaPlayer mMediaWaterClosetEe;

    public CategoryAdapter(Context context)
    {
        mContext            = context;
        mLayout             = new int[] { R.layout.categoryitem_pain,  R.layout.categoryitem_breath,  R.layout.categoryitem_watercloset };
        mMediaPainEn        = MediaPlayer.create(mContext, R.raw.voice_pain_en);
        mMediaPainEe        = MediaPlayer.create(mContext, R.raw.voice_pain_ee);
        mMediaBreathEn      = MediaPlayer.create(mContext, R.raw.voice_breath_en);
        mMediaBreathEe      = MediaPlayer.create(mContext, R.raw.voice_breath_ee);
        mMediaWaterClosetEn = MediaPlayer.create(mContext, R.raw.voice_watercloset_en);
        mMediaWaterClosetEe = MediaPlayer.create(mContext, R.raw.voice_watercloset_ee);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position)
    {
        ViewGroup lLayout = (ViewGroup) LayoutInflater.from(mContext)
                .inflate(mLayout[position], collection,false);

        switch (position)
        {
            case LAYOUT_PAIN:
            {
                Button      lButtonPain      = lLayout.findViewById(R.id.button_item_pain_title);
                ImageButton lButtonSoundPain = lLayout.findViewById(R.id.button_item_pain_sound);

                lButtonPain.setText((LANGUAGE == LANGUAGE_EE) ? R.string.category_pain_ee : R.string.category_pain_en);
                lButtonPain.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        mContext.startActivity(new Intent(mContext, BodyActivity.class));
                    }
                });

                lButtonSoundPain.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        resolveMedia(LAYOUT_PAIN);
                    }
                });

                break;
            }

            case LAYOUT_BREATH:
            {
                Button      lButtonBreath      = lLayout.findViewById(R.id.button_item_breath_title);
                ImageButton lButtonSoundBreath = lLayout.findViewById(R.id.button_item_breath_sound);

                lButtonBreath.setText((LANGUAGE == LANGUAGE_EE) ? R.string.category_breath_ee : R.string.category_breath_en);
                lButtonBreath.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        mContext.startActivity(new Intent(mContext, BreathActivity.class));
                    }
                });

                lButtonSoundBreath.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        resolveMedia(LAYOUT_BREATH);
                    }
                });

                break;
            }

            case LAYOUT_WATERCLOSET:
            {
                Button      lButtonWaterCloset      = lLayout.findViewById(R.id.button_item_watercloset_title);
                ImageButton lButtonSoundWaterCloset = lLayout.findViewById(R.id.button_item_watercloset_sound);

                lButtonWaterCloset.setText((LANGUAGE == LANGUAGE_EE) ? R.string.category_watercloset_ee : R.string.category_watercloset_en);
                lButtonWaterCloset.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        mContext.startActivity(new Intent(mContext, WaterClosetActivity.class));
                    }
                });

                lButtonSoundWaterCloset.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        resolveMedia(LAYOUT_WATERCLOSET);
                    }
                });

                break;
            }
        }

        mCollection = collection;
        mCollection.addView(lLayout);

        return lLayout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view)
    {
        collection.removeView((View) view);
    }

    @Override
    public int getCount()
    {
        return mLayout.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    public void setLanguage(int language)
    {
        LANGUAGE = language;

        Button      lButtonPain             = mCollection.findViewById(R.id.button_item_pain_title);
        ImageButton lButtonSoundPain        = mCollection.findViewById(R.id.button_item_pain_sound);
        Button      lButtonBreath           = mCollection.findViewById(R.id.button_item_breath_title);
        ImageButton lButtonSoundBreath      = mCollection.findViewById(R.id.button_item_breath_sound);
        Button      lButtonWaterCloset      = mCollection.findViewById(R.id.button_item_watercloset_title);
        ImageButton lButtonSoundWaterCloset = mCollection.findViewById(R.id.button_item_watercloset_sound);

        if (lButtonPain != null && lButtonSoundPain != null)
        {
            lButtonPain.setText((LANGUAGE == LANGUAGE_EE) ? R.string.category_pain_ee : R.string.category_pain_en);
            lButtonSoundPain.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    resolveMedia(LAYOUT_PAIN);
                }
            });
        }

        if (lButtonBreath != null && lButtonSoundBreath != null)
        {
            lButtonBreath.setText((LANGUAGE == LANGUAGE_EE) ? R.string.category_breath_ee : R.string.category_breath_en);
            lButtonSoundBreath.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    resolveMedia(LAYOUT_BREATH);
                }
            });
        }

        if (lButtonWaterCloset != null && lButtonSoundWaterCloset != null)
        {
            lButtonWaterCloset.setText((LANGUAGE == LANGUAGE_EE) ? R.string.category_watercloset_ee : R.string.category_watercloset_en);
            lButtonSoundWaterCloset.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    resolveMedia(LAYOUT_WATERCLOSET);
                }
            });
        }
    }

    private void resolveMedia(int state)
    {
        switch (CategoryAdapter.LANGUAGE)
        {
            case CategoryAdapter.LANGUAGE_EN:
            {
                switch (state)
                {
                    case LAYOUT_PAIN:
                    {
                        mMediaPainEn.start();

                        break;
                    }

                    case LAYOUT_BREATH:
                    {
                        mMediaBreathEn.start();

                        break;
                    }

                    case LAYOUT_WATERCLOSET:
                    {
                        mMediaWaterClosetEn.start();

                        break;
                    }
                }

                break;
            }

            case CategoryAdapter.LANGUAGE_EE:
            {
                switch (state)
                {
                    case LAYOUT_PAIN:
                    {
                        mMediaPainEe.start();

                        break;
                    }

                    case LAYOUT_BREATH:
                    {
                        mMediaBreathEe.start();

                        break;
                    }

                    case LAYOUT_WATERCLOSET:
                    {
                        mMediaWaterClosetEe.start();

                        break;
                    }
                }

                break;
            }
        }
    }
}
