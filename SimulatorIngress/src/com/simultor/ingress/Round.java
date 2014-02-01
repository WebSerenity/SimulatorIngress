package com.simultor.ingress;

import com.simulator.ingress.R;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class Round extends BaseActivity{
	public static String TAG = "Round";
	public static boolean fgDebug = true;
	
	private EditText edActual;
	private EditText edObjectif;
	private Button btCalcul;
	private TextView tvSeq;
	
	private int actual = -1;
	private int objectif = -1;
	private int delta = -1;
	private int nbrHack = 0;
	private int nbrRecharge = 0;
	private String strSeq = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.round);
		if (fgDebug){Log.d(TAG, "start Round");};
		edActual = (EditText)findViewById(R.id.etActual);
		edObjectif = (EditText)findViewById(R.id.etObjectif);
		tvSeq = (TextView)findViewById(R.id.tvRoundSeq);
		tvSeq.setText("");
		
		edActual.setOnEditorActionListener(new OnEditorActionListener() {
	        public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
	        	String strActual = view.getText().toString();
				int taille = strActual.length();
	            if (actionId == EditorInfo.IME_ACTION_NEXT && taille == 0) {
	            	Toast.makeText(getApplicationContext(), getResources().getString(R.string.round_error_actual), Toast.LENGTH_LONG).show();
	                return true;
	            }
	            return false;
	        }
	    });
		
		
		edObjectif.setOnEditorActionListener(new OnEditorActionListener() {
	        public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
	        	String strActual = view.getText().toString();
				int taille = strActual.length();
	            if (actionId == EditorInfo.IME_ACTION_DONE && taille == 0) {
	            	Toast.makeText(getApplicationContext(), getResources().getString(R.string.round_error_objectif), Toast.LENGTH_LONG).show();
	                return true;
	            }
	            btCalcul.setVisibility(View.VISIBLE);
	            return false;
	        }
	    });
		
		
		
		btCalcul = (Button)findViewById(R.id.btRoundCalcul);
		btCalcul.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String strActual = edActual.getText().toString();
				String strObjectif = edObjectif.getText().toString();
				actual = Integer.parseInt(strActual);
				if (fgDebug){Log.d(TAG, "actual = " + actual);};
				objectif = Integer.parseInt(strObjectif);
				if (fgDebug){Log.d(TAG, "objectif = " + objectif);};
				delta = objectif - actual;
				if (fgDebug){Log.d(TAG, "delta = " + delta);};
				String strDelta = String.valueOf(delta);
				int last = Integer.parseInt(strDelta.substring(strDelta.length()-1));
				if (fgDebug){Log.d(TAG, "last = " + last);};
				if (delta <= 0){
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.round_too_small), Toast.LENGTH_LONG).show();
					return;
				}
				strSeq = "";
				calculateSeq(delta, last);
				tvSeq.setText(strSeq);
				
				
			}
		});
		
		btCalcul.setVisibility(View.INVISIBLE);
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	    HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.svMenuHoriz);
	    Button button = (Button) findViewById(R.id.btRound);
	    int x, y;
	    x = button.getLeft();
	    y = button.getTop();
	    hsv.scrollTo(x, y);
	}
	
	private void calculateSeq(int delta, int last){
		boolean fgPossible = false;
		strSeq = getResources().getString(R.string.round_actual) + " "  + String.valueOf(actual);
		if (fgDebug){Log.d(TAG, "delta = " + delta);};
		if (last == 0){
			if (delta > 10){
				fgPossible = true;
				delta = objectif - actual;
				if (fgDebug){Log.d(TAG, "delta = " + delta);};
				calculateHack(delta);
			}
		}
		
		if (last == 1){
			if (delta >= 3 *313){
				fgPossible = true;
				delta = objectif - 3*313 - actual;
				if (fgDebug){Log.d(TAG, "delta = " + delta);};
				calculateHack(delta);
				strSeq = strSeq + "\n + \n" + getResources().getString(R.string.round_create) + " 3 " +getResources().getString(R.string.round_link) + " : 939 ";
			}
		}
		
		if (last == 2){
			if (delta > 4 * 313){
				fgPossible = true;
				delta = objectif - 4*313 - actual;
				if (fgDebug){Log.d(TAG, "delta = " + delta);};
				calculateHack(delta);
				strSeq = strSeq + "\n + \n" + getResources().getString(R.string.round_create) + " 4 " +getResources().getString(R.string.round_link) + " : 1252 ";
			}
		}
		
		if (last == 3){
			if (delta > 313){
				fgPossible = true;
				delta = objectif - 313 - actual;
				if (fgDebug){Log.d(TAG, "delta = " + delta);};
				calculateHack(delta);
				strSeq = strSeq + "\n + \n" + getResources().getString(R.string.round_create) + " 1 " +getResources().getString(R.string.round_link) + " : 313 ";
			}
		}
		
		
		if (last == 4){
			if (delta >= 3*313 + 65){
				fgPossible = true;
				delta = objectif - 3*313 - 65 - actual;
				if (fgDebug){Log.d(TAG, "delta = " + delta);};
				calculateHack(delta);
				strSeq = strSeq + "\n + \n" + getResources().getString(R.string.round_create) + " 3 links (3*313) + " +getResources().getString(R.string.round_mod) + " (65) : 1004 ";
			}
		}
		
		if (last == 5){
			if (delta >= 65){
				fgPossible = true;
				delta = objectif - 65 - actual;
				if (fgDebug){Log.d(TAG, "delta = " + delta);};
				
				calculateHack(delta);
				strSeq = strSeq + "\n + \nSet a mod (1*65) : 65";;
			}
		}
		
		if (last == 6){
			if (delta >= 2*313){
				fgPossible = true;
				delta = objectif - 2*313 - actual;
				if (fgDebug){Log.d(TAG, "delta = " + delta);};
				calculateHack(delta);
				strSeq = strSeq + "\n + \n" + getResources().getString(R.string.round_create) + "  2 " +getResources().getString(R.string.round_link) + " (2*313) : 626";
			}
		}
		
		if (last == 7){
			if (delta >= 4*313 + 65){
				fgPossible = true;
				delta = objectif - 4*313 - 65 - actual;
				if (fgDebug){Log.d(TAG, "delta = " + delta);};
				calculateHack(delta);
				strSeq = strSeq + "\n + \n" + getResources().getString(R.string.round_create) + "  4 " +getResources().getString(R.string.round_link) + " (4*313) : 1252\n+\n" +getResources().getString(R.string.round_mod) + " (1*65) : 65";
			}
		}
		
		if (last == 8){
			if (delta >= 313 + 65){
				fgPossible = true;
				delta = objectif - 313 - 65 - actual;
				if (fgDebug){Log.d(TAG, "delta = " + delta);};
				calculateHack(delta);
				strSeq = strSeq + "\n + \n" + getResources().getString(R.string.round_create) + "  1 " +getResources().getString(R.string.round_link) + " (1*313) : 313\n+\n" +getResources().getString(R.string.round_mod) + " (1*65) : 65";
			}
		}
		
		if (last == 9){
			if (delta >= 3*313){
				fgPossible = true;
				delta = objectif - 3*313 - actual;
				if (fgDebug){Log.d(TAG, "delta = " + delta);};
				calculateHack(delta);
				strSeq = strSeq + "\n + \n" + getResources().getString(R.string.round_create) + "  3 " +getResources().getString(R.string.round_link) + " (3*313) : 939";
			}
		}
		
		if (!fgPossible){
			strSeq = getResources().getString(R.string.round_impossible);
			return;
		}
		
		if (nbrHack > 0){
			strSeq = strSeq + "\n+\n" + getResources().getString(R.string.round_hack) + " (" + String.valueOf(nbrHack) + "*100) : " + String.valueOf(nbrHack*100);
		}
		if (nbrRecharge > 0){
			strSeq =  strSeq + "\n+\n" + getResources().getString(R.string.round_recharge) + " (" + String.valueOf(nbrRecharge) + "*10) : " + String.valueOf(nbrRecharge*10);
		}
		strSeq =  strSeq + "\n= \n" + getResources().getString(R.string.round_objectif) + " " + String.valueOf(objectif);
		
	}
	
	private void calculateHack(int base){
		nbrHack = base / 100;
		if (fgDebug){Log.d(TAG, "nbrHack = " + nbrHack);};
		int reste = base - nbrHack*100;
		if (fgDebug){Log.d(TAG, "reste = " + reste);};
		nbrRecharge = reste/10;
		if (fgDebug){Log.d(TAG, "nbrRecharge = " + nbrRecharge);};
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		updateBt(BT_ROUND);
	}

}
