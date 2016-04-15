package com.tspandroid;

import java.lang.*;

/** This class creates and deals with the projectiles (bullets) used for ranged combat.  */
public class Bullet extends Character {
	public Bullet(TSPGame game, int x, int y) {
		super(game, x, y);
		
		defText = Textures.BULLET;
		width = 8;
		height = 8;
	}
}
