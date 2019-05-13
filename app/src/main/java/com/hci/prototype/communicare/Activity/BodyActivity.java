package com.hci.prototype.communicare.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.hci.prototype.communicare.R;
import com.hci.prototype.communicare.Custom.CanvasView;

public class BodyActivity extends AppCompatActivity
{
    private CanvasView mCanvasView;
    private Button     mButtonBack;
    private Button     mButtonMenu;
    private Button     mButtonTurn;
    private Button     mButtonNext;
    private SeekBar    mSeekZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_body);

        mCanvasView = findViewById(R.id.surfaceview_body_canvas);
        mButtonBack = findViewById(R.id.button_body_back);
        mButtonMenu = findViewById(R.id.button_body_menu);
        mButtonTurn = findViewById(R.id.button_body_turn);
        mButtonNext = findViewById(R.id.button_body_next);
        mSeekZoom   = findViewById(R.id.seekbar_body_zoom);

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

        mButtonTurn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mCanvasView.setPose((mCanvasView.getPose() == CanvasView.POSE_BODY_FRONT) ?
                        CanvasView.POSE_BODY_BACK : CanvasView.POSE_BODY_FRONT);
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resolveButtonsEnabled(false);
                startActivity(new Intent(getBaseContext(), PainActivity.class));
            }
        });

        mSeekZoom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar bar, int progress, boolean user)
            {
                resolveSurfaceZoom(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar bar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar bar)
            {

            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        resolveButtonsEnabled(true);
    }

    private void resolveButtonsEnabled(boolean value)
    {
        mButtonBack.setEnabled(value);
        mButtonMenu.setEnabled(value);
        mButtonNext.setEnabled(value);
    }

    private void resolveSurfaceZoom(int progress)
    {
        mCanvasView.setZoom(1 + (0.2f * (float) progress));
    }
}