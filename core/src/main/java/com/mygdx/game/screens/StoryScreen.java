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

public class StoryScreen implements Screen {

    private Main game;

    private SpriteBatch batch;

    // STORY
    private Texture[] stories;

    private int currentStory;

    // SAVE LAST STORY
    public static int savedStoryIndex = 0;

    // BUTTON TEXTURES
    private Texture homeTexture;

    private Texture backTexture;

    private Texture skipTexture;

    // BUTTON AREAS
    private Rectangle homeButton;

    private Rectangle backButton;

    private Rectangle skipButton;

    // CAMERA
    private OrthographicCamera camera;

    private Viewport viewport;

    // HOVER
    private boolean hoverHome;

    private boolean hoverBack;

    private boolean hoverSkip;

    public StoryScreen(Main game) {

        this.game = game;

        batch = new SpriteBatch();

        // LOAD STORIES
        stories = new Texture[4];

        stories[0] =
            new Texture("story1.jpeg");

        stories[1] =
            new Texture("story2.jpeg");

        stories[2] =
            new Texture("story3.jpeg");

        stories[3] =
            new Texture("story4.jpeg");

        // LOAD LAST STORY SAFELY
        currentStory = Math.min(
            savedStoryIndex,
            stories.length - 1
        );

        // BUTTONS
        homeTexture =
            new Texture("home_button.png");

        backTexture =
            new Texture("back_button.png");

        skipTexture =
            new Texture("skip_button.png");

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

        // BUTTON AREA

        // HOME
        homeButton =
            new Rectangle(
                40,
                730,
                100,
                100
            );

        // BACK
        backButton =
            new Rectangle(
                40,
                600,
                100,
                100
            );

        // SKIP
        skipButton =
            new Rectangle(
                1320,
                40,
                120,
                120
            );

        System.out.println(
            "STORY SCREEN OPEN"
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

        // MOUSE POSITION
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

        // HOVER
        hoverHome =
            homeButton.contains(
                mouseX,
                mouseY
            );

        hoverBack =
            backButton.contains(
                mouseX,
                mouseY
            );

        hoverSkip =
            skipButton.contains(
                mouseX,
                mouseY
            );

        batch.begin();

        // DRAW STORY
        batch.draw(
            stories[currentStory],
            0,
            0,
            1536,
            864
        );

        // HOME BUTTON
        batch.draw(
            homeTexture,
            40,
            730,
            hoverHome ? 110 : 100,
            hoverHome ? 110 : 100
        );

        // BACK BUTTON
        batch.draw(
            backTexture,
            40,
            600,
            hoverBack ? 110 : 100,
            hoverBack ? 110 : 100
        );

        // SKIP BUTTON
        batch.draw(
            skipTexture,
            1320,
            40,
            hoverSkip ? 130 : 120,
            hoverSkip ? 130 : 120
        );

        batch.end();

        // CLICK
        if(Gdx.input.justTouched()) {

            // HOME
            if(homeButton.contains(
                mouseX,
                mouseY
            )) {

                savedStoryIndex =
                    currentStory;

                game.setScreen(
                    new MenuScreen(game)
                );

                return;
            }

            // BACK
            if(backButton.contains(
                mouseX,
                mouseY
            )) {

                if(currentStory > 0) {

                    currentStory--;

                    savedStoryIndex =
                        currentStory;
                }

                return;
            }

            // SKIP
            if(skipButton.contains(
                mouseX,
                mouseY
            )) {

                savedStoryIndex =
                    currentStory;

                game.setScreen(
                    new CharacterIntroScreen(game)
                );

                return;
            }

            // NEXT STORY
            currentStory++;

            // SAFE SAVE
            savedStoryIndex =
                Math.min(
                    currentStory,
                    stories.length - 1
                );

            // FINISH STORY
            if(currentStory >= stories.length) {

                currentStory =
                    stories.length - 1;

                savedStoryIndex =
                    currentStory;

                System.out.println(
                    "PINDAH KE CHARACTER INTRO"
                );

                game.setScreen(
                    new CharacterIntroScreen(game)
                );

                return;
            }
        }
    }

    @Override
    public void resize(
        int width,
        int height
    ) {

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

        homeTexture.dispose();

        backTexture.dispose();

        skipTexture.dispose();

        for(Texture texture : stories) {

            texture.dispose();
        }
    }
}
