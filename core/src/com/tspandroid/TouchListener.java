package com.tspandroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class TouchListener implements InputProcessor {

    boolean[] buttonPressed;
    float heightRatio, widthRatio;

    final int UP = 0, upX = 50, upY = 362;
    final int DOWN = 1, downX = 50, downY = 462;
    final int LEFT = 2, leftX = 0, leftY = 412;
    final int RIGHT = 3, rightX = 100, rightY = 412;
    final int A = 4, aX = 387, aY = 412;
    final int B = 5, bX = 462, bY = 412;
    final int PAUSE = 6, pauseX = 231, pauseY = 462;
    final int NONE = 7;


    TouchListener(float gameHeight, float gameWidth, float screenHeight, float screenWidth) {
        buttonPressed = new boolean[7];
        heightRatio = gameHeight/screenHeight;
        widthRatio = gameWidth/screenWidth;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Scale the screen touch
        float x = screenX * widthRatio;
        float y = screenY * heightRatio;

        // Register button press
        buttonPressed[buttonTouched(x, y)] = true;

        return false;
    }

    /* Given a set of coordinates, determines which button was pressed */
    private int buttonTouched(float screenX, float screenY) {

        // Check if UP was pressed
        if (screenX>=upX && screenX<=upX+50 &&
                screenY>=upY && screenY<=upY+50) {
            return UP;
        }
        // Check if DOWN was pressed
        else if (screenX>=downX && screenX<=downX+50 &&
                screenY>=downY && screenY<=downY+50) {
            return DOWN;
        }
        // Check if LEFT was pressed
        else if (screenX>=leftX && screenX<=leftX+50 &&
                screenY>=leftY && screenY<=leftY+50) {
            return LEFT;
        }
        // Check if RIGHT was pressed
        else if (screenX>=rightX && screenX<=rightX+50 &&
                screenY>=rightY && screenY<=rightY+50) {
            return RIGHT;
        }
        // Check if A was pressed
        else if (screenX>=aX && screenX<=aX+50 &&
                screenY>=aY && screenY<=aY+50) {
            return A;
        }
        // Check if B was pressed
        else if (screenX>=bX && screenX<=bX+50 &&
                screenY>=bY && screenY<=bY+50) {
            return B;
        }
        // Check if PAUSE was pressed
        else if (screenX>=pauseX && screenX<=pauseX+50 &&
                screenY>=pauseY && screenY<=pauseY+50) {
            return PAUSE;
        }
        // Check if no was pressed
        else {
            return NONE;
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // Scale the screen touch
        float x = screenX * widthRatio;
        float y = screenY * heightRatio;

        // Determine button released
        int released = buttonTouched(x, y);

        // If A or B button released, keep moving
        if (released == A || released == B) {
            buttonPressed[A] = false;
            buttonPressed[B] = false;
            buttonPressed[PAUSE] = false;
        }
        // Any other button released, stop moving
        else {
            buttonPressed[UP] = false;
            buttonPressed[DOWN] = false;
            buttonPressed[LEFT] = false;
            buttonPressed[RIGHT] = false;
            buttonPressed[A] = false;
            buttonPressed[B] = false;
            buttonPressed[PAUSE] = false;
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
