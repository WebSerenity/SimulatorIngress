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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Resonator extends BaseActivity{
	private ImageView btLeft;
	private ImageView btRight;
	private ImageView ivReso;
	private TextView tvResoTitre;
	private TextView tvResoInfoLevel;
	private TextView tvResoInfoEnergy;
	private TextView tvResoInfoDecay;
	//private TextView tvResoInfoRange;
	private TextView tvResoInfoCost;
	private TextView tvResoInfoAPFirst;
	private TextView tvResoInfoAPLast;
	private TextView tvResoInfoAPOther;
	private TextView tvResoInfoRecycle;
	private LinearLayout llResoImage;
	
	private int level = 1;
	
	private GestureDetector gestureDetector;
	private int posGesture = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resonator);
		
		btLeft = (ImageView)findViewById(R.id.btResoLeft);
		btRight = (ImageView)findViewById(R.id.btResoRight);
		ivReso = (ImageView)findViewById(R.id.ivReso);
		tvResoTitre = (TextView)findViewById(R.id.tvResoTitre);
		tvResoInfoLevel = (TextView)findViewById(R.id.tvResoInfoLevel);
		tvResoInfoEnergy = (TextView)findViewById(R.id.tvResoInfoEnergy);
		tvResoInfoDecay = (TextView)findViewById(R.id.tvResoInfoDecay);
		//tvResoInfoRange = (TextView)findViewById(R.id.tvResoInfoRange);
		tvResoInfoCost = (TextView)findViewById(R.id.tvResoInfoCost);
		tvResoInfoAPFirst = (TextView)findViewById(R.id.tvResoInfoAPFirst);
		tvResoInfoAPLast = (TextView)findViewById(R.id.tvResoInfoAPLast);
		tvResoInfoAPOther = (TextView)findViewById(R.id.tvResoInfoAPOther);
		tvResoInfoRecycle = (TextView)findViewById(R.id.tvResoInfoRecycle);
		
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
		llResoImage = (LinearLayout)findViewById(R.id.llBadge);
		llResoImage.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;

			}
		});
		
		ivReso.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;

			}
		});
		
		
		updateInfo();
		
	}
	
	
	
	private void updateTVResoTitre(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.reso_titre) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoTitre.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(level));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoTitre.append(WordtoSpanPart_2);
	}
	
	private void updateTVResoLevel(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.reso_level) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoLevel.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(level));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoLevel.append(WordtoSpanPart_2);
	}
	
	private void updateTVEnergy(double energy){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.energy) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoEnergy.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(String.valueOf(energy) + " k");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoEnergy.append(WordtoSpanPart_2);
	}
	
	private void updateTVResoDecay(double energy){
		double decay = energy*0.15;
		String strDecay  = distFormatter.format(decay);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.reso_decay) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoDecay.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(strDecay + " k per day");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoDecay.append(WordtoSpanPart_2);
	}
	/*
	private void updateTVResoRange(double range){
		String unit = "m";		
		if (range / 1000.00 > 1){
			range = range/1000.00;
			unit = "km";
		}
		String strRange  = distFormatter.format(range);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.reso_range) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoRange.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(strRange + " " + unit);  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoRange.append(WordtoSpanPart_2);
	}
	*/
	private void updateTVCost(int cost){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.reso_cost) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoCost.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(String.valueOf(cost) + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoCost.append(WordtoSpanPart_2);
	}
	
	private void updateTVAPFirst(int ap){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.reso_gain_first) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoAPFirst.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(String.valueOf(ap) + " AP");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoAPFirst.append(WordtoSpanPart_2);
	}
	
	private void updateTVAPLast(int ap){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.reso_gain_last) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoAPLast.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(String.valueOf(ap) + " AP");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoAPLast.append(WordtoSpanPart_2);
	}
	
	private void updateTVAPOther(int ap){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.reso_gain_other) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoAPOther.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(String.valueOf(ap) + " AP");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoAPOther.append(WordtoSpanPart_2);
	}
	
	private void updateTVRecycle(int xm){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.reso_recycle) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoRecycle.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(String.valueOf(xm) + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoInfoRecycle.append(WordtoSpanPart_2);
	}
	
	private void updateIVReso(int level){
		String strView = "resonator_medium_" + String.valueOf(level);
		int resIdView = getResources().getIdentifier(strView, "drawable", getPackageName());
		ivReso.setBackgroundDrawable(getResources().getDrawable(resIdView));
	}
	
	private void updateInfo(){
		updateTVResoTitre(level);
		updateTVResoLevel(level);
		updateTVEnergy(energyReso[level - 1]);
		updateTVResoDecay(energyReso[level - 1]);
		//updateTVResoRange(rangeReso[level - 1]);
		updateTVCost(costUsedReso[level - 1]);
		updateTVAPFirst(APCreateResoFirst);
		updateTVAPLast(APCreateResoLast);
		updateTVAPOther(APCreateReso);
		updateTVRecycle(recycleReso*level);
		updateIVReso(level);
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
	    
	    
	};
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateBt(BT_RESONATOR);
	}

}

