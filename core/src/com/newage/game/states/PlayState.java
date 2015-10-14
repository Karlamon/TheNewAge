package com.newage.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newage.game.Game;
import com.newage.game.sprites.Player1;

public class PlayState extends State {
	private Texture background;
	private Player1 player1;
	
	protected PlayState(GameStateManager gsm) {
		super(gsm);
		player1 = new Player1(50, 300);
		background = new Texture("background.png");
	}

	@Override
	protected void handelInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float dt) {
		handelInput();
		player1.update(dt);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(background, 0, 0, Game.WIDTH, Game.HEIGHT);
		sb.draw(player1.getTexture(), player1.getPosition().x, player1.getPosition().y);
		sb.end();

	}

	@Override
	public void dispose() {
		background.dispose();
		player1.dispose();
	}

}
