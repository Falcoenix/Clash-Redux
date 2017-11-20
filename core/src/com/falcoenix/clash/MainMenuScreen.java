package com.falcoenix.clash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen
{
	final ClashGame game;
	final Skin skin;
	
	private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
	
	private Stage stage;
	
	private MainMenuWindow window;
	
	OrthographicCamera camera;
	
	InputMultiplexer inputMultiplexer;

	public MainMenuScreen(final ClashGame game, final Skin skin)
	{
		this.game = game;
		this.skin = skin;
		
		batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("sleeping_dragon_by_mocaran.jpg"));
        sprite = new Sprite(texture);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		stage = new Stage(new ScreenViewport());
		
		window = new MainMenuWindow(this.game, this.skin);
		stage.addActor(window);
		
		inputMultiplexer = new InputMultiplexer();
	}
	
	@Override
	public void show()
	{
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void pause()
	{
		inputMultiplexer.removeProcessor(stage);
	}

	@Override
	public void resume()
	{
		inputMultiplexer.addProcessor(stage);
	}

	@Override
	public void hide()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose()
	{
		texture.dispose();
		sprite.getTexture().dispose();
		batch.dispose();
	}
}
