package com.newage.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Switch {
	private Vector3 position;
	private Texture switchTexture;
	public static Rectangle bounds;
	private Animation switchAnimation;
	private boolean active;

	public Switch(int x, int y) {
		position = new Vector3(x, y, 0);
		switchTexture = new Texture("button.png");
		switchAnimation = new Animation(new TextureRegion(switchTexture), 1, 1, 0);
		bounds = new Rectangle(x, y, switchTexture.getWidth(), switchTexture.getHeight());
		active = false;
	}

	public boolean isActive() {
		return active;
	}

	public void update(float dt) {
		if (active == true)
			switchAnimation.update(dt);
	}

	public Vector3 getPosition() {
		return position;
	}

	public TextureRegion getTexture() {
		return switchAnimation.getFrame();
	}

	public void dispose() {
		switchTexture.dispose();
	}

	public void setOn() {

		active = true;

	}

	public void setOff() {

		active = false;

	}

	public static Rectangle getBounds() {
		return bounds;
	}
}
