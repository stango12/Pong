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
	Sprite button1;
	
	public MainMenuScreen(final Pong g)
	{
		game = g;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 800);
		button1 = new Sprite(new Texture(Gdx.files.internal("startGameBtn.png")));
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
		
		camera.update();
		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Pong", 450, 400, 50.0f, 1, false);
		button1.draw(game.batch);
		game.batch.end(); 
		
		if(Gdx.input.isTouched())
		{
//			System.out.println("Rectangle: " + button1.getBoundingRectangle().getX() + ", " + button1.getBoundingRectangle().getY());
//			System.out.println("Touched: " + Gdx.input.getX() + ", " + Gdx.input.getY());
			if(button1.getBoundingRectangle().contains(Gdx.input.getX(), 800 - Gdx.input.getY()))
			{
				//System.out.println("hey");
				game.setScreen(new GameScreen(game));
				dispose();
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
