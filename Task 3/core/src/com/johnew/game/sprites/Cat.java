package com.johnew.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.johnew.game.FlappyTest;

public class Cat {
    private static final int GRAVITY = 15;
    private static final int SPEED = 5;
    private int x_direction = 1;
    private int y_direction = 1;

    private Vector3 position;
    private Rectangle bounds;
    private Animation catAnimation;

    public Cat(int x, int y, String texture) {
        position = new Vector3(x, y, 0);
        catAnimation = new Animation(4,  0.1f, texture);
        bounds = new Rectangle(x, y, catAnimation.getFrame().getWidth(), catAnimation.getFrame().getHeight());
    }

    public void update(float dt) {
        catAnimation.update(dt);

        // update the next sprite position.
        position.add(SPEED * x_direction, SPEED * y_direction, 0);

        // move the sprites bounds to new position.
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getCat() {
        return catAnimation.getFrame();
    }


    public int getX_direction() {
        return x_direction;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public boolean collides(Cat otherSprite) {
        return otherSprite.getBounds().overlaps(bounds);
    }

    public void handleWallCollision() {
        if (position.y > FlappyTest.HEIGHT - getCat().getHeight() || position.y < 0) {
            y_direction = y_direction * -1;
        }
        if (position.x > (FlappyTest.WIDTH - getCat().getWidth()) || position.x < 0) {
            x_direction = x_direction * -1;
        }
    }

    public void copterCollision() {
        y_direction = y_direction * -1;
        x_direction = x_direction * -1;
    }

    public void dispose() {
        catAnimation.getFrame().dispose();
    }
}
