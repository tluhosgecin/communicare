package com.hci.prototype.communicare.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hci.prototype.communicare.Adapter.CategoryAdapter;
import com.hci.prototype.communicare.R;

public class PainActivity extends AppCompatActivity
{
    private final int PAIN_DULL   = 0;
    private final int PAIN_SHARP  = 1;
    private final int PAIN_BURN   = 2;
    private final int PAIN_NOLIST = 3;

    private Button      mButtonBack;
    private Button      mButtonMenu;
    private ImageButton mButtonEn;
    private ImageButton mButtonEe;
    private Button      mButtonDull;
    private Button      mButtonSharp;
    private Button      mButtonBurn;
    private Button      mButtonNolist;
    private MediaPlayer mMediaDullEn;
    private MediaPlayer mMediaDullEe;
    private MediaPlayer mMediaSharpEn;
    private MediaPlayer mMediaSharpEe;
    private MediaPlayer mMediaBurnEn;
    private MediaPlayer mMediaBurnEe;
    private MediaPlayer mMediaNolistEn;
    private MediaPlayer mMediaNolistEe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pain);

        mButtonBack    = findViewById(R.id.button_pain_back);
        mButtonMenu    = findViewById(R.id.button_pain_menu);
        mButtonEn      = findViewById(R.id.button_pain_english);
        mButtonEe      = findViewById(R.id.button_pain_eesti);
        mButtonDull    = findViewById(R.id.button_pain_dull);
        mButtonSharp   = findViewById(R.id.button_pain_sharp);
        mButtonBurn    = findViewById(R.id.button_pain_burn);
        mButtonNolist  = findViewById(R.id.button_pain_nolist);
        mMediaDullEn   = MediaPlayer.create(this, R.raw.voice_dull_en);
        mMediaDullEe   = MediaPlayer.create(this, R.raw.voice_dull_ee);
        mMediaSharpEn  = MediaPlayer.create(this, R.raw.voice_sharp_en);
        mMediaSharpEe  = MediaPlayer.create(this, R.raw.voice_sharp_ee);
        mMediaBurnEn   = MediaPlayer.create(this, R.raw.voice_burn_en);
        mMediaBurnEe   = MediaPlayer.create(this, R.raw.voice_burn_ee);
        mMediaNolistEn = MediaPlayer.create(this, R.raw.voice_nolist_en);
        mMediaNolistEe = MediaPlayer.create(this, R.raw.voice_nolist_ee);

        mButtonBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), BodyActivity.class));
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

        mButtonDull.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveMedia(PAIN_DULL);
            }
        });

        mButtonSharp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveMedia(PAIN_SHARP);
            }
        });

        mButtonBurn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveMedia(PAIN_BURN);
            }
        });

        mButtonNolist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveMedia(PAIN_NOLIST);
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
                mButtonDull.setText(R.string.pain_dull_en);
                mButtonSharp.setText(R.string.pain_sharp_en);
                mButtonBurn.setText(R.string.pain_burn_en);
                mButtonNolist.setText(R.string.pain_nolist_en);

                break;
            }

            case CategoryAdapter.LANGUAGE_EE:
            {
                mButtonDull.setText(R.string.pain_dull_ee);
                mButtonSharp.setText(R.string.pain_sharp_ee);
                mButtonBurn.setText(R.string.pain_burn_ee);
                mButtonNolist.setText(R.string.pain_nolist_ee);

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
                    case PAIN_DULL:
                    {
                        mMediaDullEn.start();

                        break;
                    }

                    case PAIN_SHARP:
                    {
                        mMediaSharpEn.start();

                        break;
                    }

                    case PAIN_BURN:
                    {
                        mMediaBurnEn.start();

                        break;
                    }

                    case PAIN_NOLIST:
                    {
                        mMediaNolistEn.start();

                        break;
                    }
                }

                break;
            }

            case CategoryAdapter.LANGUAGE_EE:
            {
                switch (state)
                {
                    case PAIN_DULL:
                    {
                        mMediaDullEe.start();

                        break;
                    }

                    case PAIN_SHARP:
                    {
                        mMediaSharpEe.start();

                        break;
                    }

                    case PAIN_BURN:
                    {
                        mMediaBurnEe.start();

                        break;
                    }

                    case PAIN_NOLIST:
                    {
                        mMediaNolistEe.start();

                        break;
                    }
                }

                break;
            }
        }
    }
}