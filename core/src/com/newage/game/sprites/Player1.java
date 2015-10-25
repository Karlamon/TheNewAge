package com.newage.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.newage.game.Game;

public class Player1 {
	private static final int MOVEMENT = 200;
	private Vector3 position;
	private Vector3 velocity;
	private Texture p1Texture;
	private Animation p1MoveDown;
	private Animation p1MoveLeft;
	private Animation p1MoveRight;
	private Animation p1MoveUp;
	private Rectangle bounds;
	private boolean p1Down = false;
	private boolean p1Up = false;
	private boolean p1Left = false;
	private boolean p1Right = false;

	public Player1(int x, int y) {
		position = new Vector3(0, 0, 0);
		velocity = new Vector3(0, 0, 0);
		p1Texture = new Texture("player1.png");
		p1MoveDown = new Animation(new TextureRegion(p1Texture), 4, 0.5f, 0);
		p1MoveLeft = new Animation(new TextureRegion(p1Texture), 4, 0.5f, 1);
		p1MoveRight = new Animation(new TextureRegion(p1Texture), 4, 0.5f, 2);
		p1MoveUp = new Animation(new TextureRegion(p1Texture), 4, 0.5f, 3);
		bounds = new Rectangle(x, y, p1Texture.getWidth() / 4, p1Texture.getHeight() / 4);
	}

	public void update(float dt) {
		bounds.setPosition(position.x, position.y);

		// Move Player 1 up.
		if ((Gdx.input.isKeyPressed(Input.Keys.W)) && (getPosition().y < (Game.HEIGHT - p1Texture.getHeight() / 4))) {
			p1MoveUp.update(dt);
			p1Down = false;
			p1Up = true;
			p1Right = false;
			p1Left = false;
			position.add(velocity.x, (MOVEMENT * dt), 0);

			// Move Player 1 down.
		} else if ((Gdx.input.isKeyPressed(Input.Keys.S)) && (getPosition().y > 0)) {
			p1MoveDown.update(dt);
			p1Down = true;
			p1Up = false;
			p1Right = false;
			p1Left = false;
			position.sub(velocity.x, (MOVEMENT * dt), 0);

			// // Move Player 1 right.
		} else if ((Gdx.input.isKeyPressed(Input.Keys.D))
				&& (getPosition().x < (Game.WIDTH - p1Texture.getWidth() / 4))) {
			p1MoveRight.update(dt);
			p1Down = false;
			p1Up = false;
			p1Right = true;
			p1Left = false;
			position.add((MOVEMENT * dt), velocity.y, 0);

			// Move Player 1 left.
		} else if ((Gdx.input.isKeyPressed(Input.Keys.A)) && (getPosition().x > 0)) {
			p1MoveLeft.update(dt);
			p1Down = false;
			p1Up = false;
			p1Right = false;
			p1Left = true;
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
		if (p1Up) {
			return p1MoveUp.getFrame();
		}

		if (p1Down) {
			return p1MoveDown.getFrame();
		}

		if (p1Right) {
			return p1MoveRight.getFrame();
		}

		if (p1Left) {
			return p1MoveLeft.getFrame();
		}

		// Default
		return p1MoveDown.getFrame();
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public void dispose() {
		p1Texture.dispose();
	}
}