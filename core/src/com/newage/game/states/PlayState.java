package com.newage.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newage.game.Game;
import com.newage.game.sprites.Player1;
import com.newage.game.sprites.Player2;
import com.newage.game.sprites.Switch;

public class PlayState extends State {
	private Texture background;
	private Switch switch1;
	private Switch switch2;
	private boolean isSwitch1On;
	private boolean isSwitch2On;
	private Player1 player1;
	private Player2 player2;

	protected PlayState(GameStateManager gsm) {
		super(gsm);
		player1 = new Player1(0, 0);
		player2 = new Player2(50, 0);
		switch1 = new Switch(300, 300);
		switch2 = new Switch(600, 600);
		isSwitch1On = false;
		isSwitch2On = false;
		background = new Texture("background.png");
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
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(background, 0, 0, Game.WIDTH, Game.HEIGHT);
		sb.draw(switch1.getTexture(), switch1.getPosition().x, switch1.getPosition().y);
		sb.draw(switch2.getTexture(), switch2.getPosition().x, switch2.getPosition().y);
		sb.draw(player1.getTexture(), player1.getPosition().x, player1.getPosition().y);
		sb.draw(player2.getTexture(), player2.getPosition().x, player2.getPosition().y);
		sb.end();

	}

	@Override
	public void dispose() {
		background.dispose();
		switch1.dispose();
		switch2.dispose();
		player1.dispose();
		player2.dispose();
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

}
