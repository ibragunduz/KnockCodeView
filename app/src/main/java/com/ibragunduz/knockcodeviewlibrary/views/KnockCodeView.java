package com.ibragunduz.knockcodeviewlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ibragunduz.knockcodeviewlibrary.R;
import com.ibragunduz.knockcodeviewlibrary.interfaces.ClickDetected;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class KnockCodeView extends RelativeLayout implements View.OnClickListener,ClickDetected {

    public static final int SET_PASSWORD = 0;
    public static final int LOCK_SCREEN = 1;
    private int type;
    private boolean isVibrationActive = true;

    public KnockCodeView(Context context) {
        super(context);
        initialize(context,null);
    }

    public KnockCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);

    }


   private ClickDetected clickDetector;
    private int[] clicksSquence = new int[8];
    private View rootView;
    private CardView card1,card2,card3,card4;
    private  View cornerlt1,cornerlt2,cornerlb1,cornerlb2,cornerrt1,cornerrt2,cornerrb1,cornerrb2;
    private   List<CardView> cardViewsList;
    private List<View> cornerViews;
    private   LinearLayout layoutBack;
    private View line1,line3,lineCenter;


    private void initialize(Context context,AttributeSet attributeSet){
        timer = new Timer();


        rootView =  LayoutInflater.from(context).inflate(R.layout.activity_knock_code_view, this);
        card1 = (CardView) rootView.findViewById(R.id.cardview_1);
        card2 = (CardView) rootView.findViewById(R.id.cardview_2);
        card3 = (CardView) rootView.findViewById(R.id.cardview_3);
        card4 = (CardView) rootView.findViewById(R.id.cardview_4);

        line1 = (View) rootView.findViewById(R.id.knock_view_line1);
        lineCenter = (View) rootView.findViewById(R.id.knock_view_line2_center);
        line3 = (View) rootView.findViewById(R.id.knock_view_line3);

        layoutBack = (LinearLayout) rootView.findViewById(R.id.knock_code_view_linear_layout_back);

        cardViewsList = new ArrayList<>();
        cardViewsList.add(card1);
        cardViewsList.add(card2);
        cardViewsList.add(card3);
        cardViewsList.add(card4);



        cornerlb1 =   rootView.findViewById(R.id.knock_view_corner_lb1);
        cornerlb2 =   rootView.findViewById(R.id.knock_view_corner_lb2);

        cornerlt1 =   rootView.findViewById(R.id.knock_view_corner_lt1);
        cornerlt2 =   rootView.findViewById(R.id.knock_view_corner_lt2);

        cornerrb1 =   rootView.findViewById(R.id.knock_view_corner_rb1);
        cornerrb2 =   rootView.findViewById(R.id.knock_view_corner_rb2);

        cornerrt1 =   rootView.findViewById(R.id.knock_view_corner_rt1);
        cornerrt2 =   rootView.findViewById(R.id.knock_view_corner_rt2);

        cornerViews = new ArrayList<>();
        cornerViews.add(cornerlb1);
        cornerViews.add(cornerlb2);
        cornerViews.add(cornerlt1);
        cornerViews.add(cornerlt2);
        cornerViews.add(cornerrb1);
        cornerViews.add(cornerrb2);
        cornerViews.add(cornerrt1);
        cornerViews.add(cornerrt2);


        for (CardView crd : cardViewsList){
            crd.setOnClickListener(this);
            crd.setPreventCornerOverlap(false);
            crd.setCardElevation(0);

        }

        for (int i = 0; i< clicksSquence.length; i++){
            clicksSquence[i] = -1;
        }




        if (attributeSet != null){
            final TypedArray typedArray = context.obtainStyledAttributes(attributeSet,
                    R.styleable.KnockCodeView, 0, 0);

            setLineWidth((int) typedArray.getDimension(R.styleable.KnockCodeView_knock_line_width,5));
            setButtonsColor(typedArray.getColor(R.styleable.KnockCodeView_knock_button_color,Color.TRANSPARENT));
            setCornerColor(typedArray.getColor(R.styleable.KnockCodeView_knock_corner_color,Color.DKGRAY));
            setLineColor(typedArray.getColor(R.styleable.KnockCodeView_knock_line_color,Color.LTGRAY));

        }









    }







    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.cardview_1 :
                addClickToSquence(1);
                break;
            case  R.id.cardview_2 :
                addClickToSquence(2);
                break;
            case  R.id.cardview_3 :
                addClickToSquence(3);
                break;
            case  R.id.cardview_4 :
                addClickToSquence(4);
                break;
        }
    }




    public void resetClicksSquence(){
         clearClicks();
        firstClicked = false;

        clickDetected(clicksSquence);

    }

    private void addClickToSquence(int number){

        if (getClicksCount()<clicksSquence.length){
            try {
            clicksSquence[getClicksCount()] = number;
            }catch (IndexOutOfBoundsException e){

            }
        }


            clickDetected(clicksSquence);


    }



    private boolean isEntryIsTrue(int[] clicks){

        for (int i = 0; i<clicks.length;i++){
            if (clicks[i]!= getTruePassword()[i]){
                return false;
            }
        }

        return true;


    }

    public int getClicksCount(){
        for (int i = 0; i< clicksSquence.length; i++){
            if (clicksSquence[i]==-1)return i;

        }
        return clicksSquence.length;
    }

ClicksIndicatorView inditactor;
    public void setInditactor(ClicksIndicatorView inditactor) {
        this.inditactor = inditactor;

    }
    int sure =0;
  private   boolean isEntryTrue = false;

    final Handler handler = new Handler();

    TimerTask timertask = new TimerTask() {
        @Override
        public void run() {
            handler.post(new Runnable() {
                public void run() {
                    if (++sure > 6 && firstClicked&&!isEntryTrue){
                        inCorrectEntry();
                        Log.i("Süre : " ,sure+ "  ---" );

                    }
                }
            });
        }
    };
    Timer timer;
    boolean isTimerStarted=false;
    boolean firstClicked = false;



    @Override
    public void clickDetected(final int[] clicks) {


    if (getType() == LOCK_SCREEN){
            sure = 0;
            firstClicked = true;
            if (getClicksCount()==1 && !isTimerStarted){
                timer.schedule(timertask, 0, 100);
                isTimerStarted = true;
            }


                isEntryTrue = false;
               if (clicks!=null){
                   if (isEntryIsTrue(clicks)){
                       correctEntry();
                   }
               }


}else{

    }





        if (clickDetector!=null){
            clickDetector.clickDetected(clicksSquence);
        }
        if (inditactor!=null){
            inditactor.SetInditactor(clicks);
        }
    }

    @Override
    public void inCorrectEntry() {
        clearClicks();
        firstClicked = false;
        Toast.makeText(getContext(), "Yanlış giriş yaptınız tekrar deneyiniz", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void correctEntry() {
        clearClicks();
        firstClicked = false;
        isEntryTrue = true;
        Toast.makeText(getContext(), "Giriş başarılı", Toast.LENGTH_SHORT).show();

    }

    private void clearClicks(){

        for (int i = 0;i<clicksSquence.length;i++){
            clicksSquence[i] = -1;
        }

        if (inditactor!=null){
            inditactor.SetInditactor(clicksSquence);
        }
    }




    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public boolean isVibrationActive() {
        return isVibrationActive;
    }

    public void setVibrationActive(boolean b) {
        this.isVibrationActive = b;
    }





    private int[] truePassword;
    public int[] getTruePassword() {
        if (truePassword == null)truePassword =  new int[]{-1,-1,-1,-1,-1,-1,-1,1};

        if (truePassword.length<clicksSquence.length){
            int [] yeni = new int[]{-1,-1,-1,-1,-1,-1,-1,-1};
            for (int i = 0; i<truePassword.length;i++){
                yeni[i] = truePassword[i];
            }
            truePassword = yeni;
        }
        return truePassword;
    }
    public void setTruePassword(int[] truePassword) {
        this.truePassword = truePassword;
    }


    public void  setLineWidth(int width){

        line1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,width));
        line3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,width));
        lineCenter.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));


    }
    public void setViewParams(int width,int height){

        layoutBack.setLayoutParams(new RelativeLayout.LayoutParams(width, height));


    }
    public void setCornersParams(int width,int height){
        width *=3;

        //w>h
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(width,height);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        cornerlb1.setLayoutParams(rlp);

        rlp = new RelativeLayout.LayoutParams(width,height);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        cornerlt1.setLayoutParams(rlp);

        rlp = new RelativeLayout.LayoutParams(width,height);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        cornerrt1.setLayoutParams(rlp);

        rlp = new RelativeLayout.LayoutParams(width,height);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        cornerrb1.setLayoutParams(rlp);
//h>w


        rlp = new RelativeLayout.LayoutParams(height,width);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        cornerlb2.setLayoutParams(rlp);

        rlp = new RelativeLayout.LayoutParams(height,width);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        cornerlt2.setLayoutParams(rlp);

        rlp = new RelativeLayout.LayoutParams(height,width);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        cornerrt2.setLayoutParams(rlp);

        rlp = new RelativeLayout.LayoutParams(height,width);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        cornerrb2.setLayoutParams(rlp);



    }
    public int getCornerWidth(){
        return cornerlt1.getLayoutParams().width;
    }
    public int getCornerHeight(){
        return cornerlt1.getLayoutParams().height;
    }
    public int getViewWidth(){
        return layoutBack.getLayoutParams().width;
    }
    public int getViewHeight(){
        return layoutBack.getLayoutParams().height;
    }
    public void setClickDetector(ClickDetected detector){
        this.clickDetector = detector;
    }
    public void setCornerColor(  int color){
        for (View view : cornerViews){

            view.setBackgroundColor(color);


        }
    }
    public void setLineColor(int color){
        line1.setBackgroundColor(color);
        line3.setBackgroundColor(color);
        lineCenter.setBackgroundColor(color);


    }
    public void setButtonsColor( int color){
        for (CardView crd : cardViewsList){
            crd.setCardBackgroundColor(color);
        }

    }



}

