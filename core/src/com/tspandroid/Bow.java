package com.tspandroid;

public class Bow extends EquipableItem {

	private int speed = 3;
	
	Bow() {
		name = "Bow";
		defText = Textures.DEFAULT;
	}
	
	public int getSpeed(){
		return speed;
	}
}
