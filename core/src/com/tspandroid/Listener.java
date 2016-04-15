package com.tspandroid;

import com.badlogic.gdx.InputProcessor;

/** Creates a listener which responds to keyboard presses. */
public class Listener implements InputProcessor {

	int numberOfKeys = 256;
	boolean[] keysPressed = new boolean[numberOfKeys];
	
	/** checks to see if a key is pressed. */
	@Override
	public boolean keyDown(int keycode) {
		keysPressed[keycode] = true;
		return false;
	}

	/** checks to see if a key is released. */
	@Override
	public boolean keyUp(int keycode) {
		keysPressed[keycode] = false;
		return false;
	}

	//Not used
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	//Not used
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	//Not used
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	//Not used
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	//Not used
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	//Not used
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
