package com.tspandroid;

public class Sword extends EquipableItem {
	Sword(){
		this.changeAcquired();

		name = "Sword";
		defText = Textures.SWORD1;
		width = 64;
		height = 16;
	}
}
