package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Coin {

    private Texture texture;

    private float x;
    private float y;

    private boolean collected = false;

    public Coin(
        Texture texture,
        float x,
        float y
    ) {

        this.texture = texture;

        this.x = x;
        this.y = y;
    }

    public void draw(
        SpriteBatch batch
    ) {

        if(!collected) {

            batch.draw(
                texture,
                x,
                y,
                42,
                42
            );
        }
    }

    public Rectangle getBounds() {

        return new Rectangle(
            x,
            y,
            42,
            42
        );
    }

    public boolean isCollected() {

        return collected;
    }

    public void collect() {

        collected = true;
    }
}
