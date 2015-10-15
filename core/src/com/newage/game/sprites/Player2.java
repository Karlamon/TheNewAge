package com.newage.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.newage.game.Game;

public class Player2 {
	private static final int MOVEMENT = 200;
	private Vector3 position;
	private Vector3 velocity;
	private Texture p2Texture;
	private Rectangle bounds;

	public Player2(int x, int y) {
		position = new Vector3(0, 0, 0);
		velocity = new Vector3(0, 0, 0);
		p2Texture = new Texture("player2.png");
		bounds = new Rectangle(x, y, p2Texture.getWidth() / 3, p2Texture.getHeight());
	}

	public void update(float dt) {
		bounds.setPosition(position.x, position.y);
		if ((Gdx.input.isKeyPressed(Input.Keys.UP)) && (getPosition().y < (Game.HEIGHT - p2Texture.getHeight()))) {
			position.add(velocity.x, (MOVEMENT * dt), 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.DOWN)) && (getPosition().y > 0)) {
			position.sub(velocity.x, (MOVEMENT * dt), 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && (getPosition().x < (Game.WIDTH - p2Texture.getWidth()))) {
			position.add((MOVEMENT * dt), velocity.y, 0);
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.LEFT)) && (getPosition().x > 0)) {
			position.sub((MOVEMENT * dt), velocity.y, 0);
		}
	}

	public Vector3 getPosition() {
		return position;
	}

	public Texture getTexture() {
		return p2Texture;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void dispose() {
		p2Texture.dispose();
	}
}
