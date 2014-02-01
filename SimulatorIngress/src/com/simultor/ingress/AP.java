package com.simultor.ingress;

import com.simulator.ingress.R;

import android.os.Bundle;
import android.widget.Button;
import android.widget.HorizontalScrollView;

public class AP extends BaseActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ap);
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	    HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.svMenuHoriz);
	    Button button = (Button) findViewById(R.id.btAP);
	    int x, y;
	    x = button.getLeft();
	    y = button.getTop();
	    hsv.scrollTo(x, y);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateBt(BT_AP);
	}

}