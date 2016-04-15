package com.tspandroid;

public interface EquipItem {
	
	public boolean getAcquired();
	public int changeAcquired();
	public int getDamage();
	public int changeDamage(int dam);
	public void addInteraction(Block b);
	public boolean getInteractions(Block b);
	
}
