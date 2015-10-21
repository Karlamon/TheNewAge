package com.newage.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.newage.game.Game;

public class Player2 {
	private static final int MOVEMENT = 200;
	private Vector3 position;
	private Vector3 velocity;
	private Texture p2Texture;
	private Animation p2MoveDown;
	private Animation p2MoveLeft;
	private Animation p2MoveRight;
	private Animation p2MoveUp;
	private Rectangle bounds;
	private boolean p2down = false;
	private boolean p2up = false;
	private boolean p2left = false;
	private boolean p2right = false;

	public Player2(int x, int y) {
		position = new Vector3(0, 0, 0);
		velocity = new Vector3(0, 0, 0);
		p2Texture = new Texture("player2.png");
		p2MoveDown = new Animation(new TextureRegion(p2Texture), 4, 0.5f, 0);
		p2MoveLeft = new Animation(new TextureRegion(p2Texture), 4, 0.5f, 1);
		p2MoveRight = new Animation(new TextureRegion(p2Texture), 4, 0.5f, 2);
		p2MoveUp = new Animation(new TextureRegion(p2Texture), 4, 0.5f, 3);
		bounds = new Rectangle(x, y, p2Texture.getWidth() / 4, p2Texture.getHeight() / 4);
	}

	public void update(float dt) {
		bounds.setPosition(position.x, position.y);
		
		// Move p2up
		if ((Gdx.input.isKeyPressed(Input.Keys.UP)) && (getPosition().y < (Game.HEIGHT - p2Texture.getHeight() / 4))) {
			p2MoveUp.update(dt);
			p2down = false;
			p2up = true;
			p2right = false;
			p2left = false;
			position.add(velocity.x, (MOVEMENT * dt), 0);
			
			// Move p2down
		} else if ((Gdx.input.isKeyPressed(Input.Keys.DOWN)) && (getPosition().y > 0)) {
			p2MoveDown.update(dt);
			p2down = true;
			p2up = false;
			p2right = false;
			p2left = false;
			position.sub(velocity.x, (MOVEMENT * dt), 0);
			
			// Move p2right
		} else if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && (getPosition().x < (Game.WIDTH - p2Texture.getWidth() / 4))) {
			p2MoveRight.update(dt);
			p2down = false;
			p2up = false;
			p2right = true;
			p2left = false;
			position.add((MOVEMENT * dt), velocity.y, 0);
			
			// Move p2left
		} else if ((Gdx.input.isKeyPressed(Input.Keys.LEFT)) && (getPosition().x > 0)) {
			p2MoveLeft.update(dt);
			p2down = false;
			p2up = false;
			p2right = false;
			p2left = true;
			position.sub((MOVEMENT * dt), velocity.y, 0);
			
			// No Movements
		} else {
			// No animation, player stationary
		}
	}

	public Vector3 getPosition() {
		return position;
	}

	public TextureRegion getTexture() {
		if (p2up) {
			return p2MoveUp.getFrame();
		}
		
		if (p2down) {
			return p2MoveDown.getFrame();
		}
		
		if (p2right) {
			return p2MoveRight.getFrame();
		}
		
		if (p2left) {
			return p2MoveLeft.getFrame();
		}
		
		// Default 
		return p2MoveDown.getFrame();
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void dispose() {
		p2Texture.dispose();
	}
}
