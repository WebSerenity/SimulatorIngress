package com.simultor.ingress;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.simulator.ingress.R;
import com.simulator.element.IngressXmpBuster;
import com.simulator.element.IngressXmpZone;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.text.Spannable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {
	public static String TAG = "WS";
	public static boolean fgDebug = true;
	
	protected Spannable WordtoSpanPart_1;
	protected Spannable WordtoSpanPart_2;
	
	protected static NumberFormat distFormatter = null;
	protected static NumberFormat secFormatter = null;
	protected static NumberFormat coordFormatter = null;
	
	protected static int actionBarHeight = 75;
	
	private static Button btSimulator;
	private static Button btLevel;
	private static Button btAP;
	private static Button btAgent;
	private static Button btHack;
	private static Button btAttack;
	private static Button btPortal;
	private static Button btMod;
	private static Button btResonator;
	private static Button btWeapon;
	private static Button btVirus;
	private static Button btMedia;
	private static Button btPortalKey;
	private static Button btPowerCube;
	private static Button btRound;
	
	
	
	protected static int BT_SIMULATOR = 0;
	protected static int BT_MOD = 1;
	protected static int BT_RESONATOR = 2;
	protected static int BT_WEAPON = 3;
	protected static int BT_MEDIA = 4;
	protected static int BT_PORTAL_KEY = 5;
	protected static int BT_POWER_CUBE = 6;
	protected static int BT_LEVEL = 7;
	protected static int BT_AP = 8;
	protected static int BT_PORTAL = 9;
	protected static int BT_VIRUS = 10;
	protected static int BT_HACK = 11;
	protected static int BT_AGENT = 12;
	protected static int BT_ATTACK = 13;
	protected static int BT_ROUND = 14;
	
	protected static int ZONE_MOD = 4;
	protected static int ZONE_RESO = 8;
	protected static int NBR_LEVEL = 8;
	protected static int NBR_MOD = 12;
	protected static int NBR_DECAY = 9;
	protected static double DECAY_PER_DAY = 0.15;
	protected static String VERSION = "(vers : beta 1.2)";
	public static String VERSION_NUM = "beta 1.2";
	public static String URL_PORTAL = "http://www.didungar.com/ingress/get_portal.php";
	protected Resources resources;
	public static int screenWidth = 0;
	public static int screenHeight = 0;
	
	//public IngressMod ingressMod;
	protected String MOD_TYPE_PORTAL_SHIELD = "Portal Shield";
	protected String MOD_TYPE_LINK_AMP = "Link Amp";
	protected String MOD_TYPE_HEAT_SINK = "Heat Sink";
	protected String MOD_TYPE_MULTI_HACK = "Multi Hack";
	protected String MOD_TYPE_FORCE_AMP = "Force Amp";
	protected String MOD_TYPE_TURRET = "Turret";
	protected String VIRUS_ADA = "ada";
	protected String VIRUS_JARVIS = "jarvis";
	protected int COOLDOWN = 180;
	
	protected String COMMON = "Common";
	protected String RARE = "Rare";
	protected String VERY_RARE = "Very Rare";
	
	protected int APCreateResoFirst = 500;
	protected int APCreateReso = 125;
	protected int APCreateResoLast = 250;
	protected int APCreateLink = 313;
	protected int APCreateField = 1250;
	protected int APCreateMod = 125;
	protected int APPortalAccepted = 500;
	protected int APPictureChange = 100;
	
	
	protected int APDestroyReso = 75;
	protected int APDestroyLink = 187;
	protected int APDestroyField = 750;
	
	protected int recycleReso = 20;
	
	protected double[] energyReso = new double[ZONE_RESO];
	protected static double[] rangeReso = new double[ZONE_RESO];
	protected static double[] rangeDelta = new double[ZONE_RESO];
	protected int[] costUsedReso = new int[NBR_LEVEL];
	protected int[] maxReso = new int[ZONE_RESO];
	protected int[] maxPlayer = new int[ZONE_RESO];
	protected int[] xmpRange = new int[ZONE_RESO];
	protected int[] xmpDamage = new int[ZONE_RESO];
	protected int[] xmpAttack = new int[ZONE_RESO];
	protected int[] xmpAttackCritical = new int[ZONE_RESO];
	protected int[] xmpCost = new int[ZONE_RESO];
	protected int[] xmpPower = new int[ZONE_RESO];
	//protected IngressMod[] ingressMod = new IngressMod[NBR_MOD];
	
	protected ArrayList<IngressXmpBuster> listIngressXmpBuster = new ArrayList<IngressXmpBuster>(); 
	
	//protected ArrayList<IngressXmpZone> listIngressXmpZone_0 = new ArrayList<IngressXmpZone>(); 
	
	private IngressXmpZone ingressXmpZone = new IngressXmpZone();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if (fgDebug){Log.d(TAG, "Start");};
		resources = getResources();
		
		PackageInfo pInfo;
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			setTitle(getResources().getString(R.string.app_name) + " " + pInfo.versionName);
		} catch (NameNotFoundException e) {
			if (fgDebug){Log.d(TAG, "error version = " + e.getMessage());};
		}
		
	    
	    Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		screenWidth = size.x;
		//if (fgDebug){Log.d(TAG, "screenWidth = " + screenWidth);};
		screenHeight = size.y;
		//if (fgDebug){Log.d(TAG, "screenHeight = " + screenHeight);};
		
		distFormatter = new DecimalFormat("#0.00");
		secFormatter = new DecimalFormat("#0");
		coordFormatter = new DecimalFormat("#0.000000");
		
		energyReso[0] = 1;
		energyReso[1] = 1.5;
		energyReso[2] = 2;
		energyReso[3] = 2.5;
		energyReso[4] = 3;
		energyReso[5] = 4;
		energyReso[6] = 5;
		energyReso[7] = 6;
		
		rangeReso[0] = 160;
		rangeReso[1] = 160 * 2*2*2*2;
		rangeReso[2] = 160 * 3*3*3*3;
		rangeReso[3] = 160 * 4*4*4*4;
		rangeReso[4] = 160 * 5*5*5*5;
		rangeReso[5] = 160 * 6*6*6*6;
		rangeReso[6] = 160 * 7*7*7*7;
		rangeReso[7] = 160 * 8*8*8*8;
		
		rangeDelta[0] = (rangeReso[1] - rangeReso[0])/8;
		rangeDelta[1] = (rangeReso[2] - rangeReso[1])/8;
		rangeDelta[2] = (rangeReso[3] - rangeReso[2])/8;
		rangeDelta[3] = (rangeReso[4] - rangeReso[3])/8;
		rangeDelta[4] = (rangeReso[5] - rangeReso[4])/8;
		rangeDelta[5] = (rangeReso[6] - rangeReso[5])/8;
		rangeDelta[6] = (rangeReso[7] - rangeReso[6])/8;
		rangeDelta[7] = 0;
		
		
		
		
		costUsedReso[0] = 50;
		costUsedReso[1] = 100;
		costUsedReso[2] = 150;
		costUsedReso[3] = 200;
		costUsedReso[4] = 250;
		costUsedReso[5] = 300;
		costUsedReso[6] = 350;
		costUsedReso[7] = 400;
		
		maxReso[0] = 8;
		maxReso[1] = 4;
		maxReso[2] = 4;
		maxReso[3] = 4;
		maxReso[4] = 2;
		maxReso[5] = 2;
		maxReso[6] = 1;
		maxReso[7] = 1;
		
		maxPlayer[0] = 1;
		maxPlayer[1] = 2;
		maxPlayer[2] = 2;
		maxPlayer[3] = 2;
		maxPlayer[4] = 4;
		maxPlayer[5] = 4;
		maxPlayer[6] = 8;
		maxPlayer[7] = 8;
		
		xmpRange[0] = 42;
		xmpRange[1] = 48;
		xmpRange[2] = 58;
		xmpRange[3] = 72;
		xmpRange[4] = 90;
		xmpRange[5] = 112;
		xmpRange[6] = 138;
		xmpRange[7] = 168;
		
		xmpDamage[0] = 150;
		xmpDamage[1] = 300;
		xmpDamage[2] = 500;
		xmpDamage[3] = 900;
		xmpDamage[4] = 1200;
		xmpDamage[5] = 1500;
		xmpDamage[6] = 1800;
		xmpDamage[7] = 2700;
		
		xmpAttack[0] = 75;
		xmpAttack[1] = 150;
		xmpAttack[2] = 300;
		xmpAttack[3] = 500;
		xmpAttack[4] = 750;
		xmpAttack[5] = 1125;
		xmpAttack[6] = 1625;
		xmpAttack[7] = 2500;
		
		xmpCost[0] = 10;
		xmpCost[1] = 20;
		xmpCost[2] = 70;
		xmpCost[3] = 140;
		xmpCost[4] = 250;
		xmpCost[5] = 360;
		xmpCost[6] = 490;
		xmpCost[7] = 640;
		
		xmpPower[0] = 1000;
		xmpPower[1] = 2000;
		xmpPower[2] = 3000;
		xmpPower[3] = 4000;
		xmpPower[4] = 5000;
		xmpPower[5] = 6000;
		xmpPower[6] = 7000;
		xmpPower[7] = 8000;
		
		
		
		for (int Cpt = 0; Cpt < NBR_LEVEL; Cpt++){
			IngressXmpBuster ingressXmpBuster = new IngressXmpBuster(); 
			ingressXmpBuster.setLevel(Cpt);
			ingressXmpBuster.setCost(xmpCost[Cpt]);
			ingressXmpBuster.setDamage(xmpDamage[Cpt]);
			ingressXmpBuster.setRecycle(20 * Cpt);
			ingressXmpBuster.setRange(xmpRange[Cpt]);
			//calcul des zones d energie
			ArrayList<IngressXmpZone> listIngressXmpZone = new ArrayList<IngressXmpZone>();
			int deltaEnergy = xmpDamage[Cpt]/6;
			//if (fgDebug){Log.d(TAG, "level = " + Cpt + " deltaEnergy = " + deltaEnergy);};
			int deltaZone = xmpRange[Cpt]/5;
			//if (fgDebug){Log.d(TAG, "level = " + Cpt + " deltaZone = " + deltaZone);};
			ingressXmpZone = new IngressXmpZone();
			ingressXmpZone.setDamage(xmpDamage[Cpt] - deltaEnergy);
			ingressXmpZone.setDeb(0);
			ingressXmpZone.setFin(deltaZone - 1);
			//if (fgDebug){Log.d(TAG, "ingressXmpZone.getDamage() = " + ingressXmpZone.getDamage());};
			//if (fgDebug){Log.d(TAG, "ingressXmpZone.getDeb() = " + ingressXmpZone.getDeb());};
			//if (fgDebug){Log.d(TAG, "ingressXmpZone.getFin() = " + ingressXmpZone.getFin());};
			listIngressXmpZone.add(ingressXmpZone);
			ingressXmpZone = new IngressXmpZone();
			ingressXmpZone.setDamage(xmpDamage[Cpt] - 2*deltaEnergy);
			ingressXmpZone.setDeb(deltaZone);
			ingressXmpZone.setFin(2*deltaZone - 1);
			//if (fgDebug){Log.d(TAG, "ingressXmpZone.getDamage() = " + ingressXmpZone.getDamage());};
			//if (fgDebug){Log.d(TAG, "ingressXmpZone.getDeb() = " + ingressXmpZone.getDeb());};
			//if (fgDebug){Log.d(TAG, "ingressXmpZone.getFin() = " + ingressXmpZone.getFin());};
			listIngressXmpZone.add(ingressXmpZone);
			ingressXmpZone = new IngressXmpZone();
			ingressXmpZone.setDamage(xmpDamage[Cpt] - 3*deltaEnergy);
			ingressXmpZone.setDeb(2*deltaZone);
			ingressXmpZone.setFin(3*deltaZone - 1);
			listIngressXmpZone.add(ingressXmpZone);
			ingressXmpZone = new IngressXmpZone();
			ingressXmpZone.setDamage(xmpDamage[Cpt] - 4*deltaEnergy);
			ingressXmpZone.setDeb(3*deltaZone);
			ingressXmpZone.setFin(4*deltaZone - 1);
			listIngressXmpZone.add(ingressXmpZone);
			ingressXmpZone = new IngressXmpZone();
			ingressXmpZone.setDamage(xmpDamage[Cpt] - 5*deltaEnergy);
			ingressXmpZone.setDeb(4*deltaZone);
			ingressXmpZone.setFin(5*deltaZone - 1);
			listIngressXmpZone.add(ingressXmpZone);
			//if (fgDebug){Log.d(TAG, "listIngressXmpZone = " + listIngressXmpZone.size());};
			ingressXmpBuster.setListXmpZone(listIngressXmpZone);
			
			listIngressXmpBuster.add(ingressXmpBuster);
		}
		
		for (int Cpt = 0; Cpt < 8; Cpt++){
			//if (fgDebug){Log.d(TAG, "level = " + Cpt);};
			IngressXmpBuster ingressXmpBuster = new IngressXmpBuster();
			ingressXmpBuster = listIngressXmpBuster.get(Cpt);
			ArrayList<IngressXmpZone> list = new ArrayList<IngressXmpZone>();
			list = ingressXmpBuster.getListXmpZone();
			for (int k = 0; k < 5; k++){
				//if (fgDebug){Log.d(TAG,"level = " + Cpt + " zone = " + k +  " damage = " + list.get(k).getDamage());};
				//if (fgDebug){Log.d(TAG,"level = " + Cpt + " zone = " + k +  " deb = " + list.get(k).getDeb());};
				//if (fgDebug){Log.d(TAG,"level = " + Cpt + " zone = " + k +  " fin = " + list.get(k).getFin());};
			
			}
		}
		

	}
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getMenuInflater();
	    inflater.inflate(R.menu.main,menu);
	    setMenuBackground();
		
		return true;
	}
	
	protected void setMenuBackground(){
		final LayoutInflater infl = getLayoutInflater();
		infl.setFactory(new Factory(){

			@Override
			public View onCreateView(String name, Context context,AttributeSet attrs) {
				if ( name.equalsIgnoreCase( "com.android.internal.view.menu.IconMenuItemView" ) ) {
                    try { // Ask our inflater to create the view  
                        LayoutInflater f = getLayoutInflater();  
                        final View view = f.createView( name, null, attrs );  
                        /* The background gets refreshed each time a new item is added the options menu.  
                        * So each time Android applies the default background we need to set our own  
                        * background. This is done using a thread giving the background change as runnable 
                        * object */
                        new Handler().post( new Runnable() {  
                            public void run () {  
                                // sets the background color   
                                view.setBackgroundResource( getResources().getColor(R.color.color_bleu));
                                // sets the text color              
                                ((TextView) view).setTextColor(getResources().getColor(R.color.color_jaune));
                                // sets the text size              
                                ((TextView) view).setTextSize(18);
                }
                        } );  
                    return view;
                }
            catch ( InflateException e ) {}
            catch ( ClassNotFoundException e ) {}  
        } 
				return null;
			}});
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent intent = new Intent(BaseActivity.this, Help.class);
		startActivity(intent);
		return true;
		//return super.onMenuItemSelected(featureId, item);
	}
	
	

	@Override
	protected void onResume() {
		super.onResume();
		btSimulator = (Button)findViewById(R.id.btSimulator);
		btLevel = (Button)findViewById(R.id.btLevel);
		btAP = (Button)findViewById(R.id.btAP);
		btAgent = (Button)findViewById(R.id.btAgent);
		btPortal = (Button)findViewById(R.id.btPortal);
		btMod = (Button)findViewById(R.id.btMod);
		btResonator = (Button)findViewById(R.id.btResonator);
		btWeapon = (Button)findViewById(R.id.btWeapon);
		btVirus = (Button)findViewById(R.id.btVirus);
		btPowerCube = (Button)findViewById(R.id.btPowerCube);
		btMedia = (Button)findViewById(R.id.btMedia);
		btPortalKey = (Button)findViewById(R.id.btPortalKey);
		btHack = (Button)findViewById(R.id.btHack);
		btAttack = (Button)findViewById(R.id.btAttack);
		btRound = (Button)findViewById(R.id.btRound);
		
		
		
		btSimulator.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent =  new Intent(BaseActivity.this, Simulator.class);
				startActivity(intent);
				finish();
			}
		});
		
		btRound.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent =  new Intent(BaseActivity.this, Round.class);
				startActivity(intent);
				finish();
			}
		});
		
		btLevel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent =  new Intent(BaseActivity.this, Level.class);
				startActivity(intent);
				finish();
			}
		});
		
		btAP.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent =  new Intent(BaseActivity.this, AP.class);
				startActivity(intent);
				finish();
			}
		});
		
		btAgent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent =  new Intent(BaseActivity.this, Agent.class);
				startActivity(intent);
				finish();
			}
		});
		
		btHack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent =  new Intent(BaseActivity.this, Hack.class);
				startActivity(intent);
				finish();
			}
		});
		
		btAttack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//if (fgDebug){Log.d(TAG, "btAttack click");};
				Intent intent =  new Intent(BaseActivity.this, Attack.class);
				startActivity(intent);
				finish();
			}
		});
		
		btPortal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent =  new Intent(BaseActivity.this, Portal.class);
				startActivity(intent);
				finish();
			}
		});
		
		btResonator.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent =  new Intent(BaseActivity.this, Resonator.class);
				startActivity(intent);
				finish();
			}
		});
		
		btMod.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent =  new Intent(BaseActivity.this, Mod.class);
				startActivity(intent);
				finish();
				
			}
		});
		
		
		
		btWeapon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//if (fgDebug){Log.d(TAG, "btWeapon");};
				Intent intent =  new Intent(BaseActivity.this, Weapon.class);
				startActivity(intent);
				finish();
			}
		});
		
		btVirus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//if (fgDebug){Log.d(TAG, "btWeapon");};
				Intent intent =  new Intent(BaseActivity.this, Virus.class);
				startActivity(intent);
				finish();
			}
		});
		
		btPowerCube.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//if (fgDebug){Log.d(TAG, "btPowerCube");};
				Intent intent =  new Intent(BaseActivity.this, PowerCube.class);
				startActivity(intent);
				finish();
				
			}
		});
		
		btMedia.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//if (fgDebug){Log.d(TAG, "btMedia");};
				Intent intent =  new Intent(BaseActivity.this, Media.class);
				startActivity(intent);
				finish();
			}
		});
		
		btPortalKey.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//if (fgDebug){Log.d(TAG, "btPortalKey");};
				Intent intent =  new Intent(BaseActivity.this, Key.class);
				startActivity(intent);
				finish();
			}
		});
		
		
	}
	
	
	
	protected void updateBt(int bt){
		//if (fgDebug){Log.d(TAG, "bt = " + bt);};
		btSimulator.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btSimulator.setTextColor(getResources().getColor(R.color.color_off));
		btRound.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btRound.setTextColor(getResources().getColor(R.color.color_off));
		btLevel.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btLevel.setTextColor(getResources().getColor(R.color.color_off));
		btAP.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btAP.setTextColor(getResources().getColor(R.color.color_off));
		btAgent.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btAgent.setTextColor(getResources().getColor(R.color.color_off));
		btHack.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btHack.setTextColor(getResources().getColor(R.color.color_off));
		btAttack.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btAttack.setTextColor(getResources().getColor(R.color.color_off));
		btPortal.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btPortal.setTextColor(getResources().getColor(R.color.color_off));
		btMod.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btMod.setTextColor(getResources().getColor(R.color.color_off));
		btResonator.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btResonator.setTextColor(getResources().getColor(R.color.color_off));
		btWeapon.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btWeapon.setTextColor(getResources().getColor(R.color.color_off));
		btVirus.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btVirus.setTextColor(getResources().getColor(R.color.color_off));
		btMedia.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btMedia.setTextColor(getResources().getColor(R.color.color_off));
		btPortalKey.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btPortalKey.setTextColor(getResources().getColor(R.color.color_off));
		btPowerCube.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_off));
		btPowerCube.setTextColor(getResources().getColor(R.color.color_off));
		if (bt == BT_SIMULATOR){
			btSimulator.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btSimulator.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_ROUND){
			btRound.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btRound.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_LEVEL){
			btLevel.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btLevel.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_AP){
			btAP.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btAP.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_AGENT){
			btAgent.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btAgent.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_HACK){
			btHack.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btHack.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_ATTACK){
			if (fgDebug){Log.d(TAG, "BT_ATTACK = " + BT_ATTACK);};
			btAttack.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btAttack.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_PORTAL){
			btPortal.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btPortal.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_MOD){
			btMod.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btMod.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_RESONATOR){
			btResonator.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btResonator.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_WEAPON){
			btWeapon.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btWeapon.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_VIRUS){
			btVirus.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btVirus.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_POWER_CUBE){
			btPowerCube.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btPowerCube.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_MEDIA){
			btMedia.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btMedia.setTextColor(getResources().getColor(R.color.color_on));
		}
		if (bt == BT_PORTAL_KEY){
			btPortalKey.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_border_on));
			btPortalKey.setTextColor(getResources().getColor(R.color.color_on));
		}
		
		
	}
	
	protected double arrondi(double val) {
		return (Math.floor(val*100.0))/100;
	} 

}
