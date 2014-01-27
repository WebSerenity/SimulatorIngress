package com.simultor.ingress;

import com.simulator.ingress.R;

import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Agent extends BaseActivity{
	private TextView tvBadge_1;
	private TextView tvBadge_2;
	private TextView tvBadge_3;
	private TextView tvBadge_4;
	private TextView tvBadge_5;
	
	private TextView tvMoreConnector;
	private TextView tvMoreSeer;
	private TextView tvMoreBuilder;
	private TextView tvMoreExplorer;
	private TextView tvMoreGuardian;
	private TextView tvMoreHacker;
	private TextView tvMoreMind;
	private TextView tvMorePurifier;
	private TextView tvMoreLiberator;
	private TextView tvMorePioner;
	private TextView tvMoreFounder;
	
	private PopupWindow popupBadge;
	private Badge badgeConnector = new Badge();
	private Badge badgeSeer = new Badge();
	private Badge badgeBuilder = new Badge();
	private Badge badgeExplorer = new Badge();
	private Badge badgeGuardian = new Badge();
	private Badge badgeHacker = new Badge();
	private Badge badgeMind= new Badge();
	private Badge badgePurifier = new Badge();
	private Badge badgeLiberator = new Badge();
	private Badge badgePioner = new Badge();
	private Badge badgeFounder = new Badge();
	
	private LinearLayout ll_1;
	private LinearLayout ll_2;
	private LinearLayout ll_3;
	private LinearLayout ll_4;
	private LinearLayout ll_5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agent);
		
		tvBadge_1 = (TextView)findViewById(R.id.tvBadge_1);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.agent_badge_bronze) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBadge_1.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(getResources().getString(R.string.agent_badge_bronze_2) );  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBadge_1.append(WordtoSpanPart_2);
		
		tvBadge_2 = (TextView)findViewById(R.id.tvBadge_2);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.agent_badge_silver) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBadge_2.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(getResources().getString(R.string.agent_badge_silver_2) );  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBadge_2.append(WordtoSpanPart_2);
		
		tvBadge_3 = (TextView)findViewById(R.id.tvBadge_3);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.agent_badge_gold) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBadge_3.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(getResources().getString(R.string.agent_badge_gold_2) );  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBadge_3.append(WordtoSpanPart_2);
		
		tvBadge_4 = (TextView)findViewById(R.id.tvBadge_4);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.agent_badge_black) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBadge_4.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(getResources().getString(R.string.agent_badge_black_2) );  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBadge_4.append(WordtoSpanPart_2);
		
		tvBadge_5 = (TextView)findViewById(R.id.tvBadge_5);
		WordtoSpanPart_1 = new SpannableString(getResources().getString(R.string.agent_badge_platinium) + " ");  
		WordtoSpanPart_1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_bleu)), 0, WordtoSpanPart_1.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBadge_5.setText(WordtoSpanPart_1);
		WordtoSpanPart_2 = new SpannableString(getResources().getString(R.string.agent_badge_platinium_2) );  
		WordtoSpanPart_2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_jaune)), 0, WordtoSpanPart_2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBadge_5.append(WordtoSpanPart_2);
		
		badgeConnector.setName(getResources().getString(R.string.agent_badge_connector));
		badgeConnector.setBronze("50");
		badgeConnector.setSilver("1 000");
		badgeConnector.setGold("5 000");
		badgeConnector.setBlack("25 000");
		badgeConnector.setPlatinium("100 000");
		badgeConnector.setImage(getResources().getDrawable(R.drawable.badge_connector));
		badgeConnector.setInfo("Link Portals.");
		
		badgeSeer.setName(getResources().getString(R.string.agent_badge_seer));
		badgeSeer.setBronze("10");
		badgeSeer.setSilver("50");
		badgeSeer.setGold("200");
		badgeSeer.setBlack("500");
		badgeSeer.setPlatinium("5000");
		badgeSeer.setImage(getResources().getDrawable(R.drawable.badge_seer));
		badgeSeer.setInfo("Disocvery and sucessfully submit new Portals.");
		
		badgeBuilder.setName(getResources().getString(R.string.agent_badge_builder));
		badgeBuilder.setBronze("2 000");
		badgeBuilder.setSilver("10 000");
		badgeBuilder.setGold("30 000");
		badgeBuilder.setBlack("100 000");
		badgeBuilder.setPlatinium("200 000");
		badgeBuilder.setImage(getResources().getDrawable(R.drawable.badge_builder));
		badgeBuilder.setInfo("Deploy Resonnators.");
		
		badgeExplorer.setName(getResources().getString(R.string.agent_badge_explorer));
		badgeExplorer.setBronze("100");
		badgeExplorer.setSilver("1 000");
		badgeExplorer.setGold("2 000");
		badgeExplorer.setBlack("10 000");
		badgeExplorer.setPlatinium("30 000");
		badgeExplorer.setImage(getResources().getDrawable(R.drawable.badge_explorer));
		badgeExplorer.setInfo("Visit and hack distink Portals.");
		
		badgeGuardian.setName(getResources().getString(R.string.agent_badge_guardian));
		badgeGuardian.setBronze("3");
		badgeGuardian.setSilver("10");
		badgeGuardian.setGold("20");
		badgeGuardian.setBlack("90");
		badgeGuardian.setPlatinium("150");
		badgeGuardian.setImage(getResources().getDrawable(R.drawable.badge_guardian));
		badgeGuardian.setInfo("ontrol a Portal for consecutive days.");
		
		badgeHacker.setName(getResources().getString(R.string.agent_badge_hacker));
		badgeHacker.setBronze("100");
		badgeHacker.setSilver("500");
		badgeHacker.setGold("2 000");
		badgeHacker.setBlack("10 000");
		badgeHacker.setPlatinium("40 000");
		badgeHacker.setImage(getResources().getDrawable(R.drawable.badge_hacker));
		badgeHacker.setInfo("Hack Portals.");
		
		badgeMind.setName(getResources().getString(R.string.agent_badge_mind));
		badgeMind.setBronze("2 000");
		badgeMind.setSilver("10 000");
		badgeMind.setGold("30 000");
		badgeMind.setBlack("100 000");
		badgeMind.setPlatinium("200 000");
		badgeMind.setImage(getResources().getDrawable(R.drawable.badge_mind));
		badgeMind.setInfo("Create Control Fields");
		
		badgePurifier.setName(getResources().getString(R.string.agent_badge_purifier));
		badgePurifier.setBronze("2 000");
		badgePurifier.setSilver("10 000");
		badgePurifier.setGold("30 000");
		badgePurifier.setBlack("100 000");
		badgePurifier.setPlatinium("300 000");
		badgePurifier.setImage(getResources().getDrawable(R.drawable.badge_purifier));
		badgePurifier.setInfo("Destroy enemy Resonators.");
		
		badgeLiberator.setName(getResources().getString(R.string.agent_badge_liberator));
		badgeLiberator.setBronze("100");
		badgeLiberator.setSilver("1 000");
		badgeLiberator.setGold("5 000");
		badgeLiberator.setBlack("15 000");
		badgeLiberator.setPlatinium("40 000");
		badgeLiberator.setImage(getResources().getDrawable(R.drawable.badge_liberator));
		badgeLiberator.setInfo("Capture Portals.");
		
		badgePioner.setName(getResources().getString(R.string.agent_badge_pioner));
		badgePioner.setBronze("20");
		badgePioner.setSilver("200");
		badgePioner.setGold("1 000");
		badgePioner.setBlack("5 000");
		badgePioner.setPlatinium("20 000");
		badgePioner.setImage(getResources().getDrawable(R.drawable.badge_pionner));
		badgePioner.setInfo("Capture distincts Portals.");
		
		badgeFounder.setName(getResources().getString(R.string.agent_badge_founder));
		badgeFounder.setBronze("");
		badgeFounder.setSilver("");
		badgeFounder.setGold("");
		badgeFounder.setBlack("");
		badgeFounder.setPlatinium("");
		badgeFounder.setImage(getResources().getDrawable(R.drawable.badge_founder));
		badgeFounder.setInfo("In recognition of valiant contributions during the early days of Ingress.");
		
		tvMoreConnector = (TextView)findViewById(R.id.tvMoreConnector);
		tvMoreConnector.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initiatePopupBadge(badgeConnector);
			}
		});
		
		tvMoreSeer = (TextView)findViewById(R.id.tvMoreSeer);
		tvMoreSeer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initiatePopupBadge(badgeSeer);
			}
		});
		
		tvMoreBuilder = (TextView)findViewById(R.id.tvMoreBuilder);
		tvMoreBuilder.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initiatePopupBadge(badgeBuilder);
			}
		});
		
		tvMoreExplorer = (TextView)findViewById(R.id.tvMoreExplorer);
		tvMoreExplorer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initiatePopupBadge(badgeExplorer);
			}
		});
		
		tvMoreGuardian = (TextView)findViewById(R.id.tvMoreGuardian);
		tvMoreGuardian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initiatePopupBadge(badgeGuardian);
			}
		});
		
		tvMoreHacker = (TextView)findViewById(R.id.tvMoreHacker);
		tvMoreHacker.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initiatePopupBadge(badgeHacker);
			}
		});
		
		tvMoreMind = (TextView)findViewById(R.id.tvMoreMind);
		tvMoreMind.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initiatePopupBadge(badgeMind);
			}
		});
		
		tvMorePurifier = (TextView)findViewById(R.id.tvMorePurifier);
		tvMorePurifier.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				initiatePopupBadge(badgePurifier);
			}
		});
		
		tvMoreLiberator = (TextView)findViewById(R.id.tvMoreLiberator);
		tvMoreLiberator.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				initiatePopupBadge(badgeLiberator);
			}
		});
		
		tvMorePioner = (TextView)findViewById(R.id.tvMorePioner);
		tvMorePioner.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				initiatePopupBadge(badgePioner);
			}
		});
		
		tvMoreFounder = (TextView)findViewById(R.id.tvMoreFounder);
		tvMoreFounder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				initiatePopupBadge(badgeFounder);
			}
		});
	}
	
	
	 @Override
	protected void onResume() {
		super.onResume();
		updateBt(BT_AGENT);
	}
	 
	 private void initiatePopupBadge(final Badge badge) {
			LayoutInflater inflater = (LayoutInflater) Agent.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.badge,(ViewGroup) findViewById(R.id.popup_badge));
			popupBadge = new PopupWindow(layout, (int) (screenWidth*0.8), (int) (screenHeight*0.8), true);
			ImageView ivPopupBadge = (ImageView)layout.findViewById(R.id.ivPopupBadge);
			ivPopupBadge.setBackgroundDrawable(badge.getImage());
			TextView tvBadgeNom = (TextView)layout.findViewById(R.id.tvBadgeNom);
			tvBadgeNom.setText(badge.getName());
			TextView tvBronze = (TextView)layout.findViewById(R.id.tvBronze);
			tvBronze.setText(badge.getBronze());
			TextView tvSilver = (TextView)layout.findViewById(R.id.tvSilver);
			tvSilver.setText(badge.getSilver());
			TextView tvGold = (TextView)layout.findViewById(R.id.tvGold);
			tvGold.setText(badge.getGold());
			TextView tvBlack = (TextView)layout.findViewById(R.id.tvBlack);
			tvBlack.setText(badge.getBlack());
			TextView tvPlatinium = (TextView)layout.findViewById(R.id.tvPlatinium);
			tvPlatinium.setText(badge.getPlatinium());
			TextView tvBadgeInfo = (TextView)layout.findViewById(R.id.tvBadgeInfo);
			tvBadgeInfo.setText(badge.getInfo());
			
			ll_1 = (LinearLayout)layout.findViewById(R.id.ll_1);
			ll_2 = (LinearLayout)layout.findViewById(R.id.ll_2);
			ll_3 = (LinearLayout)layout.findViewById(R.id.ll_3);
			ll_4 = (LinearLayout)layout.findViewById(R.id.ll_4);
			ll_5 = (LinearLayout)layout.findViewById(R.id.ll_5);
			
			if (badge == badgeFounder){
				ll_1.setVisibility(View.INVISIBLE);
				ll_2.setVisibility(View.INVISIBLE);
				ll_3.setVisibility(View.INVISIBLE);
				ll_4.setVisibility(View.INVISIBLE);
				ll_5.setVisibility(View.INVISIBLE);
			}else{
				ll_1.setVisibility(View.VISIBLE);
				ll_2.setVisibility(View.VISIBLE);
				ll_3.setVisibility(View.VISIBLE);
				ll_4.setVisibility(View.VISIBLE);
				ll_5.setVisibility(View.VISIBLE);
			}
			
			popupBadge.showAtLocation(layout, Gravity.CENTER, 0, 0);
			layout.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					popupBadge.dismiss();
					
				}
			});


		}

}
