package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.Input;

import com.mygdx.game.Main;
import com.mygdx.game.player.Player;
import com.mygdx.game.objects.Platform;
import com.mygdx.game.objects.Coin;
import com.mygdx.game.objects.EnemyJerry;
import com.mygdx.game.objects.EnemyBijo;



import com.mygdx.game.objects.Flag;
import com.mygdx.game.objects.HeartSystem;

import com.mygdx.game.screens.MenuScreen;

public class Level2Screen implements Screen {

    private Main game;

    private SpriteBatch batch;
    private BitmapFont font;

    private OrthographicCamera camera;
    private OrthographicCamera uiCamera;

    private Texture sky;

    private Texture cloud;

    private Texture groundTile;

    private Texture platformBatu;

    private Texture platformMerah;

    private Texture coinTexture;

    private Texture enemyJerryTexture;

    private Texture enemyJerryKananTexture;
    private Texture enemyJerryKiriTexture;

    private Texture enemyBijoTexture;

    private Texture enemyBijoKananTexture;
    private Texture enemyBijoKiriTexture;

    private Texture heartTexture;

    private Texture flagTexture;

    private Player player;

    private Platform[] platforms;

    private Platform[] redPlatforms;

    private Coin[] coins;

    private EnemyJerry enemyJerry;

    private EnemyBijo enemyBijo;

    private Flag finishFlag;

    private HeartSystem heartSystem;

    private int score = 0;
    private float damageCooldown = 0;

    private float timeLeft = 13;

    private boolean gameOver = false;

    private boolean levelComplete = false;

    private Texture gameOverPopup;

    private Texture levelCompletePopup;

    private Texture yesButton;

    private Texture noButton;

    private Rectangle yesBounds;

    private Rectangle noBounds;

    private final float LEVEL_WIDTH = 2800;

    public Level2Screen(Main game) {

        this.game = game;

        batch = new SpriteBatch();
        font = new BitmapFont();

        // SKY
        sky =
            new Texture(
                "sky.png"
            );

        // CLOUD
        cloud =
            new Texture(
                "cloud.png"
            );

        // GROUND
        groundTile =
            new Texture(
                "ground_tile.png"
            );

        // PLATFORM
        platformBatu =
            new Texture(
                "platform_batu.png"
            );

        platformMerah =
            new Texture(
                "platform_bmerah.png"
            );

        // COIN
        coinTexture =
            new Texture(
                "coin.png"
            );

        enemyJerryKananTexture =
            new Texture("enemy_jerrykanan.png");

        enemyJerryKiriTexture =
            new Texture("enemy_jerrykiri.png");

        enemyBijoKananTexture =
            new Texture("enemy_bijokanan.png");

        enemyBijoKiriTexture =
            new Texture("enemy_bijokiri.png");

        heartTexture =
            new Texture(
                "heartfull.png"
            );

        flagTexture =
            new Texture(
                "finish_flag.png"
            );

        gameOverPopup =
            new Texture(
                "game_over.png"
            );

        levelCompletePopup =
            new Texture(
                "level_complete.png"
            );

        yesButton =
            new Texture(
                "yes.png"
            );

        noButton =
            new Texture(
                "no.png"
            );

        yesBounds =
            new Rectangle(
                700,
                340,
                160,
                60
            );

        noBounds =
            new Rectangle(
                700,
                250,
                160,
                60
            );


        // PLAYER
        player =
            new Player();

        // PLATFORM LEVEL 1
        platforms =
            new Platform[] {

                new Platform(
                    platformBatu,
                    190,
                    80,
                    90,
                    90
                ),

                new Platform(
                    platformBatu,
                    410,
                    80,
                    90,
                    90
                ),

                new Platform(
                    platformBatu,
                    630,
                    80,
                    90,
                    90
                ),

                new Platform(
                    platformBatu,
                    850,
                    80,
                    90,
                    90
                )
            };

        redPlatforms =
            new Platform[] {

                // TANJAK KIRI
                new Platform(platformMerah, 1450, 80, 70, 70),
                new Platform(platformMerah, 1520, 150, 70, 70),
                new Platform(platformMerah, 1590, 220, 70, 70),
                new Platform(platformMerah, 1660, 290, 70, 70),

                // ATAS
                new Platform(platformMerah, 1730, 360, 70, 70),
                new Platform(platformMerah, 1800, 360, 70, 70),
                new Platform(platformMerah, 1870, 360, 70, 70),
                new Platform(platformMerah, 1940, 360, 70, 70),

                // TURUN KANAN
                new Platform(platformMerah, 2070, 290, 70, 70),
                new Platform(platformMerah, 2200, 360, 70, 70),
                new Platform(platformMerah, 2330, 290, 70, 70),
                new Platform(platformMerah, 2460, 360, 70, 70)
            };

        // COIN DI ATAS PLATFORM
        coins =
            new Coin[] {

                new Coin(
                    coinTexture,
                    220,
                    165
                ),

                new Coin(
                    coinTexture,
                    430,
                    165
                ),

                new Coin(
                    coinTexture,
                    660,
                    165
                ),

                new Coin(
                    coinTexture,
                    880,
                    165
                )
            };

        enemyJerry =
            new EnemyJerry(
                enemyJerryKananTexture,
                enemyJerryKiriTexture,
                1200,
                80,
                1100,
                1400
            );

        enemyBijo =
            new EnemyBijo(
                enemyBijoKananTexture,
                enemyBijoKiriTexture,
                1650,
                70,
                1550,
                1800
            );

        finishFlag =
            new Flag(

                flagTexture,

                2700,

                95
            );

        heartSystem =
            new HeartSystem(

                heartTexture
            );

        // CAMERA
        camera =
            new OrthographicCamera();

        camera.setToOrtho(
            false,
            1536,
            864
        );

        uiCamera =
            new OrthographicCamera();

        uiCamera.setToOrtho(
            false,
            1536,
            864
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

        player.update(delta);

        if(!gameOver && !levelComplete)
        {
            timeLeft -= delta;
        }

        player.clampPosition(

            LEVEL_WIDTH

        );

        if(
            Gdx.input.isKeyJustPressed(
                Input.Keys.ESCAPE
            )
        )

            if(player.getX() > LEVEL_WIDTH - 90) {

                player.setX(
                    LEVEL_WIDTH - 90
                );
            }

        enemyJerry.update(delta);

        enemyBijo.update(delta);

        if(damageCooldown > 0) {

            damageCooldown -= delta;
        }

        // =========================
        // COLLISON PLATFORM BATU
        // =========================

        for(
            Platform platform
            : platforms
        ) {

            Rectangle playerBounds =
                player.getBounds();

            Rectangle platformBounds =
                platform.getBounds();

            if(
                playerBounds.overlaps(
                    platformBounds
                )
            ) {

                float playerTopBefore =
                    player.getPreviousY() + 120;

                float platformTop =
                    platform.getY() + 90;

                if(
                    playerTopBefore >= platformTop
                        &&
                        player.getVelocityY() <= 0
                ) {

                    player.landOnPlatform(
                        platformTop
                    );
                }
            }
        }

// =========================
// PLATFORM MERAH
// =========================

        for(
            Platform redPlatform
            : redPlatforms
        ) {

            Rectangle playerBounds =
                player.getBounds();

            Rectangle platformBounds =
                redPlatform.getBounds();

            if(
                playerBounds.overlaps(
                    platformBounds
                )
            ) {

                float playerTopBefore =
                    player.getPreviousY() + 120;

                float platformTop =
                    redPlatform.getY() + 70;

                if(
                    playerTopBefore >= platformTop
                        &&
                        player.getVelocityY() <= 0
                ) {

                    player.landOnPlatform(
                        platformTop
                    );
                }
            }
        }

        // =========================
        // COLLISION COIN
        // =========================


        for(
            Coin coin
            : coins
        ) {

            if(
                !coin.isCollected()
                    &&
                    player.getBounds()
                        .overlaps(
                            coin.getBounds()
                        )
            ) {

                coin.collect();

                score += 10;

                System.out.println(
                    "COIN : "
                        + score
                );
            }
        }

        // =========================
        // COLLISION JERRY
        // =========================

        if(
            player.getBounds()
                .overlaps(
                    enemyJerry.getBounds()
                )
                &&
                damageCooldown <= 0
        ) {

            heartSystem.damage();

            damageCooldown = 1.0f;

            System.out.println(
                heartSystem.getHearts()
            );
        }

        // =========================
        // COLLISION BIJO
        // =========================

        if(
            player.getBounds()
                .overlaps(
                    enemyBijo.getBounds()
                )
                &&
                damageCooldown <= 0
        ) {

            heartSystem.damage();

            damageCooldown = 1.0f;

            System.out.println(
                heartSystem.getHearts()
            );
        }

        if(timeLeft <= 0)
        {
            timeLeft = 0;
            gameOver = true;
        }

        // =========================
        // GAME OVER
        // =========================

        if(
            heartSystem.isDead()
        )
        {
            gameOver = true;
        }

        // =========================
        // LEVEL COMPLETE
        // =========================

        if(
            player.getBounds()
                .overlaps(
                    finishFlag.getBounds()
                )
        )
        {
            levelComplete = true;
        }

        // =========================
        // CAMERA
        // =========================

        float cameraX = 768;

        if(
            player.getX()
                > 768
        ) {

            cameraX =
                player.getX();
        }

        float maxCameraX =
            LEVEL_WIDTH - 768;

        if(
            cameraX
                > maxCameraX
        ) {

            cameraX =
                maxCameraX;
        }

        camera.position.x =
            cameraX;

        camera.position.y =
            432;

        camera.update();

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

        batch.setProjectionMatrix(
            camera.combined
        );

        boolean hoverYes =

            yesBounds.contains(

                mouseX,

                mouseY

            );

        boolean hoverNo =

            noBounds.contains(

                mouseX,

                mouseY

            );

        batch.begin();

        // SKY
        batch.draw(
            sky,
            0,
            0,
            LEVEL_WIDTH,
            864
        );

        // CLOUD
        batch.draw(
            cloud,
            250,
            650,
            180,
            120
        );

        batch.draw(
            cloud,
            850,
            730,
            180,
            120
        );

        batch.draw(
            cloud,
            1500,
            600,
            180,
            120
        );

        batch.draw(
            cloud,
            1900,
            700,
            180,
            120
        );

        batch.draw(
            cloud,
            2500,
            590,
            180,
            120
        );

        // GROUND
        for(
            int x = 0;
            x < 45;
            x++
        ) {

            batch.draw(
                groundTile,
                x * 93,
                0,
                93,
                93
            );
        }

        // PLATFORM
        for(
            Platform platform
            : platforms
        ) {

            platform.draw(batch);
        }

        for(
            Platform platform
            : redPlatforms
        ) {
            platform.draw(batch);
        }

        // COIN
        for(
            Coin coin
            : coins
        ) {

            coin.draw(
                batch
            );
        }

        // JERRY

        enemyJerry.draw(

            batch

        );

        // BIJO
        enemyBijo.draw(

            batch

        );

        // FLAG
        finishFlag.draw(

            batch

        );


        // PLAYER
        player.draw(
            batch
        );

        batch.end();

        // =========================
        // UI
        // =========================

        batch.setProjectionMatrix(
            uiCamera.combined
        );

        batch.begin();

        // HEART
        heartSystem.draw(
            batch
        );

        font.draw(
            batch,
            "Score : " + score,
            1050,
            840
        );

        font.draw(
            batch,
            "Time : " + (int)timeLeft,
            1250,
            840
        );


        if(gameOver)
        {
            batch.draw(
                gameOverPopup,
                518,
                200,
                500,
                420
            );

            if(hoverYes)
            {
                batch.draw(
                    yesButton,
                    695,
                    335,
                    170,
                    70
                );
            }
            else
            {
                batch.draw(
                    yesButton,
                    700,
                    340,
                    160,
                    60
                );
            }

            if(hoverNo)
            {
                batch.draw(
                    noButton,
                    695,
                    245,
                    170,
                    70
                );
            }
            else
            {
                batch.draw(
                    noButton,
                    700,
                    250,
                    160,
                    60
                );
            }
        }

        if(levelComplete)
        {
            batch.draw(
                levelCompletePopup,
                518,
                200,
                500,
                420
            );

            if(hoverYes)
            {
                batch.draw(
                    yesButton,
                    695,
                    335,
                    170,
                    70
                );
            }
            else
            {
                batch.draw(
                    yesButton,
                    700,
                    340,
                    160,
                    60
                );
            }

            if(hoverNo)
            {
                batch.draw(
                    noButton,
                    695,
                    245,
                    170,
                    70
                );
            }
            else
            {
                batch.draw(
                    noButton,
                    700,
                    250,
                    160,
                    60
                );
            }
        }

        batch.end();

        if(
            Gdx.input.justTouched()
        )
        {
            if(gameOver || levelComplete)
            {
                if(
                    yesBounds.contains(
                        mouseX,
                        mouseY
                    )
                )
                {

                    System.out.println("YES");

                    game.setScreen(
                        new LevelScreen(game)
                    );

                    return;
                }
                else if(
                    noBounds.contains(
                        mouseX,
                        mouseY
                    )
                )
                {
                    game.setScreen(
                        new MenuScreen(game)
                    );

                    return;
                }
            }
        }


    }

    @Override
    public void show() {

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

        font.dispose();

        batch.dispose();

        sky.dispose();

        cloud.dispose();

        groundTile.dispose();

        platformBatu.dispose();

        coinTexture.dispose();

        enemyJerryTexture.dispose();

        enemyBijoTexture.dispose();

        heartTexture.dispose();

        flagTexture.dispose();

        player.dispose();

        gameOverPopup.dispose();

        levelCompletePopup.dispose();

        yesButton.dispose();

        noButton.dispose();
    }
}
