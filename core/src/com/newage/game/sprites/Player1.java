package com.newage.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.newage.game.Game;

public class Player1 {
	private static final int MOVEMENT = 200;
	private Vector3 position, oldPosition, velocity;
	private Texture p1Texture;
	private Animation p1MoveDown, p1MoveLeft, p1MoveRight, p1MoveUp;
	private Rectangle bounds;
	private boolean p1Down = false;
	private boolean p1Up = false;
	private boolean p1Left = false;
	private boolean p1Right = false;
	private TiledMapTileLayer collisionLayer;
	private int p1Width, p1Height;
	private int tileHeight, tileWidth;

	public Player1(int x, int y, TiledMapTileLayer mapLayer) {
		position = new Vector3(x, y, 0);
		oldPosition = new Vector3(x, y, 0);
		velocity = new Vector3(0, 0, 0);
		p1Texture = new Texture("player1.png");
		p1MoveDown = new Animation(new TextureRegion(p1Texture), 4, 0.5f, 0);
		p1MoveLeft = new Animation(new TextureRegion(p1Texture), 4, 0.5f, 1);
		p1MoveRight = new Animation(new TextureRegion(p1Texture), 4, 0.5f, 2);
		p1MoveUp = new Animation(new TextureRegion(p1Texture), 4, 0.5f, 3);
		p1Height = p1Texture.getHeight() / 4;
		p1Width = p1Texture.getWidth() / 4;
		bounds = new Rectangle(x, y, p1Width, p1Height);
		this.collisionLayer = mapLayer;
		tileHeight = (int) collisionLayer.getTileHeight();
		tileWidth = (int) collisionLayer.getTileWidth();
	}

	public void update(float dt) {
		bounds.setPosition(position.x, position.y);
		oldPosition.set(position);

		// Move Player 1 up.
		if ((Gdx.input.isKeyPressed(Input.Keys.W)) && (getPosition().y < (Game.HEIGHT - p1Height))) {
			p1MoveUp.update(dt);
			p1Down = false;
			p1Up = true;
			p1Right = false;
			p1Left = false;
			// Wall Collision
			// Top Corners
			position.add(velocity.x, (MOVEMENT * dt), 0);
			int tileY = (int) ((position.y + p1Height) / tileHeight);
			int tileX1 = (int) ((position.x) / tileWidth);
			int tileX2 = (int) ((position.x + p1Width) / tileWidth);

			// check if path is blocked
			if (isBlocked(tileX1, tileY) || isBlocked(tileX2, tileY)) {
				position.set(oldPosition);
			}
			// Move Player 1 down.
		} else if ((Gdx.input.isKeyPressed(Input.Keys.S)) && (getPosition().y > 0)) {
			p1MoveDown.update(dt);
			p1Down = true;
			p1Up = false;
			p1Right = false;
			p1Left = false;
			position.sub(velocity.x, (MOVEMENT * dt), 0);
			// Wall Collision
			// Bottom Corners
			int tileY = (int) ((position.y) / tileHeight);
			int tileX1 = (int) ((position.x) / tileWidth);
			int tileX2 = (int) ((position.x + p1Width) / tileWidth);

			// check if path is blocked
			if (isBlocked(tileX1, tileY) || isBlocked(tileX2, tileY)) {
				// System.out.println("BOOOOOOOOOO");
				position.set(oldPosition);
			}

			// // Move Player 1 right.
		} else if ((Gdx.input.isKeyPressed(Input.Keys.D)) && (getPosition().x < (Game.WIDTH - p1Width))) {
			p1MoveRight.update(dt);
			p1Down = false;
			p1Up = false;
			p1Right = true;
			p1Left = false;
			position.add((MOVEMENT * dt), velocity.y, 0);

			// Wall Collision
			// Right Corners
			int tileY1 = (int) ((position.y) / tileHeight);
			int tileY2 = (int) ((position.y + p1Height) / tileHeight);
			int tileX = (int) ((position.x + p1Width) / tileWidth);

			// check if path is blocked
			if (isBlocked(tileX, tileY1) || isBlocked(tileX, tileY2)) {
				position.set(oldPosition);
			}

			// Move Player 1 left.
		} else if ((Gdx.input.isKeyPressed(Input.Keys.A)) && (getPosition().x > 0)) {
			p1MoveLeft.update(dt);
			p1Down = false;
			p1Up = false;
			p1Right = false;
			p1Left = true;
			position.sub((MOVEMENT * dt), velocity.y, 0);

			// Wall Collision
			// Left Corners
			int tileY1 = (int) ((position.y) / tileHeight);
			int tileY2 = (int) ((position.y + p1Height) / tileHeight);
			int tileX = (int) ((position.x) / tileWidth);

			// check if path is blocked
			if (isBlocked(tileX, tileY1) || isBlocked(tileX, tileY2)) {
				// System.out.println("BOOOOOOOOOO");
				position.set(oldPosition);
			}

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

	private boolean isBlocked(int x, int y) {

		Cell C1 = collisionLayer.getCell(x, y);
		if (C1 == null) {
			return false;
		}
		TiledMapTile T1 = C1.getTile();
		if (T1 == null) {
			return false;
		}
		return T1.getProperties().containsKey("Blocked");
	}

	public void dispose() {
		p1Texture.dispose();
	}
}