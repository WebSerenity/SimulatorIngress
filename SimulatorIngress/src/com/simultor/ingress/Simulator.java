package com.simultor.ingress;


import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.simulator.ingress.R;
import com.simulator.element.IngressMod;
import com.simulator.element.IngressReso;
import com.simulator.element.ResultMod;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Simulator extends BaseActivity implements com.simulator.element.AsyncTaskCompleteListener {
	private ImageButton[] ibModUp = new ImageButton[ZONE_MOD];
	private ImageButton[] ibMod = new ImageButton[ZONE_MOD];
	private ImageButton[] ibModDown = new ImageButton[ZONE_MOD];
	private LinearLayout[] llMod = new LinearLayout[ZONE_MOD];
	private TextView[] tvName = new TextView[ZONE_MOD];
	private static int[] posMod = new int[ZONE_MOD];
	
	private TextView[] tvLevel = new TextView[ZONE_RESO];
	private Button[] btReso = new Button[ZONE_RESO];
	private TextView[] tvOrientation = new TextView[ZONE_RESO];
	private TextView tvResoEnergy;
	private TextView tvResoLevel;
	private TextView tvResoLink;
	private TextView tvResoDecay;
	private TextView tvResoField;
	private TextView tvResoRange;
	private TextView tvResoHack;
	private TextView tvResoCooldown;
	//private TextView tvResoHitBonus;
	private TextView tvResoAttackFrequency;
	private TextView tvResoForceAmp;
	private TextView tvResoPortalShield;
	private EditText edLinkNbr;
	private TextView tvLinkOk;
	private EditText edFieldNbr;
	private TextView tvFieldOk;
	private TextView tvGenAbsorption;
	private TextView tvGenEnergy;
	private TextView tvGenXmpBuster;
	private TextView tvGenXMDestroy;
	private TextView tvGenAPDestroy;
	private TextView tvGenAPCreate;
	
	private LinearLayout llGetPortal;
	private TextView tvPortalGet;
	private TextView tvPortalInfo;
	private ProgressBar progressBarGetReso;
	private boolean fgPortal = false;
	private static Simulator activityRef;
	
	
	private TextView[] tvChoixReso = new TextView[ZONE_RESO + 1];
	private TextView[] tvChoixDecay = new TextView[ZONE_RESO + 1];
	
	private GestureDetector gestureDetector;
	
	public static String name = "";
	public static String team = "";
	private IngressMod[] ingressMod = new IngressMod[13];
	public static IngressReso[] ingressReso = new IngressReso[ZONE_RESO];
	
	//Parametre generaux de mise a jour des champs
	private double energy = 0;
	private int level = 0;
	private  double absorption = 0;
	private int nbrField = 0;
	private int nbrLink = 0;
	private double range = 0;
	private int hack = 4;
	private int cooldown = 300;
	private double portalShield = 0.0;
	private double damage = 0.0;
	private int apCreate = 0;
	private int apDestroy = 0;
	private int nbrReso = 0;
	private int nbrMod = 0;
	private double energyLink = 0.0;
	private double energyEquivalent = 0.0;
	private double hitBonus = 0.0;
	private double forceAmp = 0.0;
	private double attackFrequency = 0.0;
	private String strNbrBusterToDestroy = "";
	private String strCostAttackXM = "";
	private double decay = 0.0;
	private int choixDecay = 7;
	
	private PopupWindow popupReso;
	private PopupWindow popupLink;
	private PopupWindow popupField;
	private PopupWindow popupDecay;
	
	private ResultMod[] resultMod = new ResultMod[4];
	
	private double shield = 0;
	private double link = 0;
	
	private LocationManager locationManager;
	private String strLat = "";
	private String strLong = "";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simulator);
		svMenuHoriz = (HorizontalScrollView)findViewById(R.id.svMenuHoriz);
		
		
		
		gestureDetector = new GestureDetector(new GestureListener());
		
		for (int Cpt = 0; Cpt < ZONE_MOD; Cpt++){
			posMod[Cpt] = 0;
		}

		//definition des Mods
		String racine = "";
		int resID = 0;
		for (int Cpt = 0; Cpt < ZONE_MOD; Cpt++){
			racine = "ibModUp";
			final int ref = Cpt;
			final String strModUp = racine + "_" + String.valueOf(Cpt);
			int resIdModUp = getResources().getIdentifier(strModUp, "id", getPackageName());
			//if (fgDebug){Log.d(TAG, "resId = " + resIdModUp);};
			ibModUp[Cpt] = (ImageButton)findViewById(resIdModUp);
			ibModUp[Cpt].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					//if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "click on strModUp = " + strModUp);};
					posMod[ref]++;
					if (posMod[ref] >12){
						posMod[ref] = 0;
					}
					upgradeMod(posMod[ref], ref);
					analyze();
					//analyzeMod();
					
				}
			});
			
			
			racine = "ibMod";
			final String strMod = racine + "_" + String.valueOf(Cpt);
			int resId = getResources().getIdentifier(strMod, "id", getPackageName());
			//if (fgDebug){Log.d(TAG, "resId = " + resId);};
			ibMod[Cpt] = (ImageButton)findViewById(resId);
			
			ibMod[Cpt].setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					gestureDetector.onTouchEvent(event);
					return false;

				}
			});
			

			
			
			racine = "tvName";
			final String strName = racine + "_" + String.valueOf(Cpt);
			int resIdName = getResources().getIdentifier(strName, "id", getPackageName());
			//if (fgDebug){Log.d(TAG, "resId = " + resIdModDown);};
			tvName[Cpt] = (TextView)findViewById(resIdName);
			
			
			
			
			racine = "ibModDown";
			final String strModDown = racine + "_" + String.valueOf(Cpt);
			int resIdModDown = getResources().getIdentifier(strModDown, "id", getPackageName());
			//if (fgDebug){Log.d(TAG, "resId = " + resIdModDown);};
			ibModDown[Cpt] = (ImageButton)findViewById(resIdModDown);
			ibModDown[Cpt].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					//if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "click on strModDown = " + strModDown);};
					posMod[ref]--;
					if (posMod[ref] <0){
						posMod[ref] = 12;
					}
					upgradeMod(posMod[ref], ref);
					analyze();
					//analyzeMod();
					
				}
			});
			
			final String strModLL = "llMod_" + String.valueOf(Cpt);
			int resIdModLL = getResources().getIdentifier(strModLL, "id", getPackageName());
			//if (fgDebug){Log.d(TAG, "resIdModLL = " + resIdModLL);};
			llMod[Cpt] = (LinearLayout)findViewById(resIdModLL);
			
		}
		
		//definition des Reso
		final float scale = getResources().getDisplayMetrics().density;
		int resoWidth = (int) (25 * scale + 0.5f);
		
		for (int Cpt = 0; Cpt < ZONE_RESO; Cpt++){
			final int refReso = Cpt;
			final String strLevel = "tvLevel_" + String.valueOf(Cpt);
			int resIdLevel = getResources().getIdentifier(strLevel, "id", getPackageName());
			tvLevel[Cpt] = (TextView)findViewById(resIdLevel);
			tvLevel[Cpt].setText("L" + String.valueOf(Cpt+1));
			final String strReso = "btReso_" + String.valueOf(Cpt);
			int resIdReso = getResources().getIdentifier(strReso, "id", getPackageName());
			btReso[Cpt] = (Button)findViewById(resIdReso);
			btReso[Cpt].getLayoutParams().width = resoWidth;
			
			final String strColor = "color_" + String.valueOf(Cpt);
			int resIdColor = getResources().getIdentifier(strColor, "color", getPackageName());
			btReso[Cpt].setBackgroundResource(resIdColor);
			btReso[Cpt].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					initiatePopupWindowReso(refReso);
					
				}
			});
			
			final String strOrientation = "tvOrientation_" + String.valueOf(Cpt);
			int resIdOrientation = getResources().getIdentifier(strOrientation, "id", getPackageName());
			tvOrientation[Cpt] = (TextView)findViewById(resIdOrientation);
			
		}
		
		tvResoEnergy = (TextView)findViewById(R.id.tvResoEnergy);
		tvResoDecay = (TextView)findViewById(R.id.tvResoDecay);
		tvResoLevel = (TextView)findViewById(R.id.tvResoLevel);
		tvResoRange = (TextView)findViewById(R.id.tvResoRange);
		tvResoLink = (TextView)findViewById(R.id.tvResoLink);
		tvResoField = (TextView)findViewById(R.id.tvResoField);
		tvResoHack = (TextView)findViewById(R.id.tvResoHack);
		tvResoCooldown = (TextView)findViewById(R.id.tvResoCooldown);
		//tvResoHitBonus = (TextView)findViewById(R.id.tvResoHitBonus);
		tvResoAttackFrequency = (TextView)findViewById(R.id.tvResoAttackFrequency);
		tvResoForceAmp = (TextView)findViewById(R.id.tvResoForceAmp);
		tvResoPortalShield = (TextView)findViewById(R.id.tvResoPortalShield);
		tvGenAbsorption = (TextView)findViewById(R.id.tvGenAbsorption);
		tvGenEnergy = (TextView)findViewById(R.id.tvGenEnergy);
		tvGenXmpBuster = (TextView)findViewById(R.id.tvGenXmpBuster);
		tvGenXMDestroy = (TextView)findViewById(R.id.tvGenXMDestroy);
		tvGenAPDestroy = (TextView)findViewById(R.id.tvGenAPDestroy);
		tvGenAPCreate = (TextView)findViewById(R.id.tvGenAPCreate);
		
		initIngressMod();
		initReso();
		initOther();
		
		tvResoLink.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initiatePopupWindowLink(0);
			}
		});
		
		tvResoDecay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				initiatePopupWindowDecay();
				
			}
		});
		
		tvResoField.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initiatePopupWindowField(0);
				
			}
		});
		
		locationManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
		
		llGetPortal = (LinearLayout)findViewById(R.id.llGetPortal);
		llGetPortal.setVisibility(View.GONE);
		/*
		tvPortalGet = (TextView)findViewById(R.id.tvPortalGet);
		tvPortalInfo = (TextView)findViewById(R.id.tvPortalInfo);
		tvPortalGet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), getResources().getString(R.string.gene_dev), Toast.LENGTH_LONG).show();
				if (!locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
			    	AlertDialog.Builder builder = new AlertDialog.Builder(Simulator.this);
			        builder.setCancelable(true);
			        builder.setTitle(getResources().getString(R.string.gene_info));
			        builder.setInverseBackgroundForced(true);
			        builder.setMessage(getResources().getString(R.string.gps_notavailable));
			        builder.setPositiveButton(getResources().getString(R.string.gene_quit),new DialogInterface.OnClickListener() {
		                @Override
		                public void onClick(DialogInterface dialog,int which) {
		                    dialog.dismiss();
		                }
		            });

			        AlertDialog alert = builder.create();
			        alert.show();
			        if (fgDebug){Log.d(TAG, "GPS inactif");}
			        
			    }else{
			    	Toast.makeText(getApplicationContext(), "under development ...", Toast.LENGTH_LONG).show();
			    	
			    	
			    	tvPortalInfo.setText(getResources().getString(R.string.gps_get_position));
			    	progressBarGetReso.setVisibility(View.VISIBLE);
			    	fgPortal = false;
			    	//detect location
			    	LocationListener locationListener = new LocationListener() {
						
						@Override
						public void onStatusChanged(String provider, int status, Bundle extras) {
						}
						
						@Override
						public void onProviderEnabled(String provider) {
						}
						
						@Override
						public void onProviderDisabled(String provider) {
						}
						
						@Override
						public void onLocationChanged(Location location) {
							if (!fgPortal){
								fgPortal = true;
								double coordLat = location.getLatitude();
								strLat = String.valueOf(coordLat);
								double coordLong = location.getLongitude();
								strLong = String.valueOf(coordLong);
								tvPortalInfo.setText("Lat = " + coordFormatter.format(coordLat) + " Long = " + coordFormatter.format(coordLong));
								if (fgDebug){Log.d(TAG, "strLat = " + strLat);};
								if (fgDebug){Log.d(TAG, "strLong = " + strLong);};
								tvPortalGet.setText(getResources().getString(R.string.gps_get_nearest) + " ...");
								coordLat = coordLat * 1E6;
								coordLong = coordLong * 1E6;
								
								LanceAsync lanceAsync = new LanceAsync(progressBarGetReso, secFormatter.format(coordLat), secFormatter.format(coordLong), URL_PORTAL, Simulator.this);
								lanceAsync.execute();
							}
						}
					}; 
					
					locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);
					
			    }
			}
		});
		
		
		
		progressBarGetReso = (ProgressBar)findViewById(R.id.pbGetReso);
		progressBarGetReso.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.color_bleu), android.graphics.PorterDuff.Mode.MULTIPLY);
		progressBarGetReso.setVisibility(View.INVISIBLE);
		
		*/
		analyze();
		
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	    HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.svMenuHoriz);
	    Button button = (Button) findViewById(R.id.btSimulator);
	    int x, y;
	    x = button.getLeft();
	    y = button.getTop();
	    hsv.scrollTo(x, y);
	}

	private void initiatePopupWindowReso(final int refReso) {
		LayoutInflater inflater = (LayoutInflater) Simulator.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.reso_choix,(ViewGroup) findViewById(R.id.popup_element));
		popupReso = new PopupWindow(layout, (int) (screenWidth*0.5), (int) (screenHeight*0.5), true);
		popupReso.showAtLocation(layout, Gravity.CENTER, 0, 0);
		for (int Cpt = 0; Cpt < ZONE_RESO + 1; Cpt++){
			final int choix = Cpt;
			String strChoixRes = "tvChoixReso_" + String.valueOf(Cpt);
			int resIdChoixReso = getResources().getIdentifier(strChoixRes, "id", getPackageName());
			tvChoixReso[Cpt] = (TextView)layout.findViewById(resIdChoixReso);
			tvChoixReso[Cpt].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//if (fgDebug){Log.d(TAG, "refReso = " + refReso);};
					//if (fgDebug){Log.d(TAG, "choix = " + choix);};
					updateReso(refReso, choix);
					popupReso.dismiss();
				}
			});

		}

	}
	
	private void initiatePopupWindowDecay() {
		LayoutInflater inflater = (LayoutInflater) Simulator.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.decay_choix,(ViewGroup) findViewById(R.id.popup_decay));
		popupDecay = new PopupWindow(layout, (int) (screenWidth*0.5), (int) (screenHeight*0.5), true);
		popupDecay.showAtLocation(layout, Gravity.CENTER, 0, 0);
		
		for (int Cpt = 0; Cpt < NBR_DECAY; Cpt++){
			final int choix = Cpt;
			String strChoixDecay = "tvChoixDecay_" + String.valueOf(Cpt);
			int resIdChoixDecay = getResources().getIdentifier(strChoixDecay, "id", getPackageName());
			tvChoixDecay[Cpt] = (TextView)layout.findViewById(resIdChoixDecay);
			tvChoixDecay[Cpt].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					choixDecay = choix;
					//if (fgDebug){Log.d(TAG, "decay choix click = " + choix);};
					analyze();
					popupDecay.dismiss();
				}
			});
		
		}
		
	}
	
	private void initiatePopupWindowLink(final int refLink) {
		//link = 0;
		LayoutInflater inflater = (LayoutInflater) Simulator.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.link_choix,(ViewGroup) findViewById(R.id.popup_link));
		popupLink = new PopupWindow(layout, (int) (screenWidth*0.5), (int) (screenHeight*0.5), true);
		popupLink.showAtLocation(layout, Gravity.CENTER, 0, 0);
		edLinkNbr = (EditText)layout.findViewById(R.id.etLinkNbr);
		tvLinkOk = (TextView)layout.findViewById(R.id.tvLinkOk);
		tvLinkOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String strLinkNbr = edLinkNbr.getText().toString();
				if (strLinkNbr.isEmpty()){
					strLinkNbr = "0";
				}
				nbrLink = Integer.parseInt(strLinkNbr);
				analyze();
				
				popupLink.dismiss();
			}
		});
		
	}
	
	private void initiatePopupWindowField(final int refField) {
		LayoutInflater inflater = (LayoutInflater) Simulator.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.field_choix,(ViewGroup) findViewById(R.id.popup_field));
		popupField = new PopupWindow(layout, (int) (screenWidth*0.5), (int) (screenHeight*0.5), true);
		popupField.showAtLocation(layout, Gravity.CENTER, 0, 0);
		edFieldNbr = (EditText)layout.findViewById(R.id.etfieldNbr);
		tvFieldOk = (TextView)layout.findViewById(R.id.tvfieldOk);
		tvFieldOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String strFieldNbr = edFieldNbr.getText().toString();
				if (strFieldNbr.isEmpty()){
					strFieldNbr = "0";
				}
				nbrField = Integer.parseInt(strFieldNbr);
				analyze();
				popupField.dismiss();
			}
		});
		
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
	    }

	    public void onSwipeLeft() {
	    	if (fgDebug){Log.d(TAG, "onSwipeLeft");};
	    }

	    public void onSwipeTop() {
	    	if (fgDebug){Log.d(TAG, "onSwipeTop");};
	    }

	    public void onSwipeBottom() {
	    	if (fgDebug){Log.d(TAG, "onSwipeBottom");};
	    }
	    
	    
	};
	
	private void initIngressMod(){
		ingressMod[0] = new IngressMod();
		ingressMod[0].setType("");
		ingressMod[0].setCat("");
		ingressMod[0].setDrawable(getResources().getDrawable(R.drawable.mod_vide_small));
		
		ingressMod[1] = new IngressMod();
		ingressMod[1].setType(MOD_TYPE_PORTAL_SHIELD);
		ingressMod[1].setCat(COMMON);
		ingressMod[1].setDrawable(getResources().getDrawable(R.drawable.portal_shield_common_small));
		
		ingressMod[2] = new IngressMod();
		ingressMod[2].setType(MOD_TYPE_PORTAL_SHIELD);
		ingressMod[2].setCat(RARE);
		ingressMod[2].setDrawable(getResources().getDrawable(R.drawable.portal_shield_rare_small));
		
		ingressMod[3] = new IngressMod();
		ingressMod[3].setType(MOD_TYPE_PORTAL_SHIELD);
		ingressMod[3].setCat(VERY_RARE);
		ingressMod[3].setDrawable(getResources().getDrawable(R.drawable.portal_shield_very_rare_small));
		
		ingressMod[ZONE_MOD] = new IngressMod();
		ingressMod[ZONE_MOD].setType(MOD_TYPE_LINK_AMP);
		ingressMod[ZONE_MOD].setCat(RARE);
		ingressMod[ZONE_MOD].setDrawable(getResources().getDrawable(R.drawable.link_amp_rare_small));
		
		ingressMod[5] = new IngressMod();
		ingressMod[5].setType(MOD_TYPE_HEAT_SINK);
		ingressMod[5].setCat(COMMON);
		ingressMod[5].setDrawable(getResources().getDrawable(R.drawable.heat_sink_common_small));
		
		ingressMod[6] = new IngressMod();
		ingressMod[6].setType(MOD_TYPE_HEAT_SINK);
		ingressMod[6].setCat(RARE);
		ingressMod[6].setDrawable(getResources().getDrawable(R.drawable.heat_sink_rare_small));
		
		ingressMod[7] = new IngressMod();
		ingressMod[7].setType(MOD_TYPE_HEAT_SINK);
		ingressMod[7].setCat(VERY_RARE);
		ingressMod[7].setDrawable(getResources().getDrawable(R.drawable.heat_sink_very_rare_small));
		
		ingressMod[8] = new IngressMod();
		ingressMod[8].setType(MOD_TYPE_MULTI_HACK);
		ingressMod[8].setCat(COMMON);
		ingressMod[8].setDrawable(getResources().getDrawable(R.drawable.multi_hack_common_small));
		
		ingressMod[9] = new IngressMod();
		ingressMod[9].setType(MOD_TYPE_MULTI_HACK);
		ingressMod[9].setCat(RARE);
		ingressMod[9].setDrawable(getResources().getDrawable(R.drawable.multi_hack_rare_small));
		
		ingressMod[10] = new IngressMod();
		ingressMod[10].setType(MOD_TYPE_MULTI_HACK);
		ingressMod[10].setCat(VERY_RARE);
		ingressMod[10].setDrawable(getResources().getDrawable(R.drawable.multi_hack_very_rare_small));
		
		ingressMod[11] = new IngressMod();
		ingressMod[11].setType(MOD_TYPE_FORCE_AMP);
		ingressMod[11].setCat(RARE);
		ingressMod[11].setDrawable(getResources().getDrawable(R.drawable.force_amp_rare_small));
		
		ingressMod[12] = new IngressMod();
		ingressMod[12].setType(MOD_TYPE_TURRET);
		ingressMod[12].setCat(RARE);
		ingressMod[12].setDrawable(getResources().getDrawable(R.drawable.turet_rare_small));
		
		for (int Cpt = 0; Cpt < ZONE_MOD; Cpt++){
			ibMod[Cpt].setBackgroundDrawable(ingressMod[0].getDrawable());
			resultMod[Cpt] = new ResultMod("", "");
		}
		
	}
	
	public void upgradeMod(int pos, int mod){
		ibMod[mod].setBackgroundDrawable(ingressMod[pos].getDrawable());
		String cat = ingressMod[pos].getCat();
		if (cat.equalsIgnoreCase(COMMON)){
			llMod[mod].setBackgroundDrawable(getResources().getDrawable(R.drawable.mod_border_common));
		}
		if (cat.equalsIgnoreCase(RARE)){
			llMod[mod].setBackgroundDrawable(getResources().getDrawable(R.drawable.mod_border_rare));
		}
		if (cat.equalsIgnoreCase(VERY_RARE)){
			llMod[mod].setBackgroundDrawable(getResources().getDrawable(R.drawable.mod_border_very_rare));
		}
		if (cat.equalsIgnoreCase("")){
			llMod[mod].setBackgroundDrawable(getResources().getDrawable(R.drawable.mod_border));
		}
		
		resultMod[mod] = new ResultMod(ingressMod[pos].getType(), ingressMod[pos].getCat());
		
	}
	
	private void initReso(){
		for (int Cpt = 0; Cpt < ZONE_RESO; Cpt++){
			ingressReso[Cpt] = new IngressReso();
			ingressReso[Cpt].setEnergy(energyReso[Cpt]);
			ingressReso[Cpt].setLevel(Cpt);
		}
	}
	
	private void initOther(){
		for (int Cpt = 0; Cpt < ZONE_MOD;Cpt++){
			tvName[Cpt].setText("mod");
		}
	}
	
	private void updateReso(int refReso, int choix){
		//if (fgDebug){Log.d(TAG, "updateReso refReso = " + refReso);};
		//if (fgDebug){Log.d(TAG, "updateReso choix = " + choix);};
		final String strColor = "color_" + String.valueOf(choix);
		int resIdColor = getResources().getIdentifier(strColor, "color", getPackageName());
		btReso[refReso].setBackgroundResource(resIdColor);
		if (choix == ZONE_RESO){
			tvLevel[refReso].setText("");
			ingressReso[refReso].setLevel(0);
			btReso[refReso].setBackgroundColor(Color.BLACK);
		}else{
			tvLevel[refReso].setText("L" + String.valueOf(choix + 1));
			ingressReso[refReso].setLevel(choix);
		}
		if (choix == ZONE_RESO){
			ingressReso[refReso].setEnergy(0);
		}else{
			ingressReso[refReso].setEnergy(energyReso[choix]);
		}
		//if (fgDebug){Log.d(TAG, "ingressReso[refReso].setEnergy = " + ingressReso[refReso].getEnergy());};
		analyze();
	}
	
	
	
	private void updateTVEnergy(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.energy) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoEnergy.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(String.valueOf(energy) + " k");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoEnergy.append(WordtoSpanPart_2);
	}
	
	private void updateTVDecay(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.decay) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoDecay.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(String.valueOf(distFormatter.format(decay *100)) + " %");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoDecay.append(WordtoSpanPart_2);
	}
	
	private void updateTVResoLevel(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.level) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoLevel.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(String.valueOf(level));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoLevel.append(WordtoSpanPart_2);
	}
	
	private void updateTVResoRange(){
		if (fgDebug){Log.d(TAG, "range  = " + range);};
		String unit = "m";
		double rangeAff = range;
		if (rangeAff / 1000.00 > 1){
			rangeAff = rangeAff/1000.00;
			unit = "km";
		}
		String strRange  = distFormatter.format(rangeAff);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.range) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoRange.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(strRange + " " + unit);  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoRange.append(WordtoSpanPart_2);
	}
	
	private void updateTVResoLink(){
		
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.link) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoLink.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(nbrLink));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoLink.append(WordtoSpanPart_2);
	}
	
	private void updateTVResoField(){
		
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.field) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoField.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + String.valueOf(nbrField));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoField.append(WordtoSpanPart_2);
	}
	
	private void updateTVResoHack(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.hack) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoHack.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(String.valueOf(hack));  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoHack.append(WordtoSpanPart_2);
	}
	
	private void updateTVResoCooldown(){
		String strCooldown = distFormatter.format(cooldown);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.cooldown) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoCooldown.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(strCooldown + " sec");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoCooldown.append(WordtoSpanPart_2);
	}
	/*
	private void updateTVResoHitBonus(){
		String strHitBonus = distFormatter.format(hitBonus);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.hit_bonus) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoHitBonus.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" + " + strHitBonus + " %");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoHitBonus.append(WordtoSpanPart_2);
	}
	*/
	private void updateTVResoAttackFrequency(){
		String strAttackFrequency = distFormatter.format(attackFrequency);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.attack_frequency) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoAttackFrequency.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" x " + strAttackFrequency);  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoAttackFrequency.append(WordtoSpanPart_2);
	}
	
	private void updateTVResoForceAmp(){
		String strDamage = distFormatter.format(damage);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.force_amp) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoForceAmp.setText(WordtoSpanPart_1);
		if (damage == 0.0){
			WordtoSpanPart_2 = new SpannableString(""); 
		}else{
			WordtoSpanPart_2 = new SpannableString(" + " + strDamage + " %");
		}
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoForceAmp.append(WordtoSpanPart_2);
	}
	
	private void updateTVAbsorption(){
		String strAbsorption = secFormatter.format(absorption);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.general_absorption) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenAbsorption.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + strAbsorption + " %");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenAbsorption.append(WordtoSpanPart_2);
	}
	
	
	private void updateTVEnergyEquivalent(){
		String strEnergy = distFormatter.format(energyEquivalent);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.general_energy) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenEnergy.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + strEnergy + " K");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenEnergy.append(WordtoSpanPart_2);
	}
	
	
	private void updateTVNbrXmpBusterToDestroy(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.general_xmpbuster) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenXmpBuster.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(strNbrBusterToDestroy);  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenXmpBuster.append(WordtoSpanPart_2);
	}
	
	private void updateTVXMCostToDestroy(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.general_xm_cost) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenXMDestroy.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(strCostAttackXM);  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenXMDestroy.append(WordtoSpanPart_2);
	}
	
	
	
	private void updateTVAPCreate(){
		String strAPCreate = secFormatter.format(apCreate);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.general_ap_create) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenAPCreate.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + strAPCreate + " AP");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenAPCreate.append(WordtoSpanPart_2);
	}
	
	private void updateTVAPDestroy(){
		String strAPDestroy = secFormatter.format(apDestroy);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.general_ap_destroy) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenAPDestroy.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + strAPDestroy + " AP");  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvGenAPDestroy.append(WordtoSpanPart_2);
	}
	
	
	private void updateTVResoPortalShield(){
		if (shield == 0){
			shield = 0;
		}else{
			shield = shield * 100;
		}
		String strShield = distFormatter.format(shield);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.shield) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoPortalShield.setText(WordtoSpanPart_1);
		if (shield == 0){
			WordtoSpanPart_2 = new SpannableString("0");
		}else{
			WordtoSpanPart_2 = new SpannableString(" + " + strShield + " %");
		}
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvResoPortalShield.append(WordtoSpanPart_2);
	}
	
	
	private void updateTVPortalInfo(){
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.gps_name) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvPortalInfo.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(" " + name);  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvPortalInfo.append(WordtoSpanPart_2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateBt(BT_SIMULATOR);
	}
	
	private void analyze(){
		damage = 0;
		calculEnergy();
		calculLevel();
		range = rangeReso[level - 1];
		
		calculNbrReso();
		calculEnergyLink();
		
		calculCooldown();
		calculHack();
		
		calculAPCreate();
		calculAPDestroy();
		
		//calculRange();
		calculForceAmp();
		calculTurret();
		calculShield();
		
		damage = forceAmp * 100 + hitBonus;
		
		
		calculAbsorption();
		calculDecay();
		calculEnergyEquivalent();
		
		calculRange();
		
		calculLinkAmp();	//doit etre apres calculRange
		
		updateTVDecay();
		updateTVResoLevel();
		updateTVEnergy();
		updateTVResoRange();
		updateTVResoField();
		updateTVResoLink();
		updateTVResoForceAmp();
		updateTVResoAttackFrequency();
		//updateTVResoHitBonus();
		updateTVResoCooldown();
		updateTVResoHack();
		updateTVResoPortalShield();
		
		updateTVAbsorption();
		updateTVEnergyEquivalent();
		updateTVAPDestroy();
		updateTVAPCreate();
		
		//
		getNbrBusterToDestroy();
		updateTVNbrXmpBusterToDestroy();
		updateTVXMCostToDestroy();
		
		
	}
	
	public void analyzeNearestPortal(){
		tvPortalGet.setText(getResources().getString(R.string.gps_nearest_portal));
		updateTVPortalInfo();
		for (int Cpt = 0; Cpt < NBR_LEVEL; Cpt++){
			//if (fgDebug){Log.d(TAG, "level = " + ingressReso[Cpt].getLevel());};
			updateReso(Cpt, ingressReso[Cpt].getLevel());
		}
		
		
		damage = 0;
		calculEnergy();
		calculLevel();
		range = rangeReso[level - 1];
		calculNbrReso();
		calculEnergyLink();
		
		calculCooldown();
		calculHack();
		
		calculAPCreate();
		calculAPDestroy();
		calculLinkAmp();
		calculForceAmp();
		calculTurret();
		calculShield();
		
		damage = forceAmp * 100 + hitBonus;
		
		
		calculAbsorption();
		calculDecay();
		calculEnergyEquivalent();
		
		updateTVDecay();
		updateTVResoLevel();
		updateTVEnergy();
		updateTVResoRange();
		updateTVResoField();
		updateTVResoLink();
		updateTVResoForceAmp();
		updateTVResoAttackFrequency();
		//updateTVResoHitBonus();
		updateTVResoCooldown();
		updateTVResoHack();
		updateTVResoPortalShield();
		
		updateTVAbsorption();
		updateTVEnergyEquivalent();
		updateTVAPDestroy();
		updateTVAPCreate();
		
		//
		getNbrBusterToDestroy();
		updateTVNbrXmpBusterToDestroy();
		updateTVXMCostToDestroy();
		
		
	}
	
	
	
	
	
	
	private void getNbrBusterToDestroy(){
		strCostAttackXM = "";
		double costXM = 0;
		double coefDamage = damage/100 +1;
		strNbrBusterToDestroy = "";
		int[] nbrXmpLevel = new int[NBR_LEVEL];
		for (int Cpt = 0; Cpt < NBR_LEVEL; Cpt++){
			nbrXmpLevel[Cpt] = (int) (energyEquivalent*1000/xmpDamage[Cpt]);
			if (nbrXmpLevel[Cpt] > 0){
				strNbrBusterToDestroy = strNbrBusterToDestroy + String.valueOf(nbrXmpLevel[Cpt]) + " L" +  String.valueOf(Cpt + 1) + ";";
				//costXM = cout attack par le portail X damage supplementaire PLUS le cot d'utilisation du Buster
				costXM = nbrXmpLevel[Cpt] * xmpAttack[Cpt] * coefDamage + nbrXmpLevel[Cpt] * xmpCost[Cpt]; 
				if (costXM/1000 > 1){
					strCostAttackXM = strCostAttackXM + secFormatter.format(costXM/1000) +  "k L" +  String.valueOf(Cpt + 1) + ";";
				}else{
					strCostAttackXM = strCostAttackXM + String.valueOf(costXM) +  " L" +  String.valueOf(Cpt + 1) + ";";
				}
			}
		}
		if (strNbrBusterToDestroy.length() > 0){
			strNbrBusterToDestroy = strNbrBusterToDestroy.substring(0, strNbrBusterToDestroy.length() - 1);
		}
		if (strCostAttackXM.length() > 0){
			strCostAttackXM = strCostAttackXM.substring(0, strCostAttackXM.length() - 1);
		}
		
	}
	
	public void calculRange(){
		//for (int Cpt = 0; Cpt < ZONE_RESO; Cpt++){
			//if (fgDebug){Log.d(TAG, "delta = " + rangeDelta[Cpt]);};
		//}
		double deltaEnergy = 0;
		if (energy < 8.0){
			range = 0;
		}
		if (energy >=8 & energy <12){
			range = rangeReso[0];
			deltaEnergy = (energy - 8)*2; 
			//if (fgDebug){Log.d(TAG, "deltaEnergy = " + deltaEnergy);};
			range = rangeDelta[0]*deltaEnergy;
			//if (fgDebug){Log.d(TAG, "range = " + range);};
			range = rangeReso[0] + rangeDelta[0]*deltaEnergy;
		}
		if (energy >=12 & energy <16){
			range = rangeReso[1];
			deltaEnergy = (energy - 12)*2; 
			range = rangeDelta[1]*deltaEnergy;
			range = rangeReso[1] + rangeDelta[1]*deltaEnergy;
		}
		if (energy >=16 & energy <20){
			range = rangeReso[2];
			deltaEnergy = (energy - 16)*2; 
			range = rangeDelta[2]*deltaEnergy;
			range = rangeReso[2] + rangeDelta[2]*deltaEnergy;
		}
		if (energy >=20 & energy <24){
			range = rangeReso[3];
			deltaEnergy = (energy - 20)*2; 
			range = rangeDelta[3]*deltaEnergy;
			range = rangeReso[3] + rangeDelta[3]*deltaEnergy;
		}
		if (energy >=24 & energy <32){
			range = rangeReso[4];
			deltaEnergy = (energy - 24); 
			range = rangeDelta[4]*deltaEnergy;
			range = rangeReso[4] + rangeDelta[4]*deltaEnergy;
		}
		if (energy >=32 & energy <40){
			range = rangeReso[5];
			deltaEnergy = (energy - 32); 
			range = rangeDelta[5]*deltaEnergy;
			range = rangeReso[5] + rangeDelta[5]*deltaEnergy;
		}
		if (energy >=40 & energy <48){
			range = rangeReso[6];
			deltaEnergy = (energy - 40); 
			range = rangeDelta[6]*deltaEnergy;
			range = rangeReso[6] + rangeDelta[6]*deltaEnergy;
		}
		if (energy >=48){
			range = rangeReso[7];
		}
	}
	
	private void calculDecay(){
		//if (fgDebug){Log.d(TAG, "choixDecay = " + choixDecay);};
		if (choixDecay == 7 || choixDecay == 8){
			decay = 0.0;
		}else{
			decay = DECAY_PER_DAY * (choixDecay + 1);
		}
		if (decay >= 1){
			decay = 1;
		}
		//if (fgDebug){Log.d(TAG, "decay = " + decay);};
	}
	
	private void calculCooldown(){
		int hsNbrCommon = 0;
		int hsNbrRare = 0;
		int hsNbrVeryRare = 0;
		double hsCommon = 0.8;
		double hsRare = 0.5;
		double hsVeryRare = 0.3;
				
		for (int Cpt = 0; Cpt < ZONE_MOD; Cpt++){
			
			//analyse du heatSink
			if (resultMod[Cpt].getType().equalsIgnoreCase(MOD_TYPE_HEAT_SINK)){
				if (resultMod[Cpt].getCat().equalsIgnoreCase(COMMON)){
					hsNbrCommon++;
					//if (fgDebug){Log.d(TAG, "hsNbrCommon = " + hsNbrCommon);};
				}
				if (resultMod[Cpt].getCat().equalsIgnoreCase(RARE)){
					hsNbrRare++;
					//if (fgDebug){Log.d(TAG, "hsNbrRare = " + hsNbrRare);};
				}
				if (resultMod[Cpt].getCat().equalsIgnoreCase(VERY_RARE)){
					hsNbrVeryRare++;
					//if (fgDebug){Log.d(TAG, "hsNbrVeryRare = " + hsNbrVeryRare);};
				}
				
				tvName[Cpt].setText(getResources().getString(R.string.name_heat_sink));
				
			}//fin HeatSink
		}
		cooldown = (int) (Math.pow(hsCommon, hsNbrCommon) * Math.pow(hsRare, hsNbrRare) * Math.pow(hsVeryRare, hsNbrVeryRare)*300);
	
	}
	
	private void calculHack(){
		hack = 4;
		int multiHack = 0;
		int multiHackNbr = 0;
		
		for (int Cpt = 0; Cpt < ZONE_MOD; Cpt++){
							
			if (resultMod[Cpt].getType().equalsIgnoreCase(MOD_TYPE_MULTI_HACK)){
				if (resultMod[Cpt].getCat().equalsIgnoreCase(COMMON)){
					multiHackNbr++;
					if (multiHackNbr == 1){
						multiHack = 4;
					}else{
						multiHack = 4/2;
					}
				}
				if (resultMod[Cpt].getCat().equalsIgnoreCase(RARE)){
					multiHackNbr++;
					if (multiHackNbr == 1){
						multiHack = 8;
					}else{
						multiHack = 8/2;
					}
				}
				if (resultMod[Cpt].getCat().equalsIgnoreCase(VERY_RARE)){
					multiHackNbr++;
					if (multiHackNbr == 1){
						multiHack = 12;
					}else{
						multiHack = 12/2;
					}
				}
				//if (fgDebug){Log.d(TAG, "multiHackNbr = " + multiHackNbr);};
				//if (fgDebug){Log.d(TAG, "multiHack = " + multiHack);};
				
				hack = hack + multiHack;
				//if (fgDebug){Log.d(TAG, "hack = " + hack);};
				tvName[Cpt].setText(getResources().getString(R.string.name_multi_hack));
			}// fin mutiHack
			
		}
	}
	
	private void calculLinkAmp(){
		int linkAmpNbr = 0;
		double rangeInit = range;;
		
		for (int Cpt = 0; Cpt < ZONE_MOD; Cpt++){
			if (resultMod[Cpt].getType().equalsIgnoreCase(MOD_TYPE_LINK_AMP)){
				//un seul mode RARE
				linkAmpNbr++;
				//if (fgDebug){Log.d(TAG, "linkAmpNbr = " + linkAmpNbr);};
				if (linkAmpNbr == 1){
					range = rangeInit * 2;
				}
				if (linkAmpNbr == 2){
					range = rangeInit * 2 + rangeInit*0.5;
				}
				if (linkAmpNbr == 3){
					range = rangeInit * 2 + rangeInit*0.5 + rangeInit*0.25;
				}
				if (linkAmpNbr == 4){
					range = rangeInit * 2 + rangeInit*0.5 + rangeInit*0.25 + rangeInit*0.25;
				}
				//if (fgDebug){Log.d(TAG, "range 2  = " + range);};
				
				tvName[Cpt].setText(getResources().getString(R.string.name_link_amp));
			}// linkAmp
		
		}
	
	}
	
	private void calculForceAmp(){
		forceAmp = 0;
		int nbrForceAmp = 0;
		double forceAmpInit  = 1;
		for (int Cpt = 0; Cpt < ZONE_MOD; Cpt++){
			if (resultMod[Cpt].getType().equalsIgnoreCase(MOD_TYPE_FORCE_AMP)){
				//un seul mode RARE
				nbrForceAmp++;
				//if (fgDebug){Log.d(TAG, "linkForceNbr = " + nbrForceAmp);};
				if (nbrForceAmp == 1){
					forceAmp = forceAmpInit * 2;
				}
				if (nbrForceAmp == 2){
					forceAmp = forceAmpInit * 2 + forceAmpInit*0.5;
				}
				if (nbrForceAmp == 3){
					forceAmp = forceAmpInit * 2 + forceAmpInit*0.5 + forceAmpInit*0.25;
				}
				if (nbrForceAmp == 4){
					forceAmp = forceAmpInit * 2 + forceAmpInit*0.5 + forceAmpInit*0.25 + forceAmpInit*0.25;
				}
				//if (fgDebug){Log.d(TAG, "forceAmp = " + forceAmp);};
				
				tvName[Cpt].setText(getResources().getString(R.string.name_force_amp));
			}// linkAmp
		
		}
	
	}
	
	private void calculTurret(){
		attackFrequency = 0;
		hitBonus = 0;
		int nbrTurret = 0;
		double attackFrequencyInit  = 1;
		for (int Cpt = 0; Cpt < ZONE_MOD; Cpt++){
			if (resultMod[Cpt].getType().equalsIgnoreCase(MOD_TYPE_TURRET)){
				//un seul mode RARE
				nbrTurret++;
				//if (fgDebug){Log.d(TAG, "nbrTurret = " + nbrTurret);};
				if (nbrTurret == 1){
					attackFrequency = attackFrequencyInit * 2;
				}
				if (nbrTurret == 2){
					attackFrequency = attackFrequencyInit * 2 + attackFrequencyInit*0.5;
				}
				if (nbrTurret == 3){
					attackFrequency = attackFrequencyInit * 2 + attackFrequencyInit*0.5 + attackFrequencyInit*0.25;
				}
				if (nbrTurret == 4){
					attackFrequency = attackFrequencyInit * 2 + attackFrequencyInit*0.5 + attackFrequencyInit*0.25 + attackFrequencyInit*0.25;
				}
				hitBonus = 0.3 * nbrTurret * 100;
				//if (fgDebug){Log.d(TAG, "forceAmp = " + forceAmp);};
				
				tvName[Cpt].setText(getResources().getString(R.string.name_turret));
			}// linkAmp
		
		}
	
	}
	
	private void calculShield(){
		shield = 0;
		int shieldNbrCommon = 0;
		int shieldNbrRare = 0;
		int shieldNbrVeryRare = 0;
		double shieldCommon = 0.9;
		double shieldRare = 0.8;
		double shieldVeryRare = 0.7;
				
		for (int Cpt = 0; Cpt < ZONE_MOD; Cpt++){
			
			//analyse du heatSink
			if (resultMod[Cpt].getType().equalsIgnoreCase(MOD_TYPE_PORTAL_SHIELD)){
				if (resultMod[Cpt].getCat().equalsIgnoreCase(COMMON)){
					shieldNbrCommon++;
					//if (fgDebug){Log.d(TAG, "shieldNbrCommon = " + shieldNbrCommon);};
				}
				if (resultMod[Cpt].getCat().equalsIgnoreCase(RARE)){
					shieldNbrRare++;
					//if (fgDebug){Log.d(TAG, "shieldNbrRare = " + shieldNbrRare);};
				}
				if (resultMod[Cpt].getCat().equalsIgnoreCase(VERY_RARE)){
					shieldNbrVeryRare++;
					//if (fgDebug){Log.d(TAG, "shieldNbrVeryRare = " + shieldNbrVeryRare);};
				}
				
				tvName[Cpt].setText(getResources().getString(R.string.name_shield));
				
			}//fin HeatSink
		}
		if (shieldNbrCommon == 0 && shieldNbrRare == 0 && shieldNbrVeryRare == 0){
			shield = 0.0;
		}else{
			shield = Math.pow(shieldCommon, shieldNbrCommon) * Math.pow(shieldRare, shieldNbrRare) * Math.pow(shieldVeryRare, shieldNbrVeryRare);
		}
		//if (fgDebug){Log.d(TAG, "shield = " + shield);};
		
	
	
	}
	
	private void calculAbsorption(){
		if (shield >  0.0){
			shield = 1 - shield;
		}
		absorption = energyLink + shield; 
		//if (fgDebug){Log.d(TAG, "absorption = " + absorption);};
		absorption = absorption * 100;
		
		
	}
	
	private void calculEnergyEquivalent(){
		energyEquivalent = energy * (1 + absorption /100.0);
		energyEquivalent = energyEquivalent * ( 1 - decay);
		//if (fgDebug){Log.d(TAG, "energyEquivalent = " + energyEquivalent);};
	}
	
	private void calculEnergyLink(){
		energyLink = nbrLink/Math.E;
		//if (fgDebug){Log.d(TAG, "energyLink = " + energyLink);};
		energyLink = Math.atan(energyLink);
		//if (fgDebug){Log.d(TAG, "energyLink = " + energyLink);};
		energyLink = (4.0/9.0)*energyLink;
		//if (fgDebug){Log.d(TAG, "energyLink = " + energyLink);};
		
	}
	
	private void calculNbrReso(){
		nbrReso = 0;
		for (int Cpt = 0; Cpt < ingressReso.length; Cpt++){
			if (ingressReso[Cpt].getEnergy() > 0){
				nbrReso++;
			}
		}
	}
	
	private void calculAPCreate(){
		apCreate = 0;
		
		if (nbrReso >= 1){
			apCreate = APCreateResoFirst;
		}
		for (int Cpt = 0; Cpt < nbrReso; Cpt++){
			apCreate = apCreate + APCreateReso;
		}
		if (nbrReso == 8){
			apCreate = apCreate + APCreateResoLast;
		}
		//if (fgDebug){Log.d(TAG, "apCreate reso = " + apCreate);};
		
		apCreate = apCreate + APCreateLink * (int)nbrLink;
		//if (fgDebug){Log.d(TAG, "apCreate link = " + apCreate);};
		apCreate = apCreate + APCreateField * (int)nbrField;
		//if (fgDebug){Log.d(TAG, "apCreate field = " + apCreate);};
		nbrMod = 0;
		for (int Cpt = 0; Cpt < ZONE_MOD; Cpt++){
			if (!resultMod[Cpt].getType().equalsIgnoreCase("")){
				nbrMod++;
			}
		}
		//if (fgDebug){Log.d(TAG, "apCreate mode = " + apCreate);};
		apCreate = apCreate + APCreateMod * nbrMod;
		
	}
	
	private void calculAPDestroy(){
		apDestroy = 0;
		apDestroy = apDestroy + nbrReso * APDestroyReso;
		apDestroy = apDestroy + nbrLink * APDestroyLink;
		apDestroy = apDestroy + nbrField * APDestroyField;
		//if (fgDebug){Log.d(TAG, "apDestroy mode = " + apDestroy);};
	}
	
	
	private void calculEnergy(){
		energy = 0;
		for (int Cpt = 0; Cpt < ingressReso.length; Cpt++){
			//if (fgDebug){Log.d(TAG, "ingressReso[Cpt].getEnergy() = " + ingressReso[Cpt].getEnergy());};
			energy = energy + ingressReso[Cpt].getEnergy();
			
		}
		//if (fgDebug){Log.d(TAG, "energy = " + energy);};
	}
	
	private void calculLevel(){
		if (energy < 12){
			level = 1;
		}
		if (energy >=12 & energy < 16){
			level = 2;
		}
		if (energy >= 16 & energy < 20){
			level = 3;
		}
		if (energy >= 20 & energy < 24){
			level = 4;
		}
		if (energy >= 24 & energy < 32){
			level = 5;
		}
		if (energy >= 32 & energy < 40){
			level = 6;
		}
		if (energy >= 40 & energy < 48){
			level = 7;
		}
		if (energy == 48){
			level = 8;
		}
		
	}
	
	private void decodeJson(String result){
		if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, result);}
		try {
			JSONObject jObject = new JSONObject(result);
			//info general
			String id = jObject.getString("id");
			//if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "id = " + id);}
			name = jObject.getString("name");
			//if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "name = " + name);}
			team = jObject.getString("team");
			//if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "team = " + team);}
			
			//info resonator
			JSONArray jArray = jObject.getJSONArray("resonator");
			for (int i = 0; i < jArray.length(); i++){
		        JSONObject oneObject = jArray.getJSONObject(i);
		        String slot = oneObject.getString("slot");
		        //if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "slot = " + slot);}
		        String level = oneObject.getString("level");
		        //if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "level = " + level);}
		        String energyTotal = oneObject.getString("energyTotal");
		        //if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "energyTotal = " + energyTotal);}
		        String distanceToPortal = oneObject.getString("distanceToPortal");
		        //if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "distanceToPortal = " + distanceToPortal);}
		        ingressReso[i].setLevel(Integer.parseInt(level)-1);
		        ingressReso[i].setEnergy(Double.parseDouble(energyTotal));
			    
			}
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void onTaskComplete(String result) {
		// TODO Auto-generated method stub
		//Log.d("WS","result :: "+ result);
		decodeJson(result);
		analyzeNearestPortal();
		progressBarGetReso.setVisibility(View.INVISIBLE);
	}



}
