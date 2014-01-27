package com.simultor.ingress;

import com.simulator.ingress.R;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PowerCube extends BaseActivity{
	private ImageView btLeft;
	private ImageView btRight;
	private ImageView ivPower;
	private TextView tvPowerTitre;
	private TextView tvPowerInfoLevel;
	private TextView tvPowerInfoStorage;
	private TextView tvPowerInfoRecycle;
	private LinearLayout llPowerImage;
	
	private int level = 1;
	private GestureDetector gestureDetector;
	private int posGesture = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.power_cube);
		
		btLeft = (ImageView)findViewById(R.id.btPowerLeft);
		btRight = (ImageView)findViewById(R.id.btPowerRight);
		ivPower = (ImageView)findViewById(R.id.ivPower);
		tvPowerTitre = (TextView)findViewById(R.id.tvPowerTitre);
		tvPowerInfoLevel = (TextView)findViewById(R.id.tvPowerInfoLevel);
		tvPowerInfoStorage = (TextView)findViewById(R.id.tvPowerInfoStorage);
		tvPowerInfoRecycle = (TextView)findViewById(R.id.tvPowerInfoRecycle);
		
		btLeft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				level--;
				if (level < 1){
					level = NBR_LEVEL;
				}
				updateInfo();
				if (fgDebug){Log.d(TAG, "Level = " + level);};
				
			}
		});
		
		btRight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				level++;
				if (level > 8){
					level = 1;
				}
				updateInfo();
				if (fgDebug){Log.d(TAG, "Level = " + level);};
			}
		});
		
		gestureDetector = new GestureDetector(new GestureListener());
		llPowerImage = (LinearLayout)findViewById(R.id.llPowerImage);
		llPowerImage.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;

			}
		});
		
		ivPower.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;

			}
		});
		
		
		updateInfo();
		
	}
	
	private void updateInfo(){
		updateTVPowerTitre(level);
		updateTVPowerLevel(level);
		updateIVPower(level);
		updateTVPowerStorage(level);
		updateTVPowerRecycle(level);

	}
	
	private void updateTVPowerTitre(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.power_titre) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvPowerTitre.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(level));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvPowerTitre.append(WordtoSpanPart_2);
	}
	
	private void updateIVPower(int level){
		String strView = "power_cube_medium_" + String.valueOf(level);
		int resIdView = getResources().getIdentifier(strView, "drawable", getPackageName());
		ivPower.setBackgroundDrawable(getResources().getDrawable(resIdView));
	}
	
	private void updateTVPowerLevel(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.power_level) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvPowerInfoLevel.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(level));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvPowerInfoLevel.append(WordtoSpanPart_2);
	}
	
	private void updateTVPowerStorage(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.power_storage) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvPowerInfoStorage.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(xmpPower[level - 1]) + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvPowerInfoStorage.append(WordtoSpanPart_2);
	}
	
	private void updateTVPowerRecycle(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.power_recycle) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvPowerInfoRecycle.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(level*20) + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvPowerInfoRecycle.append(WordtoSpanPart_2);
	}
	
	private final class GestureListener extends SimpleOnGestureListener {
		private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
	    @Override
	    public boolean onDoubleTap(MotionEvent e) { 
	        return super.onDoubleTap(e);
	    }

	    @Override
	    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
	    	boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        

	    }

	    @Override
	    public void onLongPress(MotionEvent e) {
	        super.onLongPress(e);
	    }

	    @Override
	    public boolean onSingleTapConfirmed(MotionEvent e) {
	        return super.onSingleTapConfirmed(e);
	    }

	    
	    public void onSwipeRight() {
	    	if (fgDebug){Log.d(TAG, "onSwipeRight");};
	    	level++;
			if (level > 8){
				level = 1;
			}
			updateInfo();
			if (fgDebug){Log.d(TAG, "Level = " + level);};
	    }

	    public void onSwipeLeft() {
	    	if (fgDebug){Log.d(TAG, "onSwipeLeft");};
	    	level--;
			if (level < 1){
				level = NBR_LEVEL;
			}
			updateInfo();
			if (fgDebug){Log.d(TAG, "Level = " + level);};
	    }

	    public void onSwipeTop() {
	    	if (fgDebug){Log.d(TAG, "onSwipeTop");};
	    }

	    public void onSwipeBottom() {
	    	if (fgDebug){Log.d(TAG, "onSwipeBottom");};
	    }
	    
	    
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateBt(BT_POWER_CUBE);
	}

}
