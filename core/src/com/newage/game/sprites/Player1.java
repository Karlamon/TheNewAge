package com.newage.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.newage.game.Game;
import com.newage.gamecontrollers.PovDirection.XBox360Pad;

public class Player1 implements ControllerListener {
	private static final int MOVEMENT = 200;
	private Vector3 position;
	private Vector3 velocity;
	private Texture p1Texture;
	private Animation p1Animation;
	private Rectangle bounds;
	boolean hasControllers = true;
	
	public Player1(int x, int y) {
		position = new Vector3(0, 0, 0);
		velocity = new Vector3(0, 0, 0);
		p1Texture = new Texture("player1.png");
		p1Animation = new Animation(new TextureRegion(p1Texture), 4, 0.5f);
		bounds = new Rectangle(x, y, p1Texture.getWidth(), p1Texture.getHeight());
	}

	public void update(float dt) {
		p1Animation.update(dt);
		bounds.setPosition(position.x, position.y);
		if ((Gdx.input.isKeyPressed(Input.Keys.W)) && (getPosition().y < (Game.HEIGHT - p1Texture.getHeight() / 4))) {
			position.add(velocity.x, (MOVEMENT * dt), 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.S)) && (getPosition().y > 0)) {
			position.sub(velocity.x, (MOVEMENT * dt), 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.D)) && (getPosition().x < (Game.WIDTH - p1Texture.getWidth() / 4))) {
			position.add((MOVEMENT * dt), velocity.y, 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.A)) && (getPosition().x > 0)) {
			position.sub((MOVEMENT * dt), velocity.y, 0);
		}
	}

	public Vector3 getPosition() {
		return position;
	}

	public TextureRegion getTexture() {
		return p1Animation.getFrame();
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void dispose() {
		p1Texture.dispose();
	}

	@Override
	public void connected(Controller controller) {
		hasControllers = true;
	}

	@Override
	public void disconnected(Controller controller) {
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
