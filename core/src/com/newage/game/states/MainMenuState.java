package com.newage.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.newage.game.Game;

public class MainMenuState extends State {
	private Texture background;
	private Texture playButton;
	
	public MainMenuState(GameStateManager gsm) {
		super(gsm);
		
		
		
		background = new Texture("background.png");
		playButton = new Texture("play_button.png");
	}

	@Override
	protected void handelInput() {
		if ((Gdx.input.isKeyPressed(Input.Keys.ENTER)) || (Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {
			gsm.set(new PlayState(gsm));
		}
	}

	@Override
	public void update(float dt) {
		handelInput();
	}

	@Override
	public void render(SpriteBatch sb) {

		sb.begin();
		sb.draw(background, 0, 0, Game.WIDTH, Game.HEIGHT);
		sb.draw(playButton, (Game.WIDTH / 2) - (playButton.getWidth() / 2), Game.HEIGHT / 2);
		sb.end();
	}

	@Override
	public void dispose() {
		background.dispose();
		playButton.dispose();
	}

}
