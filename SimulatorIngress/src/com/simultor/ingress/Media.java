package com.simultor.ingress;

import com.simulator.ingress.R;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class Media extends BaseActivity{
	private TextView tvMediaInfo;
	private TextView tvMediaRecycle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media);
		tvMediaInfo = (TextView)findViewById(R.id.tvMediaInfo);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.media_info) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvMediaInfo.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + getResources().getString(R.string.media_info_detail));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvMediaInfo.append(WordtoSpanPart_2);
		
		
		tvMediaRecycle = (TextView)findViewById(R.id.tvMediaRecycle);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.media_recycle) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvMediaRecycle.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + getResources().getString(R.string.media_recycle_detail));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvMediaRecycle.append(WordtoSpanPart_2);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateBt(BT_MEDIA);
	}

}
