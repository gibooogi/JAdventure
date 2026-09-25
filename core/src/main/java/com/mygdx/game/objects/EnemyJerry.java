package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class EnemyJerry {

    private Texture textureKanan;
    private Texture textureKiri;

    private float x;
    private float y;

    private float speed = 100;

    private boolean moveRight = true;

    private float leftLimit;
    private float rightLimit;

    public EnemyJerry(
        Texture textureKanan,
        Texture textureKiri,
        float x,
        float y,
        float leftLimit,
        float rightLimit
    ) {

        this.textureKanan = textureKanan;
        this.textureKiri = textureKiri;

        this.x = x;
        this.y = y;

        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
    }

    public void update(float delta) {

        if(moveRight) {

            x += speed * delta;

            if(x >= rightLimit) {

                moveRight = false;
            }

        } else {

            x -= speed * delta;

            if(x <= leftLimit) {

                moveRight = true;
            }
        }
    }

    public void draw(
        SpriteBatch batch
    ) {

        if(moveRight)
        {
            batch.draw(
                textureKanan,
                x,
                y,
                80,
                80
            );
        }
        else
        {
            batch.draw(
                textureKiri,
                x,
                y,
                80,
                80
            );
        }
    }

    public Rectangle getBounds() {

        return new Rectangle(
            x,
            y,
            80,
            80
        );
    }
}
