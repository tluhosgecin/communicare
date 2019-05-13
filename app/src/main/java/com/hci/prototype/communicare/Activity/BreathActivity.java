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

public class BreathActivity extends AppCompatActivity
{
    private final int BREATH_DIFFICULT = 0;
    private final int BREATH_THROAT    = 1;
    private final int BREATH_BURN      = 2;

    private Button      mButtonBack;
    private Button      mButtonMenu;
    private ImageButton mButtonEn;
    private ImageButton mButtonEe;
    private Button      mButtonDifficult;
    private Button      mButtonThroat;
    private Button      mButtonBurn;
    private MediaPlayer mMediaDifficultEn;
    private MediaPlayer mMediaDifficultEe;
    private MediaPlayer mMediaThroatEn;
    private MediaPlayer mMediaThroatEe;
    private MediaPlayer mMediaBurnEn;
    private MediaPlayer mMediaBurnEe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_breath);

        mButtonBack       = findViewById(R.id.button_breath_back);
        mButtonMenu       = findViewById(R.id.button_breath_menu);
        mButtonEn         = findViewById(R.id.button_breath_english);
        mButtonEe         = findViewById(R.id.button_breath_eesti);
        mButtonDifficult  = findViewById(R.id.button_breath_difficult);
        mButtonThroat     = findViewById(R.id.button_breath_throat);
        mButtonBurn       = findViewById(R.id.button_breath_burn);
        mMediaDifficultEn = MediaPlayer.create(this, R.raw.voice_difficult_en);
        mMediaDifficultEe = MediaPlayer.create(this, R.raw.voice_difficult_ee);
        mMediaThroatEn    = MediaPlayer.create(this, R.raw.voice_throat_en);
        mMediaThroatEe    = MediaPlayer.create(this, R.raw.voice_throat_ee);
        mMediaBurnEn      = MediaPlayer.create(this, R.raw.voice_burn_en);
        mMediaBurnEe      = MediaPlayer.create(this, R.raw.voice_burn_ee);

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

        mButtonDifficult.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveMedia(BREATH_DIFFICULT);
            }
        });

        mButtonThroat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveMedia(BREATH_THROAT);
            }
        });

        mButtonBurn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveMedia(BREATH_BURN);
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
                mButtonDifficult.setText(R.string.breath_difficult_en);
                mButtonThroat.setText(R.string.breath_throat_en);
                mButtonBurn.setText(R.string.breath_burn_en);

                break;
            }

            case CategoryAdapter.LANGUAGE_EE:
            {
                mButtonDifficult.setText(R.string.breath_difficult_ee);
                mButtonThroat.setText(R.string.breath_throat_ee);
                mButtonBurn.setText(R.string.breath_burn_ee);

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
                    case BREATH_DIFFICULT:
                    {
                        mMediaDifficultEn.start();

                        break;
                    }

                    case BREATH_THROAT:
                    {
                        mMediaThroatEn.start();

                        break;
                    }

                    case BREATH_BURN:
                    {
                        mMediaBurnEn.start();

                        break;
                    }
                }

                break;
            }

            case CategoryAdapter.LANGUAGE_EE:
            {
                switch (state)
                {
                    case BREATH_DIFFICULT:
                    {
                        mMediaDifficultEe.start();

                        break;
                    }

                    case BREATH_THROAT:
                    {
                        mMediaThroatEe.start();

                        break;
                    }

                    case BREATH_BURN:
                    {
                        mMediaBurnEe.start();

                        break;
                    }
                }

                break;
            }
        }
    }
}