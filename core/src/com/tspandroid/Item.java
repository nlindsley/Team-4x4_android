package com.tspandroid;

import java.lang.*;

/** Class which controls items. */
public class Item extends Character {
	public Item(TSPGame game, int x, int y) {
		super(game, x, y);
		
		defText = Textures.ARROWPACK;
		width = 32;
		height = 32;
	}
}