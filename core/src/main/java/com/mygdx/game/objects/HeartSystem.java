package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HeartSystem {

    private int hearts = 3;

    private Texture fullHeart;

    public HeartSystem(
        Texture fullHeart
    ) {

        this.fullHeart = fullHeart;
    }

    public void damage() {

        if(hearts > 0) {

            hearts--;
        }
    }

    public boolean isDead() {

        return hearts <= 0;
    }

    public int getHearts() {

        return hearts;
    }

    public void draw(
        SpriteBatch batch
    ) {

        for(
            int i = 0;
            i < hearts;
            i++
        ) {

            batch.draw(
                fullHeart,
                20 + (i * 50),
                800,
                40,
                40
            );
        }
    }
}
