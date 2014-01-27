package com.simulator.element;

public class ResultMod {
	private String type;
	private String cat;
	
	public ResultMod(String type, String cat){
		this.type = type;
		this.cat = cat;
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

}
