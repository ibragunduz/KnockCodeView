package com.ibragunduz.knockcodeview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.ibragunduz.knockcodeview.R;


/**
 * Created by ibrahim on 14.width9.2width17.
 */

public class SingleIndicatorView extends RelativeLayout  {
    public SingleIndicatorView(Context context) {
        super(context);
        initialize(context,null);
    }

    public SingleIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);
    }

   private View rootView;
   private LinearLayout llback,ll1,ll2,ll3,ll4;
    private void initialize(Context context,AttributeSet attributeSet){
        rootView =  LayoutInflater.from(context).inflate(R.layout.single_click_indicator_view,this);
        llback = (LinearLayout)rootView.findViewById(R.id.indicator_knock_view_back);
        ll1 = (LinearLayout)rootView.findViewById(R.id.indicator_knock_view_1);
        ll2 = (LinearLayout)rootView.findViewById(R.id.indicator_knock_view_2);
        ll3 = (LinearLayout)rootView.findViewById(R.id.indicator_knock_view_3);
        ll4 = (LinearLayout)rootView.findViewById(R.id.indicator_knock_view_4);

        if (attributeSet != null){
            final TypedArray typedArray = context.obtainStyledAttributes(attributeSet,
                    R.styleable.SingleIndicatorView, 0, 0);

            setLineWidth((int) typedArray.getDimension(R.styleable.SingleIndicatorView_line_width,1));
            setColorButtons(typedArray.getColor(R.styleable.SingleIndicatorView_button_color,Color.WHITE));
            setLineColor(typedArray.getColor(R.styleable.SingleIndicatorView_line_color,Color.DKGRAY));
            setClickedColor(typedArray.getColor(R.styleable.SingleIndicatorView_selected_color,Color.BLUE));

        }





    }

    int lineWidth = 0;

    public int getLineWidth() {
        return lineWidth;
    }


    public void setLineWidth(int width){
            int twoWidth  = 2 * width;
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) ll1.getLayoutParams();
            p.setMargins(twoWidth,twoWidth,width,width);
            ll1.requestLayout();


            p = (ViewGroup.MarginLayoutParams) ll2.getLayoutParams();
            p.setMargins(width,twoWidth,twoWidth,width);
            ll2.requestLayout();




            p = (ViewGroup.MarginLayoutParams) ll3.getLayoutParams();
            p.setMargins(twoWidth,width,width,twoWidth);
            ll3.requestLayout();


            p = (ViewGroup.MarginLayoutParams) ll4.getLayoutParams();
            p.setMargins(width,width,twoWidth,twoWidth);
            ll4.requestLayout();

            this.lineWidth = width;

    }





    private int colorButtons = Color.parseColor("#ffffff");
    private int clickedColor = Color.parseColor("#feafea");
    private int lineColor = Color.parseColor("#333333");

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        llback.setBackgroundColor(lineColor);
        this.lineColor = lineColor;
    }

    public int getClickedColor() {
        return clickedColor;
    }

    public void setClickedColor(int clickedColor) {
        this.clickedColor = clickedColor;
    }

    public int getColorButtons() {
        return colorButtons;
    }

    public void setColorButtons(int colorButtons) {

        ll1.setBackgroundColor(colorButtons);
        ll2.setBackgroundColor(colorButtons);
        ll3.setBackgroundColor(colorButtons);
        ll4.setBackgroundColor(colorButtons);
        this.colorButtons = colorButtons;
    }

    public void setClicked(int which){
        ll1.setBackgroundColor(getColorButtons());
        ll2.setBackgroundColor(getColorButtons());
        ll3.setBackgroundColor(getColorButtons());
        ll4.setBackgroundColor(getColorButtons());
        switch (which){
            case 1: ll1.setBackgroundColor(getClickedColor());
                break;
            case 2: ll2.setBackgroundColor(getClickedColor());
                break;
            case 3: ll3.setBackgroundColor(getClickedColor());
                break;
            case 4: ll4.setBackgroundColor(getClickedColor());
                break;
        }
    }







}
