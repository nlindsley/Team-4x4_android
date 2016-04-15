package com.tspandroid;

public class Bridge extends Background {
	
	boolean appears = false;

	public Bridge(TSPGame game, int x, int y, int world) {
		super(game, x, y, world);
		
		defText = Textures.BRIDGE;
	}
	
	public void appear(){
		appears = true;
	}
	
	public boolean isVisisble(){
		return appears;
	}
}
