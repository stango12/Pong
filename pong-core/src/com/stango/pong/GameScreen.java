package com.stango.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {
	final Pong game;
	private OrthographicCamera camera;
	private Rectangle playerRec;
	private Rectangle cpuRec;
	private Rectangle pokeball;
	
	//int angle = (int) (Math.random() * 361);
	int angle = 45;
	int score = 0;
	private Texture paddle1; //paddle 64x256
	private Texture paddle2;
	private Texture ball; //ball 90x90
	private Sound bounce;
	private Sound plusPoint;
	private Music gameSong;
	
	public GameScreen(final Pong g)
	{
		this.game = g;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 800);
		paddle1 = new Texture(Gdx.files.internal("paddle1.png"));
		paddle2 = new Texture(Gdx.files.internal("paddle1.png"));
		ball = new Texture(Gdx.files.internal("pokeball.png"));
		bounce = Gdx.audio.newSound(Gdx.files.internal("bounce.wav"));
		plusPoint = Gdx.audio.newSound(Gdx.files.internal("plus.wav"));
		gameSong = Gdx.audio.newMusic(Gdx.files.internal("Palette Town Theme.mp3"));
		
		gameSong.setLooping(true);
		gameSong.play();
		
		//player paddle stuff
		playerRec = new Rectangle();
		playerRec.x = 1000 - 64;
		playerRec.y = 400 - 256 / 2;
		playerRec.width = 64;
		playerRec.height = 256;
		
		//cpu paddle stuff
		cpuRec = new Rectangle();
		cpuRec.x = 0;
		cpuRec.y = 400 - 256 / 2;
		cpuRec.width = 64;
		cpuRec.height = 256;
		
		//ball stuff
		pokeball = new Rectangle();
		pokeball.x = 500 - 45;
		pokeball.y = 400 - 45;
		pokeball.width = 90;
		pokeball.height = 90;
	}

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.begin();
		game.font.draw(game.batch, "Score: " + score, 475, 800);
		game.batch.draw(paddle1, playerRec.x, playerRec.y);
		game.batch.draw(paddle2, cpuRec.x, cpuRec.y);
		game.batch.draw(ball, pokeball.x, pokeball.y);
		game.batch.end();
		
		//moving player paddle
		if(Gdx.input.isKeyPressed(Keys.UP))
		{
			playerRec.y += 400 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN))
		{
			playerRec.y -= 400 * Gdx.graphics.getDeltaTime();
		}
		
		//limit check
		if(playerRec.y < 0)
			playerRec.y = 0;
		if(playerRec.y > 800 - 256)
			playerRec.y = 800 - 256;
		
		//ball randomly goes left or right at start(not implemented)
		pokeball.x += Math.cos(Math.toRadians(angle)) * game.diff;
		pokeball.y += Math.sin(Math.toRadians(angle)) * game.diff;
		//out of bounds check
		if(pokeball.x < 0 || pokeball.x > 1000 - 90)
		{
			if(pokeball.x < 0)
			{
				score++;
				plusPoint.play();
			}
			else
				score--;
			pokeball.x = 500 - 45;
		}
		if(pokeball.y < 0 || pokeball.y > 800 - 90)
		{
			angle = 360 - angle;
			bounce.play();
		}
		
		//if ball hits paddle
		if(pokeball.overlaps(playerRec))
		{
			angle = 180 - angle;
			bounce.play();
		}
		if(pokeball.overlaps(cpuRec))
		{
			angle = 180 - angle;
			bounce.play();
		}
		
		//cpu paddle
		//limit check
		if(cpuRec.y < 0)
			cpuRec.y = 0;
		if(cpuRec.y > 800 - 256)
			cpuRec.y = 800 - 256;
		cpuRec.y += Math.sin(Math.toRadians(angle)) * (game.diff / 3);
	}

	@Override
	public void show() {
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		paddle1.dispose();
		paddle2.dispose();
		ball.dispose();
		bounce.dispose();
		plusPoint.dispose();
		gameSong.dispose();
	}		
}
