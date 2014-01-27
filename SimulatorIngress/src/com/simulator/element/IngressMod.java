package com.simulator.element;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class IngressMod {
	private Drawable drawable;
	private String type;
	private String cat;
	private String info;
	private double absorption;
	private double range;
	private double cooldown;
	private int hack;
	private double forceAmp;
	private double turret;
	private int cost;
	private int recycle;
	private double linkAmp;
	
	public IngressMod(){
		
	}
	public Drawable getDrawable() {
		return drawable;
	}
	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public double getAbsorption() {
		return absorption;
	}
	public void setAbsorption(double absorption) {
		this.absorption = absorption;
	}
	public double getRange() {
		return range;
	}
	public void setRange(double range) {
		this.range = range;
	}
	public double getCooldown() {
		return cooldown;
	}
	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}
	public int getHack() {
		return hack;
	}
	public void setHack(int hack) {
		this.hack = hack;
	}
	public double getForceAmp() {
		return forceAmp;
	}
	public void setForceAmp(double forceAmp) {
		this.forceAmp = forceAmp;
	}
	public double getTurret() {
		return turret;
	}
	public void setTurret(double turret) {
		this.turret = turret;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getRecycle() {
		return recycle;
	}
	public void setRecycle(int recycle) {
		this.recycle = recycle;
	}
	public double getLinkAmp() {
		return linkAmp;
	}
	public void setLinkAmp(double linkAmp) {
		this.linkAmp = linkAmp;
	}


}
