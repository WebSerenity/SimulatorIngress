package com.simultor.ingress;

import com.simulator.ingress.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class Help extends Activity{
	private static TextView tvHelpVersion;
	private Spannable WordtoSpanPart_1;
	private Spannable WordtoSpanPart_2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		
		tvHelpVersion = (TextView)findViewById(R.id.tvHelpVersion);
		
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.help_version) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvHelpVersion.setText(WordtoSpanPart_1);
		
		WordtoSpanPart_2 = new SpannableString(BaseActivity.VERSION_NUM); 
		
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvHelpVersion.append(WordtoSpanPart_2);
	}

}
