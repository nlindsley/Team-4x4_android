package com.tspandroid;

import java.lang.*;
import java.util.ArrayList;

/** Class which creates the player character */
public class Player extends Character {

	boolean isKnight	= false;
	boolean isMage		= false;
	boolean isArcher	= false;
	int currentRoomX;
	int currentRoomY;
	private int spot = 0;
	private ArrayList<EquipableItem> inventory = new ArrayList<EquipableItem>();
	private EquipableItem current;
	
	public Player(TSPGame game, int x, int y) {
		super(game, x, y);	// Uses Character constructor

		defText = Textures.PLAYER1;	// Overwrites player texture
		lives = 100;
		lastFacing = 1;
		isPlayer = true;
		width = 57;
		height = 57;
		
		fillInventory();
		current = inventory.get(spot);
	}

	/** 
	 * Part of collision handling. Controls movement on the X-axis 
	 * @param amount amount that the character is moving.
	 */
	public void xMove(double amount) {
		x += amount;	// allows movement (left-right)

		for(Enemy e : game.enemies) {
			if(this.isCollidingWith(e) && !isBullet) {
				x -= amount;							// move out of invalid space
				this.knockback(this.lastFacing, 10, 1);	// knockback player and damage
			}
		}
		for(Block b : game.blockArr) {
			if(this.isCollidingWith(b)) {
				x -= amount;	// move back if touching a block
				return;
			}
		}
	}

	/** 
	 * Part of collision handling. Controls movement on the Y-axis 
	 * @param amount amount that the character is moving.
	 */
	public void yMove(double amount) {
		y += amount;	// allows movement (up-down)

		for(Enemy e : game.enemies) {
			if(this.isCollidingWith(e) && !isBullet) { 
				y -= amount;
				this.knockback(this.lastFacing, 5, 1);
			}
		}
		for(Block b : game.blockArr) {
			if(this.isCollidingWith(b)) {
				y -= amount;	// move back if touching a block
				return;
			}
		}
	}
	/**
	 * Uses the selected class's attack ability.
	 */
	public void attack() {
		if(this.isKnight) {
			EquipableItem sword = inventory.get(0);
			if(lastFacing==0){
				sword.changeTexture(Textures.SWORD0);
				sword.x = this.x-47;
				sword.y = this.y+26;
			}
			if(lastFacing==1){
				sword.changeTexture(Textures.SWORD1);
				sword.x = this.x;
				sword.y = this.y-38;
			}
			if(lastFacing==2){
				sword.changeTexture(Textures.SWORD2);
				sword.x = this.x+24;
				sword.y = this.y+8;
			}
			if(lastFacing==3){
				sword.changeTexture(Textures.SWORD3);
				sword.x = this.x+42;
				sword.y = this.y+32;
			}
			
			for(Enemy e : game.enemies) {
				if(lastFacing == 0 || lastFacing == 2) {
					if(sword.isCollidingWith(sword.width, sword.height, e)) {
						e.lives -= sword.getDamage();
					}
				} else {
					if(sword.isCollidingWith(sword.height, sword.width, e)) {
						e.lives -= sword.getDamage();
					}
				}
			}
			for(Boss b : game.bosses) {
				if(lastFacing == 0 || lastFacing == 2) {
					if(sword.isCollidingWith(sword.width, sword.height, b)) {
						b.lives -= sword.getDamage();
					}
				} else {
					if(sword.isCollidingWith(sword.height, sword.width, b)) {
						b.lives -= sword.getDamage();
					}
				}
			}
			
		} else if(this.isMage) {
			if(game.ammo > 0) {
				game.ammo -= 1;
				Bullet bullet = new Bullet(game,(int)this.x, (int)this.y+8);
				if(this.lastFacing == 3)	{ bullet.setYVelocity(15); }
				if(this.lastFacing == 1) 	{ bullet.setYVelocity(-15); }
				if(this.lastFacing == 0) 	{ bullet.setXVelocity(-15); }
				if(this.lastFacing == 2) 	{ bullet.setXVelocity(15); }
				bullet.isBullet = true;
				game.bullets.add(bullet);
			}
		} else if(this.isArcher) {

		}
	}
	
	/**
	 * Creates and initializes an array of inventory items.
	 */
	public void accessInventory() {
		spot++;
		if(spot>6){
			spot = 0;
		} else {
			boolean access = inventory.get(spot).getAcquired();
			if(access == false){
				spot = 0;
			}
		}
		current = inventory.get(spot);
		System.out.printf("selected item %10s\n", current.name);
	}

	/** Returns the inventory item that is currently selected. */
	public EquipableItem getSelectedInventory() { return current; }

	/**
	 * Adds new items the the players' inventory.
	 */
	private void fillInventory(){
		inventory.add(new Sword());
		inventory.add(new Scythe());
		inventory.add(new Lantern());
		inventory.add(new Bombs());
		inventory.add(new MegaShield());
		inventory.add(new Bow());
		inventory.add(new LightSword());
	}
}
