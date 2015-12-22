package com.stango.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MainMenuScreen implements Screen
{
	final Pong game;
	OrthographicCamera camera;
	Sprite startButton;
	Sprite button1, button2, button3;
	
	public MainMenuScreen(final Pong g)
	{
		game = g;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 800);
		startButton = new Sprite(new Texture(Gdx.files.internal("startGameBtn.png")));
		button1 = new Sprite(new Texture(Gdx.files.internal("num1.png")));
		button2 = new Sprite(new Texture(Gdx.files.internal("num2.png")));
		button3 = new Sprite(new Texture(Gdx.files.internal("num3.png")));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		button1.setSize(100, 100);
		button2.setSize(100, 100);
		button3.setSize(100, 100);
		button1.setCenter(350, 300);
		button2.setCenter(500, 300);
		button3.setCenter(650, 300);
		int temp = game.diff - 6;
		camera.update();
		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Pong!", 450, 400);
		game.font.draw(game.batch, "Current Difficulty: " + temp, 5, 795);
		startButton.draw(game.batch);
		button1.draw(game.batch);
		button2.draw(game.batch);
		button3.draw(game.batch);
		game.batch.end(); 
		
		if(Gdx.input.isTouched())
		{
//			System.out.println("Rectangle: " + button1.getBoundingRectangle().getX() + ", " + button1.getBoundingRectangle().getY());
//			System.out.println("Touched: " + Gdx.input.getX() + ", " + Gdx.input.getY());
			if(startButton.getBoundingRectangle().contains(Gdx.input.getX(), 800 - Gdx.input.getY()))
			{
				//System.out.println("hey");
				game.setScreen(new GameScreen(game));
				dispose();
			}
			if(button1.getBoundingRectangle().contains(Gdx.input.getX(), 800 - Gdx.input.getY()))
			{
				game.diff = 7;
			}
			if(button2.getBoundingRectangle().contains(Gdx.input.getX(), 800 - Gdx.input.getY()))
			{
				game.diff = 8;
			}
			if(button3.getBoundingRectangle().contains(Gdx.input.getX(), 800 - Gdx.input.getY()))
			{
				game.diff = 9;
			}
		}
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
		
	}

}
