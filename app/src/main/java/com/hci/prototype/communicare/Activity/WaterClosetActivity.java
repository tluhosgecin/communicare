package com.hci.prototype.communicare.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.hci.prototype.communicare.Adapter.CategoryAdapter;
import com.hci.prototype.communicare.R;

public class WaterClosetActivity extends AppCompatActivity
{
    private final int WATERCLOSET_PEE    = 0;
    private final int WATERCLOSET_POOP   = 1;
    private final int WATERCLOSET_WASH   = 2;
    private final int WATERCLOSET_SHOWER = 3;

    private Button      mButtonBack;
    private Button      mButtonMenu;
    private ImageButton mButtonEn;
    private ImageButton mButtonEe;
    private Button      mButtonPee;
    private Button      mButtonPoop;
    private Button      mButtonWash;
    private Button      mButtonShower;
    private MediaPlayer mMediaPeeEn;
    private MediaPlayer mMediaPeeEe;
    private MediaPlayer mMediaPoopEn;
    private MediaPlayer mMediaPoopEe;
    private MediaPlayer mMediaWashEn;
    private MediaPlayer mMediaWashEe;
    private MediaPlayer mMediaShowerEn;
    private MediaPlayer mMediaShowerEe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_watercloset);

        mButtonBack    = findViewById(R.id.button_watercloset_back);
        mButtonMenu    = findViewById(R.id.button_watercloset_menu);
        mButtonEn      = findViewById(R.id.button_watercloset_english);
        mButtonEe      = findViewById(R.id.button_watercloset_eesti);
        mButtonPee     = findViewById(R.id.button_watercloset_pee);
        mButtonPoop    = findViewById(R.id.button_watercloset_poop);
        mButtonWash    = findViewById(R.id.button_watercloset_wash);
        mButtonShower  = findViewById(R.id.button_watercloset_shower);
        mMediaPeeEn    = MediaPlayer.create(this, R.raw.voice_pee_en);
        mMediaPeeEe    = MediaPlayer.create(this, R.raw.voice_pee_ee);
        mMediaPoopEn   = MediaPlayer.create(this, R.raw.voice_poop_en);
        mMediaPoopEe   = MediaPlayer.create(this, R.raw.voice_poop_ee);
        mMediaWashEn   = MediaPlayer.create(this, R.raw.voice_wash_en);
        mMediaWashEe   = MediaPlayer.create(this, R.raw.voice_wash_ee);
        mMediaShowerEn = MediaPlayer.create(this, R.raw.voice_shower_en);
        mMediaShowerEe = MediaPlayer.create(this, R.raw.voice_shower_ee);

        mButtonBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), CategoryActivity.class));
            }
        });

        mButtonMenu.setOnClickListener(new View.OnClickListener()
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
                resolveLanguage(CategoryAdapter.LANGUAGE_EN);
            }
        });

        mButtonEe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveLanguage(CategoryAdapter.LANGUAGE_EE);
            }
        });

        mButtonPee.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveMedia(WATERCLOSET_PEE);
            }
        });

        mButtonPoop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveMedia(WATERCLOSET_POOP);
            }
        });

        mButtonWash.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveMedia(WATERCLOSET_WASH);
            }
        });

        mButtonShower.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveMedia(WATERCLOSET_SHOWER);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        resolveLanguage(CategoryAdapter.LANGUAGE);
        resolveButtonsEnabled(true);
    }

    private void resolveButtonsEnabled(boolean value)
    {
        mButtonBack.setEnabled(value);
        mButtonMenu.setEnabled(value);
    }

    private void resolveLanguage(int language)
    {
        CategoryAdapter.LANGUAGE = language;

        switch (CategoryAdapter.LANGUAGE)
        {
            case CategoryAdapter.LANGUAGE_EN:
            {
                mButtonPee.setText(R.string.watercloset_pee_en);
                mButtonPoop.setText(R.string.watercloset_poop_en);
                mButtonWash.setText(R.string.watercloset_wash_en);
                mButtonShower.setText(R.string.watercloset_shower_en);

                break;
            }

            case CategoryAdapter.LANGUAGE_EE:
            {
                mButtonPee.setText(R.string.watercloset_pee_ee);
                mButtonPoop.setText(R.string.watercloset_poop_ee);
                mButtonWash.setText(R.string.watercloset_wash_ee);
                mButtonShower.setText(R.string.watercloset_shower_ee);

                break;
            }
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
                    case WATERCLOSET_PEE:
                    {
                        mMediaPeeEn.start();

                        break;
                    }

                    case WATERCLOSET_POOP:
                    {
                        mMediaPoopEn.start();

                        break;
                    }

                    case WATERCLOSET_WASH:
                    {
                        mMediaWashEn.start();

                        break;
                    }

                    case WATERCLOSET_SHOWER:
                    {
                        mMediaShowerEn.start();

                        break;
                    }
                }

                break;
            }

            case CategoryAdapter.LANGUAGE_EE:
            {
                switch (state)
                {
                    case WATERCLOSET_PEE:
                    {
                        mMediaPeeEe.start();

                        break;
                    }

                    case WATERCLOSET_POOP:
                    {
                        mMediaPoopEe.start();

                        break;
                    }

                    case WATERCLOSET_WASH:
                    {
                        mMediaWashEe.start();

                        break;
                    }

                    case WATERCLOSET_SHOWER:
                    {
                        mMediaShowerEe.start();

                        break;
                    }
                }

                break;
            }
        }
    }
}