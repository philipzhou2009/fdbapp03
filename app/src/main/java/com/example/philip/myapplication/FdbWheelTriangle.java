package com.example.philip.myapplication;

/**
 * Created by philip on 10/21/14.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class FdbWheelTriangle extends View {
    Paint paint = new Paint();

    ArrayList<float[]> mCoordsList;
    Context mContext;

    public FdbWheelTriangle(Context context, ArrayList<float[]> coordsList) {
        super(context);
        paint.setColor(context.getResources().getColor(R.color.gold));
        paint.setStrokeWidth(1);

        mCoordsList = coordsList;
        mContext = context;
    }

    @Override
    public void onDraw(Canvas canvas) {

        //canvas.drawLine(0, 0, 20, 20, paint);
        //canvas.drawLine(20, 0, 0, 20, paint);
        float[] coords0 = mCoordsList.get(0);
        float[] coords1 = mCoordsList.get(1);
        float[] coords2 = mCoordsList.get(2);

        Path path = new Path();
        path.moveTo(coords0[0], coords0[1]);
        path.lineTo(coords1[0], coords1[1]);
        path.lineTo(coords2[0], coords2[1]);
        path.lineTo(coords0[0], coords0[1]);
        path.close();

        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawPath(path, paint);

        //canvas.drawLine(0, 0, coords0[0], coords0[1], paint);
        //Log.e("coords0[0]", "" + coords0[0]);
        //Log.e("coords0[1]", "" + coords0[1]);
    }
}
