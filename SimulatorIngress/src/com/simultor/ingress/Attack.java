package com.simultor.ingress;

import com.simulator.ingress.R;

import android.os.Bundle;

public class Attack extends BaseActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attack);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateBt(BT_ATTACK);
	}

}
