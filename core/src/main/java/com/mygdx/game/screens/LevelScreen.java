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
import com.mygdx.game.GameData;

import com.mygdx.game.screens.Level2Screen;
import com.mygdx.game.screens.Level3Screen;

public class LevelScreen implements Screen {

    private Main game;

    private SpriteBatch batch;

    // BACKGROUND
    private Texture background;

    // UI
    private Texture homeTexture;
    private Texture backTexture;

    // LEVELS
    private Texture level1Texture;
    private Texture level2Texture;
    private Texture level3Texture;


    // BUTTON AREA
    private Rectangle homeButton;
    private Rectangle backButton;

    // LEVEL AREA
    private Rectangle level1;
    private Rectangle level2;
    private Rectangle level3;


    // CAMERA
    private OrthographicCamera camera;

    private Viewport viewport;

    public LevelScreen(Main game) {

        this.game = game;

        batch = new SpriteBatch();

        // BACKGROUND
        background =
            new Texture("level_select.png");

        // UI
        homeTexture =
            new Texture("home_button.png");

        backTexture =
            new Texture("back_button.png");

        // LEVEL TEXTURE
        level1Texture =
            new Texture("level1.png");

        level2Texture =
            new Texture("level2.png");

        level3Texture =
            new Texture("level3.png");


        // CAMERA
        camera = new OrthographicCamera();

        viewport =
            new FitViewport(
                1536,
                864,
                camera
            );

        viewport.apply();

        camera.position.set(
            768,
            432,
            0
        );

        // UI AREA
        homeButton =
            new Rectangle(
                40,
                730,
                100,
                100
            );

        backButton =
            new Rectangle(
                40,
                600,
                100,
                100
            );

        // LEVEL POSITION
        level1 =
            new Rectangle(
                180,
                420,
                120,
                120
            );

        level2 =
            new Rectangle(
                350,
                420,
                120,
                120
            );

        level3 =
            new Rectangle(
                520,
                420,
                120,
                120
            );

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(
            0,
            0,
            0,
            1
        );

        Gdx.gl.glClear(
            GL20.GL_COLOR_BUFFER_BIT
        );

        camera.update();

        batch.setProjectionMatrix(
            camera.combined
        );

        // MOUSE
        float mouseX =
            Gdx.input.getX();

        float mouseY =
            Gdx.graphics.getHeight()
                - Gdx.input.getY();

        mouseX =
            mouseX * 1536
                / Gdx.graphics.getWidth();

        mouseY =
            mouseY * 864
                / Gdx.graphics.getHeight();

        batch.begin();

        // BACKGROUND
        batch.draw(
            background,
            0,
            0,
            1536,
            864
        );

        // HOME
        batch.draw(
            homeTexture,
            40,
            730,
            homeButton.contains(mouseX,mouseY)
                ? 110 : 100,
            homeButton.contains(mouseX,mouseY)
                ? 110 : 100
        );

        // BACK
        batch.draw(
            backTexture,
            40,
            600,
            backButton.contains(mouseX,mouseY)
                ? 110 : 100,
            backButton.contains(mouseX,mouseY)
                ? 110 : 100
        );

        // LEVEL 1
        batch.draw(
            level1Texture,
            level1.contains(mouseX,mouseY)
                ? 170 : 180,
            level1.contains(mouseX,mouseY)
                ? 410 : 420,
            level1.contains(mouseX,mouseY)
                ? 140 : 120,
            level1.contains(mouseX,mouseY)
                ? 140 : 120
        );

        // LEVEL 2
        batch.draw(
            level2Texture,
            level2.contains(mouseX,mouseY)
                ? 340 : 350,
            level2.contains(mouseX,mouseY)
                ? 410 : 420,
            level2.contains(mouseX,mouseY)
                ? 140 : 120,
            level2.contains(mouseX,mouseY)
                ? 140 : 120
        );

        // LEVEL 3
        batch.draw(
            level3Texture,
            level3.contains(mouseX,mouseY)
                ? 510 : 520,
            level3.contains(mouseX,mouseY)
                ? 410 : 420,
            level3.contains(mouseX,mouseY)
                ? 140 : 120,
            level3.contains(mouseX,mouseY)
                ? 140 : 120
        );



        batch.end();

        // CLICK
        if(Gdx.input.justTouched()) {

            // HOME
            if(homeButton.contains(mouseX,mouseY)) {

                game.setScreen(
                    new MenuScreen(game)
                );

                return;
            }

            // BACK
            if(backButton.contains(mouseX,mouseY)) {

                game.setScreen(
                    new CharacterScreen(game)
                );

                return;
            }

            // LEVEL 1
            if(level1.contains(mouseX,mouseY)) {

                GameData.selectedLevel = 1;

                game.setScreen(
                    new GameScreen(game)
                );

                return;
            }

            // LEVEL 2
            if(level2.contains(mouseX,mouseY)) {

                GameData.selectedLevel = 2;

                game.setScreen(
                    new Level2Screen(game)
                );

                return;
            }

            // LEVEL 3
            if(level3.contains(mouseX,mouseY)) {

                GameData.selectedLevel = 3;

                game.setScreen(
                    new Level3Screen(game)
                );

                return;
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

        homeTexture.dispose();

        backTexture.dispose();

        level1Texture.dispose();
        level2Texture.dispose();
        level3Texture.dispose();

    }
}
