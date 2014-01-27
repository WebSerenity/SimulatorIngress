package com.simultor.ingress;

import android.R.drawable;
import android.graphics.drawable.Drawable;

public class Badge {
	private String name = "";
	private String bronze;
	private String silver;
	private String gold;
	private String black;
	private String platinium;
	private Drawable image;
	private String info = "";
	
	public Badge(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBronze() {
		return bronze;
	}

	public void setBronze(String bronze) {
		this.bronze = bronze;
	}

	public String getSilver() {
		return silver;
	}

	public void setSilver(String silver) {
		this.silver = silver;
	}

	public String getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}

	public String getBlack() {
		return black;
	}

	public void setBlack(String black) {
		this.black = black;
	}

	public String getPlatinium() {
		return platinium;
	}

	public void setPlatinium(String platinium) {
		this.platinium = platinium;
	}

	public Drawable getImage() {
		return image;
	}

	public void setImage(Drawable drawable) {
		this.image = drawable;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
