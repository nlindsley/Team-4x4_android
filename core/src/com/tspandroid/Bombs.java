package com.tspandroid;

public class Bombs extends EquipableItem {
	
	Bombs(){
		CrackedWall cwall = new CrackedWall(null, 0, 0);	// creates a dummy block to cross reference
		addInteraction(cwall);	// adds dummy block to interaction array
		changeDamage(2);

		name = "Bombs";
		defText = Textures.DEFAULT;
	}
}
