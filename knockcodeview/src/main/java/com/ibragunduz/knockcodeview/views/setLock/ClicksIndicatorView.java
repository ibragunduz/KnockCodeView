package com.ibragunduz.knockcodeview.views.setLock;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.ibragunduz.knockcodeview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibrahim on 14.09.2017.
 */

public class ClicksIndicatorView extends RelativeLayout {
    public ClicksIndicatorView(Context context) {
        super(context);
        initialize(context,null);
    }

    public ClicksIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);
    }
    private List<SingleIndicatorView> indicators;
    private View rootView;
    private SingleIndicatorView vw1,vw2,vw3,vw4,vw5,vw6,vw7,vw8;
    private void initialize(Context context,AttributeSet attributeSet){
     rootView = LayoutInflater.from(context).inflate(R.layout.clicks_indicator,this);
        vw1 = (SingleIndicatorView) rootView.findViewById(R.id.inditactor_view_1);
        vw2 = (SingleIndicatorView) rootView.findViewById(R.id.inditactor_view_2);
        vw3 = (SingleIndicatorView) rootView.findViewById(R.id.inditactor_view_3);
        vw4 = (SingleIndicatorView) rootView.findViewById(R.id.inditactor_view_4);
        vw5 = (SingleIndicatorView) rootView.findViewById(R.id.inditactor_view_5);
        vw6 = (SingleIndicatorView) rootView.findViewById(R.id.inditactor_view_6);
        vw7 = (SingleIndicatorView) rootView.findViewById(R.id.inditactor_view_7);
        vw8 = (SingleIndicatorView) rootView.findViewById(R.id.inditactor_view_8);

        indicators = new ArrayList<>();
        indicators.add(vw1);
        indicators.add(vw2);
        indicators.add(vw3);
        indicators.add(vw4);
        indicators.add(vw5);
        indicators.add(vw6);
        indicators.add(vw7);
        indicators.add(vw8);







        for (int i = 0;i<indicators.size();i++){
            indicators.get(i).setVisibility(View.GONE);
        }



        }


    public void setColorFilter(int color){
        vw1.setColorFilter(color);
        vw2.setColorFilter(color);
        vw3.setColorFilter(color);
        vw4.setColorFilter(color);
        vw5.setColorFilter(color);
        vw6.setColorFilter(color);
        vw7.setColorFilter(color);
        vw8.setColorFilter(color);
    }


    

    
public void setHeightWidth(int w,int h){
    for (int i = 0; i<indicators.size();i++){
        ViewGroup.LayoutParams p = (ViewGroup.MarginLayoutParams) indicators.get(i).getLayoutParams();
        p.height = h;
        p.width = w;

        indicators.get(i).requestLayout();
    }
}

public void setMargins(int width){
    for (int i = 0; i<indicators.size();i++){
        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) indicators.get(i).getLayoutParams();
        p.setMargins(width,width,width,width);
        indicators.get(i).requestLayout();
    }
}

public void SetInditactor(int[] clicks){
    for (int i = 0;i<clicks.length;i++){
        switch (clicks[i]){

            case -1 :
                indicators.get(i).setVisibility(View.VISIBLE);
                indicators.get(i).setBackgroundResource(R.drawable.ic_knock_code_empty);
                Log.i("2","----");


                break;
            case 1 :
            case 2 :
            case 3 :
            case 4 :
                indicators.get(i).setVisibility(View.VISIBLE);

                indicators.get(i).setClicked(clicks[i]);
                break;






        }
    }
}







}
