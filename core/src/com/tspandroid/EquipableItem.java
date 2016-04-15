package com.tspandroid;

import java.lang.*;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class EquipableItem implements EquipItem {
	
	String name = "item";
	private boolean acquired = false;
	private int damage = 1;
	private ArrayList<Block> inter = new ArrayList<Block>();
	int width;
	int height;
	double x;
	double y;
	Texture defText;

	@Override
	public boolean getAcquired() {
		return acquired;
	}

	@Override
	public int changeAcquired() {
		acquired = true;
		return 0;
	}

	@Override
	public int getDamage() {
		return 1;
	}

	@Override
	public int changeDamage(int dam) {
		damage = dam;
		return 0;
	}

	@Override
	public boolean getInteractions(Block b) {
		if(inter.contains(b)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public void addInteraction(Block b) {
		inter.add(b);
		
	}
	
	public int attack(){
		return damage;
	}
	

	/**
	 * This class checks if two characters are colliding and returns a boolean value.
	 * @param other unit possibly being collided with.
	 * @return True if they have collided; false otherwise.
	 */
	boolean isCollidingWith(int width, int height, Character other) {
		// Create a bounding rectangle over each character
		Rectangle thisCharacter = new Rectangle((int)x, (int)y, width, height);
		Rectangle otherCharacter = new Rectangle((int)other.x, (int)other.y, other.width, other.height);

		return thisCharacter.overlaps(otherCharacter);
	}

	/** 
	 * Draw all the textures. 
	 * @param batch batch used to draw.
	 */
	public void draw(SpriteBatch batch) {
		batch.draw(defText, (int)x, (int)y);
	}
	
	public void changeTexture (Texture t){
		defText = t;
	}

}
