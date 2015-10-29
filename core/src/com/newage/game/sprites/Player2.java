package com.newage.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.newage.game.Game;

public class Player2 {
	private static final int MOVEMENT = 200;
	private Vector3 position, newPosition, oldPosition, velocity;
	private Texture p2Texture;
	private Animation p2MoveDown, p2MoveLeft, p2MoveRight, p2MoveUp;
	private Rectangle bounds;
	private boolean p2Down = false;
	private boolean p2Up = false;
	private boolean p2Left = false;
	private boolean p2Right = false;
	private TiledMapTileLayer collisionLayer;
	private int p2Width, p2Height;
	private int tileHeight, tileWidth;

	public Player2(int x, int y, TiledMapTileLayer mapLayer) {
		position = new Vector3(x, y, 0);
		newPosition = new Vector3(0, 0, 0);
		oldPosition = new Vector3(x, y, 0);
		velocity = new Vector3(0, 0, 0);
		p2Texture = new Texture("player2.png");
		p2MoveDown = new Animation(new TextureRegion(p2Texture), 4, 0.5f, 0);
		p2MoveLeft = new Animation(new TextureRegion(p2Texture), 4, 0.5f, 1);
		p2MoveRight = new Animation(new TextureRegion(p2Texture), 4, 0.5f, 2);
		p2MoveUp = new Animation(new TextureRegion(p2Texture), 4, 0.5f, 3);
		p2Height = p2Texture.getHeight() / 4;
		p2Width = p2Texture.getWidth() / 4;
		bounds = new Rectangle(x, y, p2Width, p2Height);
		this.collisionLayer = mapLayer;
		tileHeight = (int) collisionLayer.getTileHeight();
		tileWidth = (int) collisionLayer.getTileWidth();
	}

	public void update(float dt) {
		bounds.setPosition(position.x, position.y);
		oldPosition.set(position);
		boolean collided = false;

		// Move Player 1 up.
		if ((Gdx.input.isKeyPressed(Input.Keys.UP)) && (getPosition().y < (Game.HEIGHT - p2Height))) {
			p2MoveUp.update(dt);
			p2Down = false;
			p2Up = true;
			p2Right = false;
			p2Left = false;
			// Wall Collision
			// Top Corners
			position.add(velocity.x, (MOVEMENT * dt), 0);
			int tileY = (int) ((position.y + p2Height) / tileHeight);
			int tileX1 = (int) ((position.x) / tileWidth);
			int tileX2 = (int) ((position.x + p2Width) / tileWidth);

			// check if path is blocked
			if (isBlocked(tileX1, tileY) || isBlocked(tileX2, tileY)) {
				// System.out.println("BOOOOOOOOOO");
				position.set(oldPosition);
			}
			// Move Player 1 down.
		} else if ((Gdx.input.isKeyPressed(Input.Keys.DOWN)) && (getPosition().y > 0)) {
			p2MoveDown.update(dt);
			p2Down = true;
			p2Up = false;
			p2Right = false;
			p2Left = false;
			position.sub(velocity.x, (MOVEMENT * dt), 0);
			// Wall Collision
			// Bottom Corners
			int tileY = (int) ((position.y) / tileHeight);
			int tileX1 = (int) ((position.x) / tileWidth);
			int tileX2 = (int) ((position.x + p2Width) / tileWidth);

			// check if path is blocked
			if (isBlocked(tileX1, tileY) || isBlocked(tileX2, tileY)) {
				// System.out.println("BOOOOOOOOOO");
				position.set(oldPosition);
			}

			// // Move Player 1 right.
		} else if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && (getPosition().x < (Game.WIDTH - p2Width))) {
			p2MoveRight.update(dt);
			p2Down = false;
			p2Up = false;
			p2Right = true;
			p2Left = false;
			position.add((MOVEMENT * dt), velocity.y, 0);

			// Wall Collision
			// Right Corners
			int tileY1 = (int) ((position.y) / tileHeight);
			int tileY2 = (int) ((position.y + p2Height) / tileHeight);
			int tileX = (int) ((position.x + p2Width) / tileWidth);

			// check if path is blocked
			if (isBlocked(tileX, tileY1) || isBlocked(tileX, tileY2)) {
				// System.out.println("BOOOOOOOOOO");
				position.set(oldPosition);
			}

			// Move Player 1 left.
		} else if ((Gdx.input.isKeyPressed(Input.Keys.LEFT)) && (getPosition().x > 0)) {
			p2MoveLeft.update(dt);
			p2Down = false;
			p2Up = false;
			p2Right = false;
			p2Left = true;
			position.sub((MOVEMENT * dt), velocity.y, 0);

			// Wall Collision
			// Left Corners
			int tileY1 = (int) ((position.y) / tileHeight);
			int tileY2 = (int) ((position.y + p2Height) / tileHeight);
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
		if (p2Up) {
			return p2MoveUp.getFrame();
		}

		if (p2Down) {
			return p2MoveDown.getFrame();
		}

		if (p2Right) {
			return p2MoveRight.getFrame();
		}

		if (p2Left) {
			return p2MoveLeft.getFrame();
		}

		// Default
		return p2MoveDown.getFrame();
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
		p2Texture.dispose();
	}
}