package com.simultor.ingress;

import com.simulator.ingress.R;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class Portal extends BaseActivity{
	private TextView[] tvPortalLevel = new TextView[ZONE_RESO];
	private TextView[] tvPortalEnergy = new TextView[ZONE_RESO];
	private TextView[] tvPortalDecay = new TextView[ZONE_RESO];
	private TextView[] tvPortalRange = new TextView[ZONE_RESO];
	private TextView[] tvPortalReso = new TextView[ZONE_RESO];
	private TextView[] tvPortalPlayer = new TextView[ZONE_RESO];
	
	protected Spannable spannableLevel_1;
	protected Spannable spannableLevel_2;
	protected Spannable spannableEnergy_1;
	protected Spannable spannableEnergy_2;
	protected Spannable spannableDecay_1;
	protected Spannable spannableDecay_2;
	protected Spannable spannableRange_1;
	protected Spannable spannableRange_2;
	protected Spannable spannableReso_1;
	protected Spannable spannableReso_2;
	protected Spannable spannablePlayer_1;
	protected Spannable spannablePlayer_2;
	
	private int level = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.portal);
		
		for (int Cpt = 0; Cpt < ZONE_RESO; Cpt++){
			//level
			level= Cpt + 1;
			final String strLevel = "tvPortalLevel_" + String.valueOf(Cpt);
			int resIdLevel = getResources().getIdentifier(strLevel, "id", getPackageName());
			tvPortalLevel[Cpt] = (TextView)findViewById(resIdLevel);
			spannableLevel_1 = new SpannableString(getResources().getString(R.string.portal_level) + " ");  
			spannableLevel_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, spannableLevel_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalLevel[Cpt].setText(spannableLevel_1);
			spannableLevel_2 = new SpannableString(String.valueOf(level));  
			spannableLevel_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, spannableLevel_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalLevel[Cpt].append(spannableLevel_2);
			
			//energy
			final String strEnergy = "tvPortalEnergy_" + String.valueOf(Cpt);
			int resIdEnergy = getResources().getIdentifier(strEnergy, "id", getPackageName());
			tvPortalEnergy[Cpt] = (TextView)findViewById(resIdEnergy);
			spannableEnergy_1 = new SpannableString(getResources().getString(R.string.portal_energy) + " ");  
			spannableEnergy_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, spannableEnergy_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalEnergy[Cpt].setText(spannableEnergy_1);
			spannableEnergy_2 = new SpannableString(String.valueOf(energyReso[level - 1]*8) + " k");  
			spannableEnergy_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, spannableEnergy_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalEnergy[Cpt].append(spannableEnergy_2);
			
			//decay
			double decay = energyReso[level - 1]*0.15*8;
			final String strDecay = "tvPortalDecay_" + String.valueOf(Cpt);
			int resIdDecay = getResources().getIdentifier(strDecay, "id", getPackageName());
			tvPortalDecay[Cpt] = (TextView)findViewById(resIdDecay);
			spannableDecay_1 = new SpannableString(getResources().getString(R.string.portal_decay) + " ");  
			spannableDecay_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, spannableDecay_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalDecay[Cpt].setText(spannableDecay_1);
			spannableDecay_2 = new SpannableString(distFormatter.format(decay) + " k");  
			spannableDecay_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, spannableDecay_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalDecay[Cpt].append(spannableDecay_2);
			
			//range
			double range = rangeReso[level - 1];
			range = range /1000;
			final String strRange = "tvPortalRange_" + String.valueOf(Cpt);
			int resIdRange = getResources().getIdentifier(strRange, "id", getPackageName());
			tvPortalRange[Cpt] = (TextView)findViewById(resIdRange);
			spannableRange_1 = new SpannableString(getResources().getString(R.string.portal_range) + " ");  
			spannableRange_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, spannableRange_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalRange[Cpt].setText(spannableRange_1);
			spannableRange_2 = new SpannableString(distFormatter.format(range) + " km");  
			spannableRange_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, spannableRange_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalRange[Cpt].append(spannableRange_2);
			
			//max reso
			final String strMax = "tvPortalReso_" + String.valueOf(Cpt);
			int resIdReso = getResources().getIdentifier(strMax, "id", getPackageName());
			tvPortalReso[Cpt] = (TextView)findViewById(resIdReso);
			spannableReso_1 = new SpannableString(getResources().getString(R.string.portal_reso) + " ");  
			spannableReso_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, spannableReso_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalReso[Cpt].setText(spannableReso_1);
			spannableReso_2 = new SpannableString(String.valueOf(maxReso[level - 1]));  
			spannableReso_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, spannableReso_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalReso[Cpt].append(spannableReso_2);
			
			//max player
			final String strPlayer = "tvPortalPlayer_" + String.valueOf(Cpt);
			int resIdPlayer = getResources().getIdentifier(strPlayer, "id", getPackageName());
			tvPortalPlayer[Cpt] = (TextView)findViewById(resIdPlayer);
			spannablePlayer_1 = new SpannableString(getResources().getString(R.string.portal_player) + " ");  
			spannablePlayer_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, spannablePlayer_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalPlayer[Cpt].setText(spannablePlayer_1);
			spannablePlayer_2 = new SpannableString(String.valueOf(maxPlayer[level - 1]));  
			spannablePlayer_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, spannablePlayer_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tvPortalPlayer[Cpt].append(spannablePlayer_2);
		}
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateBt(BT_PORTAL);
	}

}