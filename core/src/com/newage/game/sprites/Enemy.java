package com.newage.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.newage.game.Game;

public class Enemy {
	private static final int MOVEMENT = 200;
	private Vector3 position, oldPosition, velocity;
	private Texture eTexture;
	private Animation eMoveDown, eMoveLeft, eMoveRight, eMoveUp;
	private Rectangle bounds;
	private boolean eDown = false;
	private boolean eUp = false;
	private boolean eLeft = false;
	private boolean eRight = false;
	private TiledMapTileLayer collisionLayer;
	private int eWidth, eHeight;
	private int tileHeight, tileWidth;

	public Enemy(int x, int y, TiledMapTileLayer mapLayer) {
		position = new Vector3(x, y, 0);
		oldPosition = new Vector3(x, y, 0);
		velocity = new Vector3(0, 0, 0);
		eTexture = new Texture("enemy.png");
		eMoveDown = new Animation(new TextureRegion(eTexture), 4, 0.5f, 0);
		eMoveLeft = new Animation(new TextureRegion(eTexture), 4, 0.5f, 1);
		eMoveRight = new Animation(new TextureRegion(eTexture), 4, 0.5f, 2);
		eMoveUp = new Animation(new TextureRegion(eTexture), 4, 0.5f, 3);
		eHeight = eTexture.getHeight() / 4;
		eWidth = eTexture.getWidth() / 4;
		bounds = new Rectangle(x, y, eWidth, eHeight);
		this.collisionLayer = mapLayer;
		tileHeight = (int) collisionLayer.getTileHeight();
		tileWidth = (int) collisionLayer.getTileWidth();
	}

	public void update(float dt) {
		bounds.setPosition(position.x, position.y);
		oldPosition.set(position);

		// Move Player 1 up.
		if (getPosition().y < (Game.HEIGHT - eHeight)) {
			eMoveUp.update(dt);
			eDown = false;
			eUp = true;
			eRight = false;
			eLeft = false;
			// Wall Collision
			// Top Corners
			position.add(velocity.x, (MOVEMENT * dt), 0);
			int tileY = (int) ((position.y + eHeight) / tileHeight);
			int tileX1 = (int) ((position.x) / tileWidth);
			int tileX2 = (int) ((position.x + eWidth) / tileWidth);

			// check if path is blocked
			if (isBlocked(tileX1, tileY) || isBlocked(tileX2, tileY)) {
				// System.out.println("BOOOOOOOOOO");
				position.set(oldPosition);
			}
			// Move Player 1 down.
		} else if (getPosition().y > 0) {
			eMoveDown.update(dt);
			eDown = true;
			eUp = false;
			eRight = false;
			eLeft = false;
			position.sub(velocity.x, (MOVEMENT * dt), 0);
			// Wall Collision
			// Bottom Corners
			int tileY = (int) ((position.y) / tileHeight);
			int tileX1 = (int) ((position.x) / tileWidth);
			int tileX2 = (int) ((position.x + eWidth) / tileWidth);

			// check if path is blocked
			if (isBlocked(tileX1, tileY) || isBlocked(tileX2, tileY)) {
				// System.out.println("BOOOOOOOOOO");
				position.set(oldPosition);
			}

			// // Move Player 1 right.
		} else if (getPosition().x < (Game.WIDTH - eWidth)) {
			eMoveRight.update(dt);
			eDown = false;
			eUp = false;
			eRight = true;
			eLeft = false;
			position.add((MOVEMENT * dt), velocity.y, 0);

			// Wall Collision
			// Right Corners
			int tileY1 = (int) ((position.y) / tileHeight);
			int tileY2 = (int) ((position.y + eHeight) / tileHeight);
			int tileX = (int) ((position.x + eWidth) / tileWidth);

			// check if path is blocked
			if (isBlocked(tileX, tileY1) || isBlocked(tileX, tileY2)) {
				position.set(oldPosition);
			}

			// Move Player 1 left.
		} else if (getPosition().x > 0) {
			eMoveLeft.update(dt);
			eDown = false;
			eUp = false;
			eRight = false;
			eLeft = true;
			position.sub((MOVEMENT * dt), velocity.y, 0);

			// Wall Collision
			// Left Corners
			int tileY1 = (int) ((position.y) / tileHeight);
			int tileY2 = (int) ((position.y + eHeight) / tileHeight);
			int tileX = (int) ((position.x) / tileWidth);

			// check if path is blocked
			if (isBlocked(tileX, tileY1) || isBlocked(tileX, tileY2)) {
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
		eTexture.dispose();
	}
}