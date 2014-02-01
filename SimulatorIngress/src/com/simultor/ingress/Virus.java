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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Virus extends BaseActivity{
	private ImageView btLeft;
	private ImageView btRight;
	private ImageView ivVirus;
	private TextView tvVirusTitre;
	private TextView tvVirusInfoDamage;
	private TextView tvVirusInfoFreq;
	private TextView tvVirusInfoCost;
	private TextView tvVirusInfoRecycle;
	private LinearLayout llVirusImage;
	
	private GestureDetector gestureDetector;
	private int posGesture = 1;
	private String virus = VIRUS_JARVIS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.virus);
		
		btLeft = (ImageView)findViewById(R.id.btVirusLeft);
		btRight = (ImageView)findViewById(R.id.btVirusRight);
		ivVirus = (ImageView)findViewById(R.id.ivVirus);
		tvVirusTitre = (TextView)findViewById(R.id.tvVirusTitre);
		tvVirusInfoDamage = (TextView)findViewById(R.id.tvVirusDamage);
		tvVirusInfoFreq = (TextView)findViewById(R.id.tvVirusFreq);
		tvVirusInfoCost = (TextView)findViewById(R.id.tvVirusCost);
		tvVirusInfoRecycle = (TextView)findViewById(R.id.tvVirusRecycle);
		
		btLeft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (virus.equalsIgnoreCase(VIRUS_JARVIS)){
					virus = VIRUS_ADA;
				}else{
					virus = VIRUS_JARVIS;
				}
				updateInfo();
				
				
			}
		});
		

		
		btRight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (virus.equalsIgnoreCase(VIRUS_JARVIS)){
					virus = VIRUS_ADA;
				}else{
					virus = VIRUS_JARVIS;
				}
				updateInfo();
			}
		});
		
		gestureDetector = new GestureDetector(new GestureListener());
		llVirusImage = (LinearLayout)findViewById(R.id.llVirusImage);
		llVirusImage.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;

			}
		});
		
		ivVirus.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;

			}
		});
		
		
		updateInfo();
		
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	    HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.svMenuHoriz);
	    Button button = (Button) findViewById(R.id.btVirus);
	    int x, y;
	    x = button.getLeft();
	    y = button.getTop();
	    hsv.scrollTo(x, y);
	}
	
	private void updateInfo(){
		updateTVVirusTitre(virus);
		updateIVVirus(virus);
		updateTVVirusDamage(virus);
		updateTVVirusFreq();
		updateTVVirusCost();
		updateTVVirusRecycle();
	}
	
	private void updateTVVirusTitre(String virus){
		WordtoSpanPart_1 = new SpannableString("");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvVirusTitre.setText(WordtoSpanPart_1);
		if (virus.equalsIgnoreCase(VIRUS_ADA)){
			WordtoSpanPart_2 = new SpannableString(" " + getResources().getString(R.string.virus_ada) );
		}else{
			WordtoSpanPart_2 = new SpannableString(" " + getResources().getString(R.string.virus_jarvis) );
		}
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvVirusTitre.append(WordtoSpanPart_2);
	}
	
	private void updateTVVirusDamage(String virus){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.virus_damage) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvVirusInfoDamage.setText(WordtoSpanPart_1);
		if (virus.equalsIgnoreCase(VIRUS_ADA)){
			WordtoSpanPart_2 = new SpannableString(" " + getResources().getString(R.string.virus_damage_ada) );
		}else{
			WordtoSpanPart_2 = new SpannableString(" " + getResources().getString(R.string.virus_damage_jarvis) );
		}
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvVirusInfoDamage.append(WordtoSpanPart_2);
	}
	
	private void updateIVVirus(String virus){
		String strView = virus + "_medium";
		int resIdView = getResources().getIdentifier(strView, "drawable", getPackageName());
		ivVirus.setBackgroundDrawable(getResources().getDrawable(resIdView));
	}
	
	private void updateTVVirusFreq(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.virus_freq) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvVirusInfoFreq.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + getResources().getString(R.string.very_rare));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvVirusInfoFreq.append(WordtoSpanPart_2);
	}
	
	private void updateTVVirusCost(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.virus_cost) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvVirusInfoCost.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + getResources().getString(R.string.virus_cost_detail));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvVirusInfoCost.append(WordtoSpanPart_2);
	}
	
	private void updateTVVirusRecycle(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.virus_recyle) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvVirusInfoRecycle.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(100) + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvVirusInfoRecycle.append(WordtoSpanPart_2);
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
	    	if (virus.equalsIgnoreCase(VIRUS_JARVIS)){
				virus = VIRUS_ADA;
			}else{
				virus = VIRUS_JARVIS;
			}
			updateInfo();
			
	    }

	    public void onSwipeLeft() {
	    	if (fgDebug){Log.d(TAG, "onSwipeLeft");};
	    	if (virus.equalsIgnoreCase(VIRUS_JARVIS)){
				virus = VIRUS_ADA;
			}else{
				virus = VIRUS_JARVIS;
			}
			updateInfo();
			
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
		updateBt(BT_VIRUS);
	}

}