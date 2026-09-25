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

public class Level3Screen implements Screen {

    private Main game;

    private SpriteBatch batch;
    private Music bgm;

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

    private Platform[] pipePlatforms;

    private Coin[] coins;

    private EnemyJerry enemyJerry;

    private EnemyBijo enemyBijo;

    private Flag finishFlag;

    private HeartSystem heartSystem;

    private int score = 0;
    private float damageCooldown = 0;

    private float timeLeft = 10;

    private boolean gameOver = false;

    private boolean levelComplete = false;

    private Texture gameOverPopup;

    private Texture levelCompletePopup;

    private Texture yesButton;

    private Texture noButton;

    private Texture pipeTexture;

    private Texture lavaTexture;


    private Rectangle yesBounds;

    private Rectangle noBounds;

    private final float LEVEL_WIDTH = 3500;

    public Level3Screen(Main game) {

        this.game = game;

        batch = new SpriteBatch();
        bgm =
            Gdx.audio.newMusic(
                Gdx.files.internal(
                    "bgm.mp3"
                )
            );

        bgm.setLooping(true);

        bgm.setVolume(0.4f);

        bgm.play();

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
            new Texture(
                "enemy_bijokanan.png"
            );

        enemyBijoKiriTexture =
            new Texture(
                "enemy_bijokiri.png"
            );

        heartTexture =
            new Texture(
                "heartfull.png"
            );

        flagTexture =
            new Texture(
                "finish_flag.png"
            );

        pipeTexture =
            new Texture(
                "pipaijo.png"
            );

        lavaTexture =
            new Texture(
                "lava.png"
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

            };

        redPlatforms = new Platform[] {

            // bata awal
            new Platform(platformMerah, 200, 80, 70, 70),
            new Platform(platformMerah, 400, 80, 70, 70),
            new Platform(platformMerah, 600, 80, 70, 70),
            new Platform(platformMerah, 800, 80, 70, 70),

            // platform tengah bawah
            new Platform(platformMerah, 1100, 180, 70, 70),
            new Platform(platformMerah, 1170, 180, 70, 70),
            new Platform(platformMerah, 1240, 180, 70, 70),
            new Platform(platformMerah, 1310, 180, 70, 70),
            new Platform(platformMerah, 1380, 180, 70, 70),
            new Platform(platformMerah, 1450, 180, 70, 70),
            new Platform(platformMerah, 1520, 180, 70, 70),
            new Platform(platformMerah, 1590, 180, 70, 70),
            new Platform(platformMerah, 1660, 180, 70, 70),

            // platform atas
            new Platform(platformMerah, 1100, 600, 70, 70),
            new Platform(platformMerah, 1170, 600, 70, 70),
            new Platform(platformMerah, 1240, 600, 70, 70),
            new Platform(platformMerah, 1310, 600, 70, 70),
            new Platform(platformMerah, 1380, 600, 70, 70),
            new Platform(platformMerah, 1450, 600, 70, 70),
            new Platform(platformMerah, 1520, 600, 70, 70),
            new Platform(platformMerah, 1590, 600, 70, 70),
            new Platform(platformMerah, 1660, 600, 70, 70),

        };

        pipePlatforms = new Platform[] {

            new Platform(platformMerah, 1900, 250, 70, 25),
            new Platform(platformMerah, 2150, 330, 70, 25),
            new Platform(platformMerah, 2350, 290, 70, 25),
            new Platform(platformMerah, 2550, 210, 70, 25)
        };

        // COIN DI ATAS PLATFORM
        coins = new Coin[] {

            new Coin(coinTexture, 215, 180),
            new Coin(coinTexture, 415, 180),
            new Coin(coinTexture, 615, 180),
            new Coin(coinTexture, 815, 180),

            new Coin(coinTexture, 1140, 300),
            new Coin(coinTexture, 1240, 300),
            new Coin(coinTexture, 1340, 300),
            new Coin(coinTexture, 1440, 300),
            new Coin(coinTexture, 1540, 300),
            new Coin(coinTexture, 1640, 300),

            new Coin(coinTexture, 1140, 540),
            new Coin(coinTexture, 1240, 540),
            new Coin(coinTexture, 1340, 540),
            new Coin(coinTexture, 1440, 540),
            new Coin(coinTexture, 1540, 540),
            new Coin(coinTexture, 1640, 540)
        };

        enemyJerry =
            new EnemyJerry(
                enemyJerryKananTexture,
                enemyJerryKiriTexture,
                1200,
                250,
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
                3400,
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

        for(Platform pipe : pipePlatforms)
        {
            Rectangle playerBounds =
                player.getBounds();

            Rectangle pipeBounds =
                pipe.getBounds();

            if(playerBounds.overlaps(pipeBounds))
            {
                float playerTopBefore =
                    player.getPreviousY() + 120;

                float pipeTop =
                    pipe.getY() + 25;

                if(
                    playerTopBefore >= pipeTop
                        &&
                        player.getVelocityY() <= 0
                )
                {
                    player.landOnPlatform(
                        pipeTop
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

        Rectangle lava1 =
            new Rectangle(
                1940,
                95,
                60,
                50
            );

        Rectangle lava2 =
            new Rectangle(
                1990,
                95,
                60,
                50
            );

        Rectangle lava3 =
            new Rectangle(
                2140,
                95,
                60,
                50
            );

        // =========================
        // COLLISION Lava
        // =========================
        if(
            (
                player.getBounds().overlaps(lava1)
                    ||
                    player.getBounds().overlaps(lava2)
                    ||
                    player.getBounds().overlaps(lava3)
            )
                &&
                damageCooldown <= 0
        )
        {
            heartSystem.damage();

            damageCooldown = 1f;
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
        for(int x = 0; x < 60; x++)
        {

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

        // PIPA

        batch.draw(pipeTexture, 1900, 80, 100, 180);

        batch.draw(pipeTexture, 2150, 80, 100, 260);

        batch.draw(pipeTexture, 2350, 80, 100, 220);

        batch.draw(pipeTexture, 2550, 80, 100, 140);

        // LAVA

        batch.draw(
            lavaTexture,
            2050,
            95,
            70,
            70
        );

        batch.draw(
            lavaTexture,
            2250,
            95,
            70,
            70
        );

        batch.draw(
            lavaTexture,
            2450,
            95,
            70,
            70
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

        bgm.stop();

        bgm.dispose();

        font.dispose();

        batch.dispose();

        sky.dispose();

        cloud.dispose();

        groundTile.dispose();

        platformBatu.dispose();

        coinTexture.dispose();

        enemyJerryTexture.dispose();

        enemyBijoKananTexture.dispose();
        enemyBijoKiriTexture.dispose();

        heartTexture.dispose();

        flagTexture.dispose();

        pipeTexture.dispose();

        lavaTexture.dispose();


        player.dispose();

        gameOverPopup.dispose();

        levelCompletePopup.dispose();

        yesButton.dispose();

        noButton.dispose();
    }
}
