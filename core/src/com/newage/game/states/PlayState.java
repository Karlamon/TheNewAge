package com.newage.game.states;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.newage.game.Game;
import com.newage.game.sprites.Enemy;
import com.newage.game.sprites.Player1;
import com.newage.game.sprites.Player2;
import com.newage.game.sprites.Switch;

public class PlayState extends State implements Screen{
	//private Texture background;
	private Switch switch1;
	private Switch switch2;
	private boolean isSwitch1On;
	private boolean isSwitch2On;
	private Player1 player1;
	private Player2 player2;
	private Enemy enemy1;
	private Enemy enemy2;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	protected PlayState(GameStateManager gsm) {
		super(gsm);
		player1 = new Player1(30, 30);
		player2 = new Player2(60, 30);
		enemy1 = new Enemy(400, 400);
		enemy2 = new Enemy(300, 300);
		System.out.println(enemy1.getPosition());
		System.out.println(enemy2.getPosition());
		switch1 = new Switch(300, 300);
		switch2 = new Switch(600, 600);
		isSwitch1On = false;
		isSwitch2On = false;
		//background = new Texture("background.png");
		map = new TmxMapLoader().load("maps.tmx");

		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
	    camera.setToOrtho(false,Game.WIDTH ,Game.HEIGHT );
	    camera.update();
		System.out.println(switch1.bounds);
		System.out.println(switch2.bounds);
	}

	@Override
	protected void handelInput() {

	}

	@Override
	public void update(float dt) {
		handelInput();
		checkButton();
		if (isSwitch1On == true)
			switch1.update(dt);
		if (isSwitch2On == true)
			switch2.update(dt);
		player1.update(dt);
		player2.update(dt);
		enemy1.update(dt);
		enemy2.update(dt);
	}

	@Override
	public void render(SpriteBatch sb) {
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView(camera);
		renderer.render();
		sb.begin();
		/*sb.draw(background, 0, 0, Game.WIDTH, Game.HEIGHT);*/
		sb.draw(switch1.getTexture(), switch1.getPosition().x, switch1.getPosition().y);
		sb.draw(switch2.getTexture(), switch2.getPosition().x, switch2.getPosition().y);
		sb.draw(player1.getTexture(), player1.getPosition().x, player1.getPosition().y);
		sb.draw(player2.getTexture(), player2.getPosition().x, player2.getPosition().y);
		sb.draw(enemy1.getTexture(), enemy1.getPosition().x, enemy1.getPosition().y);
		sb.draw(enemy2.getTexture(), enemy2.getPosition().x, enemy2.getPosition().y);
		sb.end();
	}

	@Override
	public void dispose() {
		//background.dispose();
		switch1.dispose();
		switch2.dispose();
		player1.dispose();
		player2.dispose();
		enemy1.dispose();
		enemy2.dispose();
	}
	
	public void setPositions(){
	}
	
	public void checkButton()
	{
		if(player1.getBounds().overlaps(switch1.bounds)) {	
			switch1.isOn = true;
			System.out.println("Turn button on.");
		}
		if(player1.getBounds().overlaps(switch2.bounds)) {
			switch2.isOn = true;
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
