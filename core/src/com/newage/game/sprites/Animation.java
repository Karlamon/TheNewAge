package com.newage.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
public class Animation {
	private Array<TextureRegion> frames;
	private float maxFrameTime;
	private float currentFrameTime;
	private int frameCount;
	private int frame;

	public Animation(TextureRegion region, int frameCount, float cycleTime, int row) {
		frames = new Array<TextureRegion>();
		int frameWidth = region.getRegionWidth() / frameCount;
		int frameHeight = region.getRegionHeight() / frameCount;
		switch(row) {
		
		// Walk Down animation
		case 0:
			for (int i = 0; i < frameCount; i++) {
				frames.add(new TextureRegion(region, i * frameWidth, 0 * frameHeight, frameWidth, frameHeight));
			}
			break;

			// Walk Left animation
		case 1:
			for (int i = 0; i < frameCount; i++) {
				frames.add(new TextureRegion(region, i * frameWidth, 1 * frameHeight, frameWidth, frameHeight));
			}
			break;

			// Walk Right animation
		case 2:
			for (int i = 0; i < frameCount; i++) {
				frames.add(new TextureRegion(region, i * frameWidth, 2 * frameHeight, frameWidth, frameHeight));
			}
			break;

			// Walk Up animation
		case 3:
			for (int i = 0; i < frameCount; i++) {
				frames.add(new TextureRegion(region, i * frameWidth, 3 * frameHeight, frameWidth, frameHeight));
			}
			break;

			// No animation, player stationary
		default:
			break;
		}
		this.frameCount = frameCount;
		maxFrameTime = cycleTime / frameCount;
		frame = 0;
	}

	public void update(float dt) {
		currentFrameTime += dt;
		if (currentFrameTime > maxFrameTime) {
			frame++;
			currentFrameTime = 0;
		}
		if (frame >= frameCount) {
			frame = 0;
		}
	}

	public TextureRegion getFrame() {
		return frames.get(frame);
	}
}
