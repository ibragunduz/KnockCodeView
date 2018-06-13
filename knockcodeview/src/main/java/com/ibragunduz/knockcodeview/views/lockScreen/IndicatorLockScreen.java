package com.ibragunduz.knockcodeview.views.lockScreen;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ibragunduz.knockcodeview.R;
import com.ibragunduz.knockcodeview.views.setLock.SingleIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibrahim on 22.04.2018.
 */

public class IndicatorLockScreen extends RelativeLayout {
    public IndicatorLockScreen(Context context) {
        super(context);
        initialize(context,null);
    }

    public IndicatorLockScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);
    }
    private List<SingleIndicatorView> indicators;
    private View rootView;
    private SingleIndicatorView vw1,vw2,vw3,vw4,vw5,vw6,vw7,vw8;
    private void initialize(Context context,AttributeSet attributeSet){
        rootView = LayoutInflater.from(context).inflate(R.layout.clicks_lock_screen,this);
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

    public void setBlack(){
        for (SingleIndicatorView s : indicators){
            s.makeBlack();
        }

        setColorFilter(Color.BLACK);
    }

    public void setWhite(){
        setColorFilter(Color.WHITE);
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

    public void updateClicks(int[] clicks){
        for (int i = 0;i<clicks.length;i++){
            if (clicks[i]==-1)
            {
                indicators.get(i).setVisibility(View.GONE);
                continue;
            }

            if ( i<clicks.length-1){
                if (clicks[i+1]==-1){
                indicators.get(i).setVisibility(View.VISIBLE);
                if (isSecretIndicator==false){

                indicators.get(i).setClicked(clicks[i],normalSize);
                if (i>0)indicators.get(i-1).updateViewWithDot(normalSize);
                }else{
                    indicators.get(i).updateViewWithDot(normalSize);
                }
                }
            }else if(i==7) {
                if (isSecretIndicator == false) {

                    indicators.get(i).setVisibility(View.VISIBLE);
                    indicators.get(i).setClicked(clicks[i], normalSize);
                    indicators.get(i - 1).updateViewWithDot(normalSize);
                }else{
                    indicators.get(i).updateViewWithDot(normalSize);
                }
            }
        }
    }




    boolean isSecretIndicator = false;

    public void setIsSecretIndicator(boolean isSecretIndicator){
        this.isSecretIndicator = isSecretIndicator;
    }
    int normalSize = 0;

    public void setHeightWidth(int w,int h){
    normalSize=w;
        for (int i = 0; i<indicators.size();i++){
            indicators.get(i).setLayoutHeightWidth(w,h);
        }
    }

    public void setMargins(int width){
        for (int i = 0; i<indicators.size();i++){
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) indicators.get(i).getLayoutParams();
            p.setMargins(width,width,width,width);
            indicators.get(i).requestLayout();
        }
    }



}
