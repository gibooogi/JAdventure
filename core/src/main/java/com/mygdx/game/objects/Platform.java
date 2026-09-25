package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Platform {

    private Texture texture;

    private float x;
    private float y;

    private float width;
    private float height;

    private Rectangle bounds;

    public Platform(
        Texture texture,
        float x,
        float y,
        float width,
        float height
    ) {

        this.texture = texture;

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        bounds =
            new Rectangle(
                x,
                y,
                width,
                height
            );
    }

    public void draw(
        SpriteBatch batch
    ) {

        batch.draw(
            texture,
            x,
            y,
            width,
            height
        );
    }

    public Rectangle getBounds() {

        return bounds;
    }

    public float getY() {

        return y;
    }

    public float getTop() {

        return y + height;

    }

    public float getLeft() {

        return x;

    }

    public float getRight() {

        return x + width;

    }
}
