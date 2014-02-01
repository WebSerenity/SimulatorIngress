package com.simultor.ingress;

import com.simulator.ingress.R;
import com.simulator.element.IngressMod;

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

public class Mod extends BaseActivity{
	private ImageView btLeft;
	private ImageView btRight;
	private ImageView ivMod;
	private TextView tvModTitre;
	private TextView tvModFreq;
	private TextView tvModInfo;
	private TextView tvModAbsorption;
	private TextView tvModRange;
	private TextView tvModCooldown;
	private TextView tvModHack;
	private TextView tvModForceAmp;
	private TextView tvModTurret;
	private TextView tvModCost;
	private TextView tvModRecycle;
	
	private LinearLayout llModImage;
	
	private int pos = 0;
	private GestureDetector gestureDetector;
	private int posGesture = 1;
	//private String[] listMod = new String[NBR_MOD];
	private IngressMod[] ingressMod = new IngressMod[NBR_MOD];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mod);
		
		btLeft = (ImageView)findViewById(R.id.btModLeft);
		btRight = (ImageView)findViewById(R.id.btModRight);
		ivMod = (ImageView)findViewById(R.id.ivMod);
		tvModTitre = (TextView)findViewById(R.id.tvModTitre);
		tvModFreq = (TextView)findViewById(R.id.tvModFreq);
		tvModInfo = (TextView)findViewById(R.id.tvModInfo);
		tvModAbsorption = (TextView)findViewById(R.id.tvModAbsorption);
		tvModRange = (TextView)findViewById(R.id.tvModRange);
		tvModCooldown = (TextView)findViewById(R.id.tvModCooldown);
		tvModHack = (TextView)findViewById(R.id.tvModHack);
		tvModForceAmp = (TextView)findViewById(R.id.tvModForceAmp);
		tvModTurret = (TextView)findViewById(R.id.tvModTurret);
		tvModCost = (TextView)findViewById(R.id.tvModCost);
		tvModRecycle = (TextView)findViewById(R.id.tvModRecycle);
		
		btLeft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pos--;
				if (pos < 0){
					pos = NBR_MOD - 1;
				}
				updateInfo();
				if (fgDebug){Log.d(TAG, "pos = " + pos);};
				
			}
		});
		

		
		btRight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pos++;
				if (pos > NBR_MOD - 1){
					pos = 0;
				}
				updateInfo();
				if (fgDebug){Log.d(TAG, "pos = " + pos);};
			}
		});
		
		gestureDetector = new GestureDetector(new GestureListener());
		llModImage = (LinearLayout)findViewById(R.id.llModImage);
		llModImage.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;

			}
		});
		
		ivMod.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;

			}
		});
		
		initIngressMod();
		updateInfo();
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	    HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.svMenuHoriz);
	    Button button = (Button) findViewById(R.id.btMod);
	    int x, y;
	    x = button.getLeft();
	    y = button.getTop();
	    hsv.scrollTo(x, y);
	}
	
	private void updateInfo(){
		updateTVModTitre();
		updateIVMod();
		updateTVFreq();
		updateTVInfo();
		updateTVAbsorption();
		updateTVRange();
		updateTVCooldown();
		updateTVHack();
		updateTVForceAmp();
		updateTVTurret();
		updateTVCost();
		updateTVRecycle();
		
	}
	
	private void updateTVModTitre(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.mod_titre) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModTitre.setText("");
		WordtoSpanPart_2 = new SpannableString(" " + ingressMod[pos].getType());  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModTitre.append(WordtoSpanPart_2);
	}
	
	private void updateIVMod(){
		ivMod.setBackgroundDrawable(ingressMod[pos].getDrawable());
	}
	
	private void updateTVFreq(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.mod_freq) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModFreq.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + ingressMod[pos].getCat());  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModFreq.append(WordtoSpanPart_2);
	}
	
	private void updateTVInfo(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.mod_info) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModInfo.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + ingressMod[pos].getInfo());  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModInfo.append(WordtoSpanPart_2);
	}
	
	private void updateTVAbsorption(){
		String strAbsorption = distFormatter.format(ingressMod[pos].getAbsorption());
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.mod_absorption) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModAbsorption.setText(WordtoSpanPart_1);
		if ( ingressMod[pos].getAbsorption() > 0.0){
			WordtoSpanPart_2 = new SpannableString(" + " + strAbsorption + " %");  
		}else{
			WordtoSpanPart_2 = new SpannableString(" " + strAbsorption + " %");  
		}
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModAbsorption.append(WordtoSpanPart_2);
	}
	
	private void updateTVRange(){
		String strRange = distFormatter.format(ingressMod[pos].getRange());
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.mod_range) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModRange.setText(WordtoSpanPart_1);
		if (ingressMod[pos].getRange() > 0.0){
			WordtoSpanPart_2 = new SpannableString(" + " + strRange + " % " + getResources().getString(R.string.mod_change));  
		}else{
			WordtoSpanPart_2 = new SpannableString(" " + strRange + " % ");
		}
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModRange.append(WordtoSpanPart_2);
	}
	
	private void updateTVCooldown(){
		String strCooldown = distFormatter.format(ingressMod[pos].getCooldown());
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.mod_cooldown) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModCooldown.setText(WordtoSpanPart_1);
		if (ingressMod[pos].getCooldown() > 0.0){
			WordtoSpanPart_2 = new SpannableString(" - " + strCooldown + " % " + getResources().getString(R.string.mod_change));  
		}else{
			WordtoSpanPart_2 = new SpannableString(" " + strCooldown + " % ");
		}
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModCooldown.append(WordtoSpanPart_2);
	}
	
	private void updateTVHack(){
		String strHack = String.valueOf(ingressMod[pos].getHack());
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.mod_hack) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModHack.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" + " + strHack); 
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModHack.append(WordtoSpanPart_2);
	}
	
	private void updateTVForceAmp(){
		String strForceAmp = distFormatter.format(ingressMod[pos].getForceAmp());
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.mod_force_amp) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModForceAmp.setText(WordtoSpanPart_1);
		if (ingressMod[pos].getForceAmp() > 0.0){
			WordtoSpanPart_2 = new SpannableString(" + " + strForceAmp + " % " + getResources().getString(R.string.mod_change));
		}else{
			WordtoSpanPart_2 = new SpannableString(" " + strForceAmp + " % ");
		}
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModForceAmp.append(WordtoSpanPart_2);
	}
	
	
	private void updateTVTurret(){
		String strTurret = distFormatter.format(ingressMod[pos].getTurret());
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.mod_turret) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModTurret.setText(WordtoSpanPart_1);
		if (ingressMod[pos].getTurret() > 0.0){
			WordtoSpanPart_2 = new SpannableString(" + " + strTurret + " % " + getResources().getString(R.string.mod_change));  
		}else{
			WordtoSpanPart_2 = new SpannableString(" " + strTurret + " % ");
		}
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModTurret.append(WordtoSpanPart_2);
	}
	
	private void updateTVCost(){
		String strCost = String.valueOf(ingressMod[pos].getCost());
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.mod_cost) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModCost.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + strCost + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModCost.append(WordtoSpanPart_2);
	}
	
	private void updateTVRecycle(){
		String strRecycle = String.valueOf(ingressMod[pos].getRecycle());
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.mod_recycle) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModRecycle.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + strRecycle + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvModRecycle.append(WordtoSpanPart_2);
	}
	
	private void initIngressMod(){	
		
		ingressMod[0] = new IngressMod();
		ingressMod[0].setType(MOD_TYPE_PORTAL_SHIELD);
		ingressMod[0].setCat(COMMON);
		ingressMod[0].setDrawable(getResources().getDrawable(R.drawable.portal_shield_common_medium));
		ingressMod[0].setInfo(getResources().getString(R.string.mod_info_shield));
		ingressMod[0].setAbsorption(10.0);
		ingressMod[0].setRange(0.0);
		ingressMod[0].setCooldown(0.0);
		ingressMod[0].setHack(0);
		ingressMod[0].setForceAmp(0.0);
		ingressMod[0].setTurret(0.0);
		ingressMod[0].setCost(400);
		ingressMod[0].setRecycle(40);
		ingressMod[0].setLinkAmp(0.0);
		
		ingressMod[1] = new IngressMod();
		ingressMod[1].setType(MOD_TYPE_PORTAL_SHIELD);
		ingressMod[1].setCat(RARE);
		ingressMod[1].setDrawable(getResources().getDrawable(R.drawable.portal_shield_rare_medium));
		ingressMod[1].setInfo(getResources().getString(R.string.mod_info_shield));
		ingressMod[1].setAbsorption(20.0);
		ingressMod[1].setRange(0.0);
		ingressMod[1].setCooldown(0.0);
		ingressMod[1].setHack(0);
		ingressMod[1].setForceAmp(0.0);
		ingressMod[1].setTurret(0.0);
		ingressMod[1].setCost(800);
		ingressMod[1].setRecycle(80);
		ingressMod[1].setLinkAmp(0.0);
		
		ingressMod[2] = new IngressMod();
		ingressMod[2].setType(MOD_TYPE_PORTAL_SHIELD);
		ingressMod[2].setCat(VERY_RARE);
		ingressMod[2].setDrawable(getResources().getDrawable(R.drawable.portal_shield_very_rare_medium));
		ingressMod[2].setInfo(getResources().getString(R.string.mod_info_shield));
		ingressMod[2].setAbsorption(30.0);
		ingressMod[2].setRange(0.0);
		ingressMod[2].setCooldown(0.0);
		ingressMod[2].setHack(0);
		ingressMod[2].setForceAmp(0.0);
		ingressMod[2].setTurret(0.0);
		ingressMod[2].setCost(1000);
		ingressMod[2].setRecycle(100);
		ingressMod[2].setLinkAmp(0.0);
		
		ingressMod[3] = new IngressMod();
		ingressMod[3].setType(MOD_TYPE_LINK_AMP);
		ingressMod[3].setCat(RARE);
		ingressMod[3].setDrawable(getResources().getDrawable(R.drawable.link_amp_rare_medium));
		ingressMod[3].setInfo(getResources().getString(R.string.mod_info_link_amp));
		ingressMod[3].setAbsorption(0.0);
		ingressMod[3].setRange(100.0);
		ingressMod[3].setCooldown(0.0);
		ingressMod[3].setHack(0);
		ingressMod[3].setForceAmp(0.0);
		ingressMod[3].setTurret(0.0);
		ingressMod[3].setCost(800);
		ingressMod[3].setRecycle(80);
		ingressMod[3].setLinkAmp(100.0);
		
		ingressMod[4] = new IngressMod();
		ingressMod[4].setType(MOD_TYPE_HEAT_SINK);
		ingressMod[4].setCat(COMMON);
		ingressMod[4].setDrawable(getResources().getDrawable(R.drawable.heat_sink_common_medium));
		ingressMod[4].setInfo(getResources().getString(R.string.mod_info_heat_sink));
		ingressMod[4].setAbsorption(0.0);
		ingressMod[4].setRange(0.0);
		ingressMod[4].setCooldown(20.0);
		ingressMod[4].setHack(0);
		ingressMod[4].setForceAmp(0.0);
		ingressMod[4].setTurret(0.0);
		ingressMod[4].setCost(400);
		ingressMod[4].setRecycle(40);
		ingressMod[4].setLinkAmp(0.0);
		
		ingressMod[5] = new IngressMod();
		ingressMod[5].setType(MOD_TYPE_HEAT_SINK);
		ingressMod[5].setCat(RARE);
		ingressMod[5].setDrawable(getResources().getDrawable(R.drawable.heat_sink_rare_medium));
		ingressMod[5].setInfo(getResources().getString(R.string.mod_info_heat_sink));
		ingressMod[5].setAbsorption(0.0);
		ingressMod[5].setRange(0.0);
		ingressMod[5].setCooldown(50.0);
		ingressMod[5].setHack(0);
		ingressMod[5].setForceAmp(0.0);
		ingressMod[5].setTurret(0.0);
		ingressMod[5].setCost(800);
		ingressMod[5].setRecycle(80);
		ingressMod[5].setLinkAmp(0.0);
		
		ingressMod[6] = new IngressMod();
		ingressMod[6].setType(MOD_TYPE_HEAT_SINK);
		ingressMod[6].setCat(VERY_RARE);
		ingressMod[6].setDrawable(getResources().getDrawable(R.drawable.heat_sink_very_rare_medium));
		ingressMod[6].setInfo(getResources().getString(R.string.mod_info_heat_sink));
		ingressMod[6].setAbsorption(0.0);
		ingressMod[6].setRange(0.0);
		ingressMod[6].setCooldown(70.0);
		ingressMod[6].setHack(0);
		ingressMod[6].setForceAmp(0.0);
		ingressMod[6].setTurret(0.0);
		ingressMod[6].setCost(1000);
		ingressMod[6].setRecycle(100);
		ingressMod[6].setLinkAmp(0.0);
		
		ingressMod[7] = new IngressMod();
		ingressMod[7].setType(MOD_TYPE_MULTI_HACK);
		ingressMod[7].setCat(COMMON);
		ingressMod[7].setDrawable(getResources().getDrawable(R.drawable.multi_hack_common_medium));
		ingressMod[7].setInfo(getResources().getString(R.string.mod_info_multi_hack));
		ingressMod[7].setAbsorption(0.0);
		ingressMod[7].setRange(0.0);
		ingressMod[7].setCooldown(0.0);
		ingressMod[7].setHack(4);
		ingressMod[7].setForceAmp(0.0);
		ingressMod[7].setTurret(0.0);
		ingressMod[7].setCost(400);
		ingressMod[7].setRecycle(40);
		ingressMod[7].setLinkAmp(0.0);
		
		ingressMod[8] = new IngressMod();
		ingressMod[8].setType(MOD_TYPE_MULTI_HACK);
		ingressMod[8].setCat(RARE);
		ingressMod[8].setDrawable(getResources().getDrawable(R.drawable.multi_hack_rare_medium));
		ingressMod[8].setInfo(getResources().getString(R.string.mod_info_multi_hack));
		ingressMod[8].setAbsorption(0.0);
		ingressMod[8].setRange(0.0);
		ingressMod[8].setCooldown(0.0);
		ingressMod[8].setHack(8);
		ingressMod[8].setForceAmp(0.0);
		ingressMod[8].setTurret(0.0);
		ingressMod[8].setCost(800);
		ingressMod[8].setRecycle(80);
		ingressMod[8].setLinkAmp(0.0);
		
		ingressMod[9] = new IngressMod();
		ingressMod[9].setType(MOD_TYPE_MULTI_HACK);
		ingressMod[9].setCat(VERY_RARE);
		ingressMod[9].setDrawable(getResources().getDrawable(R.drawable.multi_hack_very_rare_medium));
		ingressMod[9].setInfo(getResources().getString(R.string.mod_info_multi_hack));
		ingressMod[9].setAbsorption(0.0);
		ingressMod[9].setRange(0.0);
		ingressMod[9].setCooldown(0.0);
		ingressMod[9].setHack(12);
		ingressMod[9].setForceAmp(0.0);
		ingressMod[9].setTurret(0.0);
		ingressMod[9].setCost(1000);
		ingressMod[9].setRecycle(100);
		ingressMod[9].setLinkAmp(0.0);
		
		ingressMod[10] = new IngressMod();
		ingressMod[10].setType(MOD_TYPE_FORCE_AMP);
		ingressMod[10].setCat(RARE);
		ingressMod[10].setDrawable(getResources().getDrawable(R.drawable.force_amp_rare_medium));
		ingressMod[10].setInfo(getResources().getString(R.string.mod_info_force_amp));
		ingressMod[10].setAbsorption(0.0);
		ingressMod[10].setRange(0.0);
		ingressMod[10].setCooldown(0.0);
		ingressMod[10].setHack(0);
		ingressMod[10].setForceAmp(100.0);
		ingressMod[10].setTurret(0.0);
		ingressMod[10].setCost(800);
		ingressMod[10].setRecycle(80);
		ingressMod[10].setLinkAmp(0.0);
		
		ingressMod[11] = new IngressMod();
		ingressMod[11].setType(MOD_TYPE_TURRET);
		ingressMod[11].setCat(RARE);
		ingressMod[11].setDrawable(getResources().getDrawable(R.drawable.turet_rare_medium));
		ingressMod[11].setInfo(getResources().getString(R.string.mod_info_turret));
		ingressMod[11].setAbsorption(0.0);
		ingressMod[11].setRange(0.0);
		ingressMod[11].setCooldown(0.0);
		ingressMod[11].setHack(0);
		ingressMod[11].setForceAmp(0.0);
		ingressMod[11].setTurret(100.0);
		ingressMod[11].setCost(800);
		ingressMod[11].setRecycle(80);
		ingressMod[11].setLinkAmp(0.0);
		
		
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
	    	pos++;
			if (pos > 8){
				pos = 1;
			}
			updateInfo();
			if (fgDebug){Log.d(TAG, "pos = " + pos);};
	    }

	    public void onSwipeLeft() {
	    	if (fgDebug){Log.d(TAG, "onSwipeLeft");};
	    	pos--;
			if (pos < 1){
				pos = pos;
			}
			updateInfo();
			if (fgDebug){Log.d(TAG, "pos = " + pos);};
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
		updateBt(BT_MOD);
	}

}
