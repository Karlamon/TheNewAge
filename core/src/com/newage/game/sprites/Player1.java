package com.newage.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player1 {
	private static final int MOVEMENT = 200;
	private Vector3 position;
	private Vector3 velocity;
	private Texture p1Texture;
	private Rectangle bounds;

	public Player1(int x, int y) {
		position = new Vector3(x, y, 0);
		velocity = new Vector3(0, 0, 0);
		p1Texture = new Texture("player1.png");
		bounds = new Rectangle(x, y, p1Texture.getWidth() / 3, p1Texture.getHeight());
	}

	public void update(float dt) {
		bounds.setPosition(position.x, position.y);
		if ((Gdx.input.isKeyPressed(Input.Keys.UP)) && (position.y > 0)) {
			position.add((MOVEMENT * dt), velocity.y, 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.DOWN)) && (position.y > 0)) {
			position.sub((MOVEMENT * dt), velocity.y, 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && (position.x > 0)) {
			position.add((MOVEMENT * dt), velocity.x, 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.LEFT)) && (position.x > 0)) {
			position.sub((MOVEMENT * dt), velocity.x, 0);
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
}
