package com.hci.prototype.communicare.Custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.hci.prototype.communicare.R;

public class CanvasView extends SurfaceView
{
    public static final int POSE_BODY_FRONT = 0;
    public static final int POSE_BODY_BACK  = 1;

    private Bitmap  mBitmapF;
    private Bitmap  mBitmapB;
    private Bitmap  mBitmapP;
    private int     mPose    = 0;
    private float   mZoom    = 1;
    private float   mXD      = 0;
    private float   mYD      = 0;
    private float   mXM      = 0;
    private float   mYM      = 0;
    private float   mPosX    = 0;
    private float   mPosY    = 0;
    private float   mPinX    = 0;
    private float   mPinY    = 0;
    private boolean mVisible = false;

    public CanvasView(Context context)
    {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow()
    {
        setBackgroundResource(R.color.primary);

        mBitmapF = generateBitmap(getResources().getDrawable(R.drawable.ic_body_front));
        mBitmapB = generateBitmap(getResources().getDrawable(R.drawable.ic_body_back));
        mBitmapP = generateBitmap(getResources().getDrawable(R.drawable.ic_scope_pin));

        super.onAttachedToWindow();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                mXD = event.getX();
                mYD = event.getY();
                mXM = event.getX();
                mYM = event.getY();

                return true;
            }

            case MotionEvent.ACTION_UP:
            {
                float lXU = event.getX();
                float lYU = event.getY();

                if (Math.abs(lXU - mXD) < 10 && Math.abs(lYU - mYD) < 10)
                {
                    setPin(lXU, lYU, true);
                }

                return true;
            }

            case MotionEvent.ACTION_MOVE:
            {
                float lXU = event.getX();
                float lYU = event.getY();

                setTranslation(lXU - mXM, lYU - mYM);

                mXM = lXU;
                mYM = lYU;

                return true;
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.save();

        if (mVisible)
        {
            canvas.drawBitmap(mBitmapP, null, calculatePin(mBitmapP), null);
        }

        float lPosX = ((getWidth() * mZoom) - getWidth()) / 2;
        float lPosY = ((getHeight() * mZoom) - getHeight()) / 2;

        if (mZoom > 1)
        {
            canvas.translate(-lPosX + mPosX, -lPosY + mPosY);
        }
        else
        {
            mPosX = 0;
            mPosY = 0;

            canvas.translate(-lPosX, -lPosY);
        }

        canvas.scale(mZoom, mZoom);

        switch (mPose)
        {
            case POSE_BODY_FRONT:
            {
                canvas.drawBitmap(mBitmapF, null, calculatePose(mBitmapF), null);

                break;
            }

            case POSE_BODY_BACK:
            {
                canvas.drawBitmap(mBitmapB, null, calculatePose(mBitmapB), null);

                break;
            }
        }

        canvas.restore();
    }

    private Rect calculatePin(Bitmap bitmap)
    {
        float lBitmapWidth  = bitmap.getWidth();
        float lBitmapHeight = bitmap.getHeight();

        int   lWidth  = (int) (lBitmapWidth * 0.16f);
        int   lHeight = (int) (lBitmapHeight * 0.16f);
        int   lLeft   = (int) (mPinX - (lWidth / 2));
        int   lTop    = (int) (mPinY - (lHeight / 2));

        return new Rect(lLeft, lTop, (lLeft + lWidth), (lTop + lHeight));
    }

    private Rect calculatePose(Bitmap bitmap)
    {
        float lCanvasWidth  = getWidth();
        float lCanvasHeight = getHeight();
        float lBitmapWidth  = bitmap.getWidth();
        float lBitmapHeight = bitmap.getHeight();
        float lRateHeight   = lCanvasHeight * 0.02f;

        float lRate   = lCanvasHeight - (lRateHeight * 2);
        float lScale  = lRate / lBitmapHeight;
        int   lWidth  = (int) (lBitmapWidth * lScale);
        int   lHeight = (int) (lBitmapHeight * lScale);
        int   lLeft   = (int) ((lCanvasWidth / 2) - (lBitmapWidth / 2));
        int   lTop    = (int) (lRateHeight);

        return new Rect(lLeft, lTop, (lLeft + lWidth), (lTop + lHeight));
    }

    private Bitmap generateBitmap(Drawable drawable)
    {
        int    lWidth  = drawable.getIntrinsicWidth();
        int    lHeight = drawable.getIntrinsicHeight();
        Bitmap lBitmap = Bitmap.createBitmap(lWidth, lHeight, Bitmap.Config.ARGB_8888);
        Canvas lCanvas = new Canvas(lBitmap);

        drawable.setBounds(0, 0, lCanvas.getWidth(), lCanvas.getHeight());
        drawable.draw(lCanvas);

        return lBitmap;
    }

    public int getPose()
    {
        return mPose;
    }

    public void setPose(int pose)
    {
        if (pose == POSE_BODY_FRONT || pose == POSE_BODY_BACK)
        {
            mPose = pose;

            invalidate();
        }
    }

    public void setZoom(float zoom)
    {
        mZoom = zoom;

        invalidate();
    }

    public void setPin(float x, float y, boolean visible)
    {
        mPinX    = x;
        mPinY    = y;
        mVisible = visible;

        invalidate();
    }

    public void setTranslation(float x, float y)
    {
        mPosX += x;
        mPosY += y;

        invalidate();
    }
}