package com.simulator.element;

import java.util.ArrayList;

public class IngressXmpBuster {
	private int level;
	private int damage;
	private int range;
	private int cost;
	private int recycle;
	private int nbrZone = 0;
	private ArrayList<IngressXmpZone> listXmpZone = new ArrayList<IngressXmpZone>();
	private IngressXmpZone ingressImpZone = new IngressXmpZone();
	//public static xmpBusterRange
	
	public IngressXmpBuster(){
		
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
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

	public int getNbrZone() {
		return nbrZone;
	}

	public void setNbrZone(int nbrZone) {
		this.nbrZone = nbrZone;
	}

	public ArrayList<IngressXmpZone> getListXmpZone() {
		return listXmpZone;
	}

	public void setListXmpZone(ArrayList<IngressXmpZone> listXmpZone) {
		this.listXmpZone = listXmpZone;
	}
	
	public IngressXmpZone getIngressXmpZone(int level){
		return listXmpZone.get(level);
	}



}
