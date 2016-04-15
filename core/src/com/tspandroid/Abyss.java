package com.tspandroid;

import java.lang.*;

/**
 * special floor type, made a character to damage player
 *
 */

public class Abyss extends Character {
	public Abyss(TSPGame game, int x, int y) {
		super(game, x, y);
		defText = Textures.ABYSS;
	}
	
	public void xMove(int amount){
		
	}
	public void yMove(int amount){
		
	}
	
	public void damagePlayer(Player p){
		if(this.isCollidingWith(p)){
			p.lives -=100;
		}
	}
	
}
