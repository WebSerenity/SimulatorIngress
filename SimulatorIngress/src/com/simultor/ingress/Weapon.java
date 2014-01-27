package com.simultor.ingress;


import com.simulator.ingress.R;
import com.simulator.element.IngressXmpBuster;
import com.simulator.element.IngressXmpZone;

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

public class Weapon extends BaseActivity{
	private ImageView btLeft;
	private ImageView btRight;
	private ImageView ivWeapon;
	private TextView tvWeaponTitre;
	private TextView tvWeaponInfoLevel;
	private TextView tvWeaponInfoDamage;
	private TextView tvWeaponInfoRange;
	private TextView[] tvWeaponZone = new TextView[5];
	
	private TextView tvWeaponInfoCost;
	private TextView tvWeaponInfoRecycle;
	private LinearLayout llWeaponImage;
	
	private int level = 1;
	private GestureDetector gestureDetector;
	private int posGesture = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weapon);
		btLeft = (ImageView)findViewById(R.id.btWeaponLeft);
		btRight = (ImageView)findViewById(R.id.btWeaponRight);
		ivWeapon = (ImageView)findViewById(R.id.ivWeapon);
		tvWeaponTitre = (TextView)findViewById(R.id.tvWeaponTitre);
		tvWeaponInfoLevel = (TextView)findViewById(R.id.tvWeaponInfoLevel);
		tvWeaponInfoDamage = (TextView)findViewById(R.id.tvWeaponInfoDamage);
		tvWeaponInfoRange = (TextView)findViewById(R.id.tvWeaponInfoRange);
		tvWeaponZone[0] = (TextView)findViewById(R.id.tvWeaponZone1);
		tvWeaponZone[1] = (TextView)findViewById(R.id.tvWeaponZone2);
		tvWeaponZone[2] = (TextView)findViewById(R.id.tvWeaponZone3);
		tvWeaponZone[3] = (TextView)findViewById(R.id.tvWeaponZone4);
		tvWeaponZone[4] = (TextView)findViewById(R.id.tvWeaponZone5);
		tvWeaponInfoCost = (TextView)findViewById(R.id.tvWeaponInfoCost);
		tvWeaponInfoRecycle = (TextView)findViewById(R.id.tvWeaponInfoRecycle);
		
		
		
		
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
		llWeaponImage = (LinearLayout)findViewById(R.id.llWeaponImage);
		llWeaponImage.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;

			}
		});
		
		ivWeapon.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;

			}
		});
		
		
		updateInfo();
		
	}
	
	private void updateInfo(){
		updateTVWeaponTitre(level);
		updateTVWeaponLevel(level);
		updateIVWeapon(level);
		updateTVWeaponDamage(level);
		updateTVWeaponRange(level);
		updateTVWeaponCost(level);
		updateTVWeaponRecycle(level);
		for (int Cpt = 0; Cpt < tvWeaponZone.length; Cpt++){
			if (fgDebug){Log.d(TAG, "Cpt = " + Cpt);};
			updateTVZone(Cpt);
		}

	}
	
	private void updateTVWeaponTitre(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.weapon_titre) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponTitre.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(level));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponTitre.append(WordtoSpanPart_2);
	}
	
	private void updateIVWeapon(int level){
		String strView = "weapon_medium_" + String.valueOf(level);
		int resIdView = getResources().getIdentifier(strView, "drawable", getPackageName());
		ivWeapon.setBackgroundDrawable(getResources().getDrawable(resIdView));
	}
	
	private void updateTVWeaponLevel(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.weapon_level) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponInfoLevel.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(level));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponInfoLevel.append(WordtoSpanPart_2);
	}
	
	private void updateTVWeaponDamage(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.weapon_damage) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponInfoDamage.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(xmpDamage[level - 1]) + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponInfoDamage.append(WordtoSpanPart_2);
	}
	
	private void updateTVWeaponRange(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.weapon_range) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponInfoRange.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(xmpRange[level - 1]) + " m");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponInfoRange.append(WordtoSpanPart_2);
	}
	
	private void updateTVWeaponCost(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.weapon_cost) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponInfoCost.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(xmpCost[level - 1]) + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponInfoCost.append(WordtoSpanPart_2);
	}
	
	private void updateTVWeaponRecycle(int level){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.weapon_recycle) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponInfoRecycle.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(level*20) + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponInfoRecycle.append(WordtoSpanPart_2);
	}
	
	private void updateTVZone(int cpt){
		if (fgDebug){Log.d(TAG, "cpr = " + cpt);};
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.weapon_zone) + " " + String.valueOf(cpt + 1) + " : ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponZone[cpt].setText(WordtoSpanPart_1);
		
		IngressXmpBuster ingressXmpBuster = listIngressXmpBuster.get(level - 1);
		if (fgDebug){Log.d(TAG, "damage xmp = " + ingressXmpBuster.getDamage());};
		IngressXmpZone xmpZone = ingressXmpBuster.getIngressXmpZone(cpt);
		String zone = String.valueOf(xmpZone.getDamage()) + " XM between ";
		zone = zone + String.valueOf(xmpZone.getDeb()) + " m and ";
		zone = zone + String.valueOf(xmpZone.getFin()) + " m";
		
		WordtoSpanPart_2 = new SpannableString(" " + zone);  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWeaponZone[cpt].append(WordtoSpanPart_2);
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
		super.onResume();
		updateBt(BT_WEAPON);
	}

}
