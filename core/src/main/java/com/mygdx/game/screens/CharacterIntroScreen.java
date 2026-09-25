package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Main;

public class CharacterIntroScreen implements Screen {

    private final Main game;

    private SpriteBatch batch;

    // BACKGROUND + CHARACTER
    private Texture[] introBackgrounds;

    // TEXT PNG
    private Texture[] descTextures;

    // BUTTONS
    private Texture skipButton;

    private Texture homeButton;

    private Texture backButton;

    // BUTTON AREA
    private Rectangle skipBounds;

    private Rectangle homeBounds;

    private Rectangle backBounds;

    // CURRENT PAGE
    private int currentSlide = 0;

    // FADE
    private float textAlpha = 0f;

    // TIMER
    private float timer = 0f;

    // HOVER
    private boolean hoverSkip;

    private boolean hoverHome;

    private boolean hoverBack;

    public CharacterIntroScreen(Main game) {

        this.game = game;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();

        System.out.println(
            "CHARACTER INTRO OPEN"
        );

        // BACKGROUND + CHARACTER
        introBackgrounds = new Texture[] {

            new Texture("intro1.png"),
            new Texture("intro2.png"),
            new Texture("intro3.png"),
            new Texture("intro4.png"),
            new Texture("intro5.png")
        };

        // TEXT PNG
        descTextures = new Texture[] {

            new Texture("desc1.png"),
            new Texture("desc2.png"),
            new Texture("desc3.png"),
            new Texture("desc4.png"),
            new Texture("desc5.png")
        };

        // BUTTONS
        skipButton =
            new Texture("skip_button.png");

        homeButton =
            new Texture("home_button.png");

        backButton =
            new Texture("back_button.png");

        // BUTTON AREA

        // HOME
        homeBounds =
            new Rectangle(
                40,
                730,
                100,
                100
            );

        // BACK
        backBounds =
            new Rectangle(
                40,
                600,
                100,
                100
            );

        // SKIP
        skipBounds = new Rectangle(
            1320,
            50,
            100,
            100
        );
    }

    @Override
    public void render(float delta) {

        // CLEAR
        Gdx.gl.glClearColor(
            0,
            0,
            0,
            1
        );

        Gdx.gl.glClear(
            GL20.GL_COLOR_BUFFER_BIT
        );

        // TIMER
        timer += delta;

        // FADE IN
        if(textAlpha < 1f) {

            textAlpha += delta * 0.7f;
        }

        // LIMIT
        if(textAlpha > 1f) {

            textAlpha = 1f;
        }

        // MOUSE
        float mouseX =
            Gdx.input.getX();

        float mouseY =
            Gdx.graphics.getHeight()
                - Gdx.input.getY();

        // HOVER
        hoverSkip =
            skipBounds.contains(
                mouseX,
                mouseY
            );

        hoverHome =
            homeBounds.contains(
                mouseX,
                mouseY
            );

        hoverBack =
            backBounds.contains(
                mouseX,
                mouseY
            );

        batch.begin();

        // =================================
        // DRAW BACKGROUND
        // =================================

        batch.draw(
            introBackgrounds[currentSlide],
            0,
            0,
            1536,
            864
        );

        // =================================
        // TEXT FADE
        // =================================

        batch.setColor(
            1,
            1,
            1,
            textAlpha
        );

        // =================================
        // DRAW DESC
        // =================================

        batch.draw(

            descTextures[currentSlide],

            // X
            760,

            // Y
            180,

            // WIDTH
            700,

            // HEIGHT
            620
        );

        // RESET COLOR
        batch.setColor(
            1,
            1,
            1,
            1
        );

        // =================================
        // HOME BUTTON
        // =================================

        batch.draw(

            homeButton,

            40,

            730,

            hoverHome ? 110 : 100,

            hoverHome ? 110 : 100
        );

        // =================================
        // BACK BUTTON
        // =================================

        batch.draw(

            backButton,

            40,

            600,

            hoverBack ? 110 : 100,

            hoverBack ? 110 : 100
        );

        // =================================
        // SKIP BUTTON
        // =================================

        batch.draw(
            skipButton,
            1320,
            50,
            hoverSkip ? 110 : 100,
            hoverSkip ? 110 : 100
        );
        batch.end();

        // =================================
        // CLICK
        // =================================

        if(
            Gdx.input.justTouched()
                && timer > 0.5f
        ) {

            // HOME
            if(
                homeBounds.contains(
                    mouseX,
                    mouseY
                )
            ) {

                game.setScreen(
                    new MenuScreen(game)
                );

                return;
            }

            // BACK
            if(
                backBounds.contains(
                    mouseX,
                    mouseY
                )
            ) {

                // BALIK KE STORY
                if(currentSlide == 0) {

                    game.setScreen(
                        new StoryScreen(game)
                    );

                    return;
                }

                // SLIDE SEBELUMNYA
                currentSlide--;

                textAlpha = 0f;

                timer = 0f;

                return;
            }

            // SKIP
            if(
                skipBounds.contains(
                    mouseX,
                    mouseY
                )
            ) {

                game.setScreen(
                    new CharacterScreen(game)
                );

                return;
            }

            // NEXT SLIDE
            currentSlide++;

            // RESET
            textAlpha = 0f;

            timer = 0f;

            // FINISH
            if(currentSlide >= introBackgrounds.length) {

                game.setScreen(
                    new CharacterScreen(game)
                );

                return;
            }
        }

        // SPACE SKIP
        if(
            Gdx.input.isKeyJustPressed(
                Input.Keys.SPACE
            )
        ) {

            game.setScreen(
                new CharacterScreen(game)
            );
        }
    }

    @Override
    public void resize(
        int width,
        int height
    ) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        batch.dispose();

        // BACKGROUND
        for(Texture texture
            : introBackgrounds) {

            texture.dispose();
        }

        // DESC
        for(Texture texture
            : descTextures) {

            texture.dispose();
        }

        skipButton.dispose();

        homeButton.dispose();

        backButton.dispose();
    }
}
