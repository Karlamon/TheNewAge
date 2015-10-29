package com.newage.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.newage.game.Game;

public class Enemy {
	private static final int MOVEMENT = 200;
	private Vector3 position;
	private Vector3 velocity;
	private Texture eTexture;
	private Animation eMoveDown;
	private Animation eMoveLeft;
	private Animation eMoveRight;
	private Animation eMoveUp;
	private Rectangle bounds;
	private boolean eDown = false;
	private boolean eUp = false;
	private boolean eLeft = false;
	private boolean eRight = false;

	public Enemy(int x, int y) {
		position = new Vector3(x, y, 0);
		velocity = new Vector3(0, 0, 0);
		eTexture = new Texture("enemy.png");
		eMoveDown = new Animation(new TextureRegion(eTexture), 4, 0.5f, 0);
		eMoveLeft = new Animation(new TextureRegion(eTexture), 4, 0.5f, 1);
		eMoveRight = new Animation(new TextureRegion(eTexture), 4, 0.5f, 2);
		eMoveUp = new Animation(new TextureRegion(eTexture), 4, 0.5f, 3);
		bounds = new Rectangle(x, y, eTexture.getWidth() / 4, eTexture.getHeight() / 4);
	}

	public void update(float dt) {
		bounds.setPosition(position.x, position.y);

		// Move Enemy up.
		if (getPosition().y < ((Game.HEIGHT / 1.04) - eTexture.getHeight() / 4)) {
			eMoveUp.update(dt);
			eDown = false;
			eUp = true;
			eRight = false;
			eLeft = false;
			position.add(velocity.x, (MOVEMENT * dt), 0);

			// Move Enemy down.
		} else if (getPosition().y > 30) {
			eMoveDown.update(dt);
			eDown = true;
			eUp = false;
			eRight = false;
			eLeft = false;
			position.sub(velocity.x, (MOVEMENT * dt), 0);

			// // Move Enemy right.
		} else if (getPosition().x < ((Game.WIDTH / 1.02) - eTexture.getWidth() / 4)) {
			eMoveRight.update(dt);
			eDown = false;
			eUp = false;
			eRight = true;
			eLeft = false;
			position.add((MOVEMENT * dt), velocity.y, 0);

			// Move Enemy left.
		} else if (getPosition().x > 30) {
			eMoveLeft.update(dt);
			eDown = false;
			eUp = false;
			eRight = false;
			eLeft = true;
			position.sub((MOVEMENT * dt), velocity.y, 0);

			// No movements
		} else {
			// No animation, Player 1 is stationary.
		}
	}

	public Vector3 getPosition() {
		return position;
	}

	public TextureRegion getTexture() {
		if (eUp) {
			return eMoveUp.getFrame();
		}

		if (eDown) {
			return eMoveDown.getFrame();
		}

		if (eRight) {
			return eMoveRight.getFrame();
		}

		if (eLeft) {
			return eMoveLeft.getFrame();
		}

		// Default
		return eMoveDown.getFrame();
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void dispose() {
		eTexture.dispose();
	}
}
