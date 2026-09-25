package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Main;

public class MenuScreen implements Screen {

    private Main game;

    private SpriteBatch batch;

    private Texture background;

    private Texture startButton;

    private Rectangle startArea;

    private OrthographicCamera camera;

    private Viewport viewport;

    private boolean hover;

    public MenuScreen(Main game) {

        this.game = game;

        batch = new SpriteBatch();

        background =
            new Texture("menu.png");

        startButton =
            new Texture("start_button.png");

        camera = new OrthographicCamera();

        viewport =
            new FitViewport(1536,864,camera);

        viewport.apply();

        camera.position.set(768,432,0);

        startArea =
            new Rectangle(
                580,
                350,
                350,
                150
            );
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(
            camera.combined
        );

        float mouseX = Gdx.input.getX();

        float mouseY =
            Gdx.graphics.getHeight()
                - Gdx.input.getY();

        mouseX =
            mouseX * 1536
                / Gdx.graphics.getWidth();

        mouseY =
            mouseY * 864
                / Gdx.graphics.getHeight();

        hover =
            startArea.contains(mouseX,mouseY);

        batch.begin();

        batch.draw(
            background,
            0,
            0,
            1536,
            864
        );

        batch.draw(
            startButton,
            580,
            350,
            hover ? 380 : 350,
            hover ? 170 : 150
        );

        batch.end();

        if(Gdx.input.justTouched()) {

            if(startArea.contains(mouseX,mouseY)) {

                System.out.println(
                    "START CLICKED!"
                );

                game.setScreen(
                    new StoryScreen(game)
                );
            }
        }
    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width,height);
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {

        batch.dispose();

        background.dispose();

        startButton.dispose();
    }
}
