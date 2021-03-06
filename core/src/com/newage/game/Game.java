package com.newage.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newage.game.states.GameStateManager;
import com.newage.game.states.MainMenuState;

public class Game extends ApplicationAdapter {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 700;
	public static final String TITLE = "The New Age";

	private GameStateManager gsm;
	SpriteBatch batch;

	@Override
	public void create() {
		gsm = new GameStateManager();
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MainMenuState(gsm));
	}

	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {

	}
}
