package com.tspandroid;

public class Lantern extends EquipableItem {
	
	Lantern(){
		SnowBlock sblock = new SnowBlock(null,0,0);	// creates a dummy block to cross reference
		addInteraction(sblock);	// adds dummy block to interaction array
		changeDamage(0);
		
		name = "Lantern";
		defText = Textures.DEFAULT;
	}
}
