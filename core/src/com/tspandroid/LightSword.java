package com.tspandroid;

public class LightSword extends EquipableItem {
	
	LightSword(){
		changeDamage(14);

		name = "Light Sword";
		defText = Textures.DEFAULT;
	}
	
	public void checkDK(Enemy e){
		if(e.name.equals("TDK")){
			changeDamage(1);
		}
	}
}
