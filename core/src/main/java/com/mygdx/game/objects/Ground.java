package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ground {

    private Texture texture;

    private float x;
    private float y;

    public Ground(
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

        batch.draw(
            texture,
            x,
            y,
            93,
            93
        );
    }

    public float getX() {

        return x;
    }

    public float getY() {

        return y;
    }
}
