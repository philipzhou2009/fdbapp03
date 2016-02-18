package com.fragrancedubois.sg.fdbapp03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ColorWheel extends AppCompatActivity {

    RelativeLayout mCWLayout;
    public ArrayList<String> mSelections;
    public List<PerfumeXmlParser.Entry> mPerfumes;
    public List<FdbAddition> mAdditions;
    public List<FdbWheeler> mFdbWheelers;

    public PopupWindow mPW;
    public ImageView mFlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setWindowFullScreen(this);

        setContentView(R.layout.activity_color_wheel);

        Utils.enableAppbarWithBack(this);

        try {
            mFdbWheelers = FdbHelper.loadPersonalityXml(this);
            mPerfumes = FdbHelper.loadPerfumeXml(this);
            mAdditions = FdbHelper.loadAdditionsXml(this);

        } catch (IOException e) {
            Log.e("FCW", "io error");
        } catch (XmlPullParserException e) {
            Log.e("FCW", "XmlPullParserException error");
        }

        Intent intent = getIntent();
        mSelections = intent.getStringArrayListExtra("selections");

        RelativeLayout colorWheelLayout = (RelativeLayout) findViewById(R.id.colorWheelLayout);
        mCWLayout = colorWheelLayout;

        for (String select : mSelections) {
            Log.e("fdb:ColorWheel:select", select);
        }

        /*
        ArrayList<float[]> coordsList = new ArrayList<float[]>();
        for (FdbWheeler wheeler : mFdbWheelers) {
            if (true) {
                TextView tv = wheeler.createTextView1(this);
                colorWheelLayout.addView(tv);
            } else {
                for (String select : mSelections) {
                    if (wheeler.mName.equals(select)) {
                        //TextView tv = wheeler.createTextView(this);
                        TextView tv = wheeler.createHorizontalTextView(this);
                        colorWheelLayout.addView(tv);
                        float coordsXY[] = {wheeler.mRealX, wheeler.mRealY};
                        coordsList.add(coordsXY);
                    }
                }
            }
        }
        */

        // draw triangle
        /*
        FdbWheelTriangle triangle = new FdbWheelTriangle(this, coordsList);
        colorWheelLayout.addView(triangle);

        this.drawFlower();
        //this.drawBloomingAnim(coordsList);

        // draw invisible squares for perfumes
        for (PerfumeXmlParser.Entry perfume : mPerfumes) {
            View view = perfume.drawPerfume(this, mAdditions);
            colorWheelLayout.addView(view);
        }

        for (FdbAddition noteObj : mAdditions) {
            // Log.e("fcw, noteObj.mName=", "|"+ noteObj.mName + "|");
        }
        */

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        ImageView bgCw = (ImageView) findViewById(R.id.bg_colorwheel);
        int bgCwWidth = bgCw.getWidth();
        int bgCwHeight = bgCw.getHeight();
        int radius = bgCwHeight / 2;

        Log.e("bg_colorwheel width", "" + bgCwWidth);
        Log.e("bg_colorwheel height", "" + bgCwHeight);
        Log.e("bg_colorwheel radius", "" + radius);

        int[] location = new int[2];
        bgCw.getLocationOnScreen(location);
        float bgCwX = location[0];
        int bgCwY = location[1];
        Log.e("bg_colorwheel X", "" + bgCwX);
        Log.e("bg_colorwheel Y", "" + bgCwY);

        // central point
        int bgCwCX = bgCwWidth / 2;
        int bgCwCY = bgCwY + bgCwHeight / 2;
        Log.e("bgCwCX", "" + bgCwCX);
        Log.e("bgCwCY", "" + bgCwCY);

        FdbHelper.setmCenterX(bgCwCX);
        FdbHelper.setmCenterY(bgCwCY);

        float mDiameterRatio = radius / 590.0f;
        FdbHelper.mDiameterRatio = mDiameterRatio;
        FdbHelper.mScreenHeight = bgCwHeight;
        FdbHelper.mScreenWidth = bgCwWidth;
        FdbAddition.mScreenHeight = bgCwHeight;

        RelativeLayout colorWheelLayout = (RelativeLayout) findViewById(R.id.colorWheelLayout);
        mCWLayout = colorWheelLayout;

        /*
        Context mContext = this.getBaseContext();
        TextView tvc = new TextView(mContext);
        tvc.setText("X");
        tvc.setX(bgCwCX);
        tvc.setY(bgCwCY);
        colorWheelLayout.addView(tvc);
        */

        if (false) {
            for (FdbWheeler wheeler : mFdbWheelers) {
                TextView tv = wheeler.createTextView1(this);
                colorWheelLayout.addView(tv);
            }
        }

        List<FdbWheeler> wheelers = new ArrayList<>();
        for (FdbWheeler wheeler : mFdbWheelers) {
            for (String select : mSelections) {
                if (wheeler.mName.equals(select)) {
                    wheelers.add(wheeler);
                    wheeler.createTextView1(this);
                }
            }
        }

        // draw triangle
        ArrayList<float[]> coordsList = new ArrayList<>();
        for (FdbWheeler wheeler : wheelers) {
            float coordsXY[] = {wheeler.mRealX, wheeler.mRealY};
            coordsList.add(coordsXY);
        }
        FdbWheelTriangle triangle = new FdbWheelTriangle(this, coordsList);
        colorWheelLayout.addView(triangle);

        // draw text
        for (FdbWheeler wheeler : wheelers) {
            colorWheelLayout.addView(wheeler.mTV);
        }

        // flower
        //this.drawFlower();

        // draw perfume titles
        /*
        for (PerfumeXmlParser.Entry perfume : mPerfumes) {
            View view = perfume.drawPerfume(this, mAdditions, false);
            colorWheelLayout.addView(view);
        }
        */

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.color_wheel, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    public void drawFlower() {
        int iCount = 0;
        List<FdbWheeler> aEntries = new ArrayList<FdbWheeler>();

        for (FdbWheeler wheeler : mFdbWheelers) {
            if (wheeler.mFlag == true) {
                aEntries.add(wheeler);
                iCount++;
            }
        }

        if (iCount != 3) {
            return;
        }

        FdbWheeler wheeler1 = aEntries.get(0);
        FdbWheeler wheeler2 = aEntries.get(1);
        FdbWheeler wheeler3 = aEntries.get(2);

        int flowerWidth = 30;

        float fCentralX = (wheeler1.mRealX + wheeler2.mRealX + wheeler3.mRealX) / 3 - flowerWidth / 2;
        float fCentralY = (wheeler1.mRealY + wheeler2.mRealY + wheeler3.mRealY) / 3 - flowerWidth / 2;

        // find the related perfume
        float fDistance = 0f, xcoord, ycoord, fTmp;
        int iCursor = 0, iFlag = 0;
        for (PerfumeXmlParser.Entry perfume : mPerfumes) {
            xcoord = perfume.mRealX;
            ycoord = perfume.mRealY;
            fTmp = (fCentralX - xcoord) * (fCentralX - xcoord) + (fCentralY - ycoord) * (fCentralY - ycoord);
            //Log.e("fdb", Float.toString(fTmp));

            if (iCursor == 0 && fTmp <= 10000) {
                iFlag = 0;
                break;
            } else if ((fTmp <= fDistance || fDistance == 0f) && iCursor != 0) {
                fDistance = fTmp;
                iFlag = iCursor;
            }

            iCursor++;
        }

        final PerfumeXmlParser.Entry perfume = mPerfumes.get(iFlag);
        PerfumeXmlParser.Entry flowerEntry = new PerfumeXmlParser.Entry(perfume);
        flowerEntry.drawPerfume(this, mAdditions, true);

        /*
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(flowerWidth, flowerWidth);
        ImageView flower = (ImageView) flowerEntry.mFlower;
        flower.setImageResource(R.drawable.fdb_flower);
        flower.setLayoutParams(layoutParams);
        */

        View flower = flowerEntry.mView;
        flower.setX(fCentralX);
        flower.setY(fCentralY);

        mCWLayout.addView(flower);
    }

    public void drawBloomingAnim(ArrayList<float[]> coordsList) {

        int flowerWidth = 25;
        int fWidthHalf = flowerWidth / 2;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(flowerWidth, flowerWidth);
        final ImageView flower = new ImageView(this);
        flower.setImageResource(R.drawable.icon);
        flower.setLayoutParams(layoutParams);
        mCWLayout.addView(flower);

        float[] coords0 = coordsList.get(0);
        float[] coords1 = coordsList.get(1);
        float[] coords2 = coordsList.get(2);

        ArrayList<TranslateAnimation> animations = new ArrayList<TranslateAnimation>();
        final TranslateAnimation animation0 = new TranslateAnimation(coords0[0] - fWidthHalf, coords1[0] - fWidthHalf, coords0[1] - fWidthHalf, coords1[1] - fWidthHalf);
        final TranslateAnimation animation1 = new TranslateAnimation(coords1[0] - fWidthHalf, coords2[0] - fWidthHalf, coords1[1] - fWidthHalf, coords2[1] - fWidthHalf);
        final TranslateAnimation animation2 = new TranslateAnimation(coords2[0] - fWidthHalf, coords0[0] - fWidthHalf, coords2[1] - fWidthHalf, coords0[1] - fWidthHalf);

        animations.add(animation0);
        animations.add(animation1);
        animations.add(animation2);

        for (TranslateAnimation animation : animations) {
            animation.setDuration(5000);
            //animation.setRepeatCount(-1);
            //animation.setRepeatMode(2);
            animation.setFillAfter(true);
        }

        animation0.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                //Log.e("fcw", "animation0 onAnimationEnd");
                flower.startAnimation(animation1);

            }
        });

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                //Log.e("fcw", "animation1 onAnimationEnd");
                flower.startAnimation(animation2);

            }
        });

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                //Log.e("fcw", "animation2 onAnimationEnd");
                flower.startAnimation(animation0);
            }
        });

        flower.startAnimation(animation0);
    }

    /*
    public void showcustomization(View view) {
        //Log.e("fcw", "showcustomization");

        View flower_customize = findViewById(R.id.flower_customize);
        flower_customize.setOnClickListener(null);

        View tapCustomize = findViewById(R.id.fcwtapcustomize);
        tapCustomize.setVisibility(View.INVISIBLE);


        for (FdbWheeler wheeler : mFdbWheelers) {
            wheeler.hideTextView();
        }

        for (FdbAddition addition : mAdditions) {

            if(addition.mXcoord != 0) {
                // only x,y != 0, it's additional;
                String name = addition.mName;
                Log.e("fcw", name);
                //TextView tv = addition.createTextView(this);
                TextView tv = addition.createTextViewWithEvent(this, mCWLayout);
                addition.adjustTextView();
                mCWLayout.addView(tv);
            }
        }
    }
    */

    // http://stackoverflow.com/questions/5645081/android-touch-event-on-screen
    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.e("fcw", "onTouchEvent");
        if (mPW != null) {
            mPW.dismiss();
            FrameLayout layout_MainMenu = (FrameLayout) findViewById(R.id.fcwdimlayer);
            layout_MainMenu.setAlpha(0.0f);
        }
        return super.onTouchEvent(event);
    }
    */
}
