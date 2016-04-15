package com.tspandroid;

public class Scythe extends EquipableItem {
	
	Scythe(){
		GrassBlock gblock = new GrassBlock(null,0,0);		// creates a dummy block to cross reference
		addInteraction(gblock);	// adds dummy block to interaction array

		name = "Scythe";
		defText = Textures.DEFAULT;
	}
}
