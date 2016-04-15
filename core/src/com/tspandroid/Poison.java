package com.tspandroid;

import java.lang.*;

public class Poison extends Character {
	public Poison(TSPGame game, int x, int y) {
		super(game, x, y);
		defText = Textures.POISON;
	}
	
	public void xMove(int amount){
		
	}
	public void yMove(int amount){
		
	}
	
	public void damagePlayer(Player p){
		if(this.isCollidingWith(p)){
			p.lives -=5;
		}
	}

}
