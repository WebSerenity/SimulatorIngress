package com.simultor.ingress;

import com.simulator.ingress.R;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class Key extends BaseActivity{
	private TextView tvKeyInfo;
	private TextView tvKeyRecycle;
	private TextView tvKeyPortal;
	private TextView tvKeyPhoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.key);
		tvKeyInfo = (TextView)findViewById(R.id.tvKeyInfo);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.key_info) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvKeyInfo.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + getResources().getString(R.string.key_info_detail));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvKeyInfo.append(WordtoSpanPart_2);
		
		
		tvKeyRecycle = (TextView)findViewById(R.id.tvKeyRecycle);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.key_recycle) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvKeyRecycle.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + getResources().getString(R.string.key_recycle_detail));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvKeyRecycle.append(WordtoSpanPart_2);
		
		tvKeyPortal = (TextView)findViewById(R.id.tvKeyPortal);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.key_portal) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvKeyPortal.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" : " + String.valueOf(APPortalAccepted) + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvKeyPortal.append(WordtoSpanPart_2);
		
		tvKeyPhoto = (TextView)findViewById(R.id.tvKeyPhoto);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.key_photo) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvKeyPhoto.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" : " + String.valueOf(APPictureChange) + " XM");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvKeyPhoto.append(WordtoSpanPart_2);
		
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateBt(BT_PORTAL_KEY);
	}

}
