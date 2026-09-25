package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameData;

public class Player {

    private Texture texture;

    private float x;
    private float y;
    private float previousY;

    private float speed = 300;

    private float velocityY = 0;

    private float gravity = -900;

    private float jumpPower = 500;

    private boolean onGround = true;

    private int jumpCount = 0;

    private final int MAX_JUMP = 2;

    public Player() {

        switch(GameData.selectedCharacter) {

            case 0:
                texture = new Texture("player_jay.png");
                break;

            case 1:
                texture = new Texture("player_luna.png");
                break;

            case 2:
                texture = new Texture("player_rexy.png");
                break;

            case 3:
                texture = new Texture("player_kiro.png");
                break;

            case 4:
                texture = new Texture("player_niko.png");
                break;

            default:
                texture = new Texture("player_jay.png");
        }

        x = 115;
        y = 100;
    }

    public void update(float delta) {
        previousY = y;

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            x += speed * delta;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            x -= speed * delta;

            if(x < 0) {

                x = 0;
            }
        }

        if(
            Gdx.input.isKeyJustPressed(
                Input.Keys.SPACE
            )
                &&
                jumpCount < MAX_JUMP
        )
        {

            velocityY = jumpPower;

            onGround = false;

            jumpCount++;
        }

        velocityY += gravity * delta;

        y += velocityY * delta;

        // TANAH
        if(y <= 95) {

            y = 95;

            velocityY = 0;

            onGround = true;

            jumpCount = 0;
        }
    }

    public void clampPosition(
        float maxWidth
    )
    {
        if(x < 0)
        {
            x = 0;
        }

        if(x > maxWidth - 90)
        {
            x = maxWidth - 90;
        }
    }

    public Rectangle getBounds() {

        return new Rectangle(
            x,
            y,
            90,
            120
        );
    }

    public void landOnPlatform(
        float platformTop
    )
    {
        System.out.println(
            "LAND : " + platformTop
        );

        y = platformTop;

        velocityY = 0;

        onGround = true;

        jumpCount = 0;
    }

    public void leaveGround() {

        onGround = false;
    }

    public void draw(
        SpriteBatch batch
    ) {

        batch.draw(
            texture,
            x,
            y,
            90,
            120
        );
    }

    public float getX() {

        return x;
    }

    public float getY() {

        return y;
    }

    public float getPreviousY() {

        return previousY;

    }

    public float getVelocityY() {

        return velocityY;
    }

    public float getBottom() {

        return y;
    }

    public float getTop() {

        return y + 120;
    }

    public float getLeft() {

        return x;
    }

    public float getRight() {

        return x + 90;
    }

    public void setX(float x) {

        this.x = x;
    }

    public void dispose() {

        if(texture != null) {

            texture.dispose();
        }
    }
}
