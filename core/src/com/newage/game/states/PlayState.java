package com.newage.game.states;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.newage.game.Game;
import com.newage.game.sprites.Enemy;
import com.newage.game.sprites.Player1;
import com.newage.game.sprites.Player2;
import com.newage.game.sprites.Switch;

public class PlayState extends State implements Screen {
	// private Texture background;
	private Switch switch1, switch2, switch3, switch4, switch5, switch6, switch7, switch8;
	private Player1 player1;
	private Player2 player2;
	private Enemy enemy1;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	protected PlayState(GameStateManager gsm) {
		super(gsm);
		map = new TmxMapLoader().load("maps.tmx");
		player1 = new Player1(33, 33, (TiledMapTileLayer) map.getLayers().get(0));
		player2 = new Player2(33, 3 * 33, (TiledMapTileLayer) map.getLayers().get(0));
		enemy1 = new Enemy(200, 200, (TiledMapTileLayer) map.getLayers().get(0));
		switch1 = new Switch(300, 250);
		switch2 = new Switch(600, 600);
		// background = new Texture("background.png");

		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Game.WIDTH, Game.HEIGHT);
		camera.update();
	}

	@Override
	protected void handelInput() {

	}

	@Override
	public void update(float dt) {
		handelInput();
		checkButton();
		if (switch1.isActive())
			switch1.update(dt);
		if (switch2.isActive())
			switch2.update(dt);
		player1.update(dt);
		player2.update(dt);
		enemy1.update(dt);
	}

	@Override
	public void render(SpriteBatch sb) {
		// Gdx.gl.glClearColor(0, 0, 0, 1);
		// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView(camera);
		renderer.render();
		sb.begin();
		/* sb.draw(background, 0, 0, Game.WIDTH, Game.HEIGHT); */
		sb.draw(switch1.getTexture(), switch1.getPosition().x, switch1.getPosition().y);
		sb.draw(switch2.getTexture(), switch2.getPosition().x, switch2.getPosition().y);
		sb.draw(player1.getTexture(), player1.getPosition().x, player1.getPosition().y);
		sb.draw(player2.getTexture(), player2.getPosition().x, player2.getPosition().y);
		sb.draw(enemy1.getTexture(), enemy1.getPosition().x, enemy1.getPosition().y);
		sb.end();

	}

	@Override
	public void dispose() {
		// background.dispose();
		switch1.dispose();
		switch2.dispose();
		player1.dispose();
		player2.dispose();
		enemy1.dispose();
	}

	public void setPositions() {
	}

	public void checkButton() {
		if (player1.getBounds().overlaps(switch1.bounds)) {
			switch1.setOn();
			System.out.println("Turn button on.");
		}
		if (player1.getBounds().overlaps(switch2.bounds)) {
			switch2.setOn();
			System.out.println("Turn button on.");
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
}
