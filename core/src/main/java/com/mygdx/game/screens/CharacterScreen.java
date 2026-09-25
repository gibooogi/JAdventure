package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Main;
import com.mygdx.game.GameData;

public class CharacterScreen implements Screen {

    private Main game;

    private SpriteBatch batch;

    // BACKGROUND
    private Texture background;

    // CHARACTER
    private Texture[] characters;

    // BUTTONS
    private Texture homeButton;

    private Texture backButton;

    // BUTTON AREA
    private Rectangle homeBounds;

    private Rectangle backBounds;

    // CHARACTER AREA
    private Rectangle[] characterBounds;

    // HOVER
    private boolean hoverHome;

    private boolean hoverBack;

    // SCALE ANIMATION
    private float[] scales;

    // POSITION
    private float[] posX;

    private float[] posY;

    public CharacterScreen(Main game) {

        this.game = game;
    }

    @Override
    public void show() {

        System.out.println("CHARACTER SCREEN OPEN");

        batch = new SpriteBatch();

        // =================================
        // BACKGROUND
        // =================================

        background =
            new Texture(
                "character_select.png"
            );

        // =================================
        // CHARACTER
        // =================================
        // PAKAI CHARACTER PNG LANGSUNG
        // TANPA PLATFORM
        // =================================

        characters = new Texture[] {

            new Texture("jey.png"),
            new Texture("luna.png"),
            new Texture("rexy.png"),
            new Texture("kiro.png"),
            new Texture("niko.png")
        };

        // =================================
        // BUTTON
        // =================================

        homeButton =
            new Texture(
                "home_button.png"
            );

        backButton =
            new Texture(
                "back_button.png"
            );

        // =================================
        // BUTTON AREA
        // =================================

        homeBounds =
            new Rectangle(
                40,
                730,
                100,
                100
            );

        backBounds =
            new Rectangle(
                40,
                600,
                100,
                100
            );

        // =================================
        // POSITION
        // =================================

        posX = new float[] {

            220,
            440,
            660,
            880,
            1100
        };

        posY = new float[] {

            220,
            220,
            220,
            220,
            220
        };

        // =================================
        // SCALE
        // =================================

        scales = new float[5];

        for(int i = 0; i < scales.length; i++) {

            scales[i] = 1f;
        }

        // =================================
        // CHARACTER AREA
        // =================================

        characterBounds =
            new Rectangle[5];

        for(int i = 0; i < 5; i++) {

            characterBounds[i] =
                new Rectangle(

                    posX[i],

                    posY[i],

                    180,

                    320
                );
        }
    }

    @Override
    public void render(float delta) {

        // =================================
        // CLEAR
        // =================================

        Gdx.gl.glClearColor(
            0,
            0,
            0,
            1
        );

        Gdx.gl.glClear(
            GL20.GL_COLOR_BUFFER_BIT
        );

        // =================================
        // MOUSE
        // =================================

        float mouseX =
            Gdx.input.getX();

        float mouseY =
            Gdx.graphics.getHeight()
                - Gdx.input.getY();

        // =================================
        // HOVER BUTTON
        // =================================

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

        // =================================
        // HOVER CHARACTER
        // =================================

        for(int i = 0; i < 5; i++) {

            if(
                characterBounds[i]
                    .contains(
                        mouseX,
                        mouseY
                    )
            ) {

                // MEMBESAR
                scales[i] +=
                    (1.1f - scales[i])
                        * 8
                        * delta;

            } else {

                // NORMAL
                scales[i] +=
                    (1f - scales[i])
                        * 8
                        * delta;
            }
        }

        batch.begin();

        // =================================
        // BACKGROUND
        // =================================

        batch.draw(

            background,

            0,

            0,

            Gdx.graphics.getWidth(),

            Gdx.graphics.getHeight()
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
        // CHARACTER
        // =================================

        for(int i = 0; i < 5; i++) {

            float scale =
                scales[i];

            // SIZE
            float width =
                180 * scale;

            float height =
                320 * scale;

            // POSITION
            float drawX =
                posX[i]
                    - (width - 180) / 2;

            float drawY =
                posY[i]
                    - (height - 320) / 2;

            // DRAW CHARACTER
            batch.draw(

                characters[i],

                drawX,

                drawY,

                width,

                height
            );
        }

        batch.end();

        // =================================
        // CLICK
        // =================================

        if(Gdx.input.justTouched()) {

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

                game.setScreen(
                    new CharacterIntroScreen(game)
                );

                return;
            }

            // CHARACTER CLICK
            for(int i = 0; i < 5; i++) {

                if(
                    characterBounds[i]
                        .contains(
                            mouseX,
                            mouseY
                        )
                ) {

                    GameData.selectedCharacter = i;

                    System.out.println(
                        "SELECT CHARACTER : "
                            + i
                    );

                    game.setScreen(
                        new LevelScreen(game)
                    );

                    return;
                }
            }
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

        // =================================
        // SAFE DISPOSE
        // =================================

        if(batch != null)
            batch.dispose();

        if(background != null)
            background.dispose();

        if(homeButton != null)
            homeButton.dispose();

        if(backButton != null)
            backButton.dispose();

        if(characters != null) {

            for(Texture texture
                : characters) {

                if(texture != null)
                    texture.dispose();
            }
        }
    }
}
