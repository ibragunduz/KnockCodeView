package com.ibragunduz.knockcodeview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
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




        if (attributeSet!=null){
            final TypedArray typedArray = context.obtainStyledAttributes(attributeSet,
                    R.styleable.ClicksIndicatorView, 0, 0);


            SingleIndicatorView myInditactor = new SingleIndicatorView(context);
            myInditactor.setLineWidth((int) typedArray.getDimension(R.styleable.ClicksIndicatorView_in_line_width,1));
            myInditactor.setLineColor(typedArray.getColor(R.styleable.ClicksIndicatorView_in_line_color,Color.WHITE));
            myInditactor.setClickedColor(typedArray.getColor(R.styleable.ClicksIndicatorView_in_selected_color,Color.DKGRAY));
            myInditactor.setColorButtons(typedArray.getColor(R.styleable.ClicksIndicatorView_in_button_color,Color.LTGRAY));
            SetSingleIndicator(myInditactor);

            setMargins((int) typedArray.getDimension(R.styleable.ClicksIndicatorView_in_views_margin,5));




        }
        for (int i = 0;i<indicators.size();i++){
            indicators.get(i).setVisibility(View.GONE);
        }



        }

    public void SetSingleIndicator(SingleIndicatorView sngleIndicator){
     for (int i = 0; i<indicators.size();i++){
         indicators.get(i).setColorButtons(sngleIndicator.getColorButtons());
         indicators.get(i).setClickedColor(sngleIndicator.getClickedColor());
         indicators.get(i).setLineColor(sngleIndicator.getLineColor());
         indicators.get(i).setLineWidth(sngleIndicator.getLineWidth());


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
                indicators.get(i).setVisibility(View.GONE);
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