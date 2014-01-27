package com.simultor.ingress;

import com.simulator.ingress.R;

import android.os.Bundle;

public class Level extends BaseActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateBt(BT_LEVEL);
	}

}
