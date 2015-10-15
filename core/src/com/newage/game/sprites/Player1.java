package com.newage.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.newage.game.Game;
import com.newage.gamecontrollers.PovDirection.XBox360Pad;

public class Player1 implements ControllerListener {
	private static final int MOVEMENT = 200;
	private Vector3 position;
	private Vector3 velocity;
	private Texture p1Texture;
	private Rectangle bounds;
	boolean hasControllers = true;

	public Player1(int x, int y) {
		position = new Vector3(0, 0, 0);
		velocity = new Vector3(0, 0, 0);
		p1Texture = new Texture("player1.png");
		bounds = new Rectangle(x, y, p1Texture.getWidth() / 3, p1Texture.getHeight());
	}

	public void update(float dt) {
		bounds.setPosition(position.x, position.y);
		if ((Gdx.input.isKeyPressed(Input.Keys.W)) && (getPosition().y < (Game.HEIGHT - p1Texture.getHeight()))) {
			position.add(velocity.x, (MOVEMENT * dt), 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.S)) && (getPosition().y > 0)) {
			position.sub(velocity.x, (MOVEMENT * dt), 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.D)) && (getPosition().x < (Game.WIDTH - p1Texture.getWidth()))) {
			position.add((MOVEMENT * dt), velocity.y, 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.A)) && (getPosition().x > 0)) {
			position.sub((MOVEMENT * dt), velocity.y, 0);
		}
	}

	public Vector3 getPosition() {
		return position;
	}

	public Texture getTexture() {
		return p1Texture;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void dispose() {
		p1Texture.dispose();
	}

	@Override
	public void connected(Controller controller) {
		// TODO Auto-generated method stub
		hasControllers = true;
	}

	@Override
	public void disconnected(Controller controller) {
		// TODO Auto-generated method stub
		hasControllers = false;
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		// This is the dpad
		float dt = 0;
        if(value == XBox360Pad.BUTTON_DPAD_LEFT)
        	position.add(velocity.x, (MOVEMENT * dt), 0);
        if(value == XBox360Pad.BUTTON_DPAD_RIGHT)
        	position.add(velocity.x, (MOVEMENT * dt), 0);
        if(value == XBox360Pad.BUTTON_DPAD_UP)
        	position.add(velocity.x, (MOVEMENT * dt), 0);
        if(value == XBox360Pad.BUTTON_DPAD_DOWN)
        	position.add(velocity.x, (MOVEMENT * dt), 0);
        return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		return false;
	}
}
