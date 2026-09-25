package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Flag {

    private Texture texture;

    private float x;
    private float y;

    public Flag(
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
            90,
            140
        );
    }

    public Rectangle getBounds() {

        return new Rectangle(
            x,
            y,
            90,
            140
        );
    }
}
