package com.ibragunduz.knockcodeview.views.setLock;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ibragunduz.knockcodeview.R;


/**
 * Created by ibrahim on 14.width9.2width17.
 */

public class SingleIndicatorView extends RelativeLayout {
    public SingleIndicatorView(Context context) {
        super(context);
        initialize(context, null);
    }

    public SingleIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    private View rootView;

    private void initialize(Context context, AttributeSet attributeSet) {
        rootView = LayoutInflater.from(context).inflate(R.layout.single_click_indicator_view, this);


    }

    int lineWidth = 0;


    int k1 = R.drawable.ic_knock_code_full_1, k2 = R.drawable.ic_knock_code_full_2, k3 = R.drawable.ic_knock_code_full_3, k4 = R.drawable.ic_knock_code_full_4,
            ke = R.drawable.ic_knock_code_empty;

    public void setDrawableK1(int resId) {
        k1 = resId;
    }

    public void setDrawableK2(int resId) {
        k2 = resId;
    }

    public void setDrawableK3(int resId) {
        k3 = resId;
    }

    public void setDrawableK4(int resId) {
        k4 = resId;
    }

    public void setDrawableKe(int resId) {
        ke = resId;
    }


    public void setImageResource(int resid) {
        ((ImageView) rootView.findViewById(R.id.indicator_click_image_view)).setImageResource(resid);

        Log.i("burada","burada");
    }


    public void setClicked(int which,int bigSize) {

        int resID = k1;
        switch (which) {
            case 1:
                resID = k1;
                break;
            case 2:
                resID = k2;
                break;
            case 3:
                resID = k3;
                break;
            case 4:
                resID = k4;
                break;
        }

      setImageResource(resID);
        setLayoutHeightWidth(bigSize,bigSize);
    }




    public void setLayoutHeightWidth(int w,int h){
        ViewGroup.LayoutParams p = (ViewGroup.MarginLayoutParams)getLayoutParams();
        p.height = h;
        p.width = w;
        requestLayout();
    }



    public void updateViewWithDot(int normalWidth){
        if (normalWidth!=0)
        setLayoutHeightWidth(normalWidth/2,normalWidth/2);
        setImageResource(R.drawable.circle_white);
    }
    public void setColorFilter(int color) {
        ((ImageView) rootView.findViewById(R.id.indicator_click_image_view)).setColorFilter(color);

    }

}
