package com.johnew.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Cat {
    private static final int GRAVITY = 15;
    private static final int SPEED = 2;
    private int x_direction = 1;
    private int y_direction = 1;

    private Vector3 position;
    private Rectangle bounds;
    private Animation catAnimation;

    public Cat(int x, int y) {
        position = new Vector3(x, y, 0);
        catAnimation = new Animation(4,  0.1f);
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

    public void setX_direction(int x_direction) {
        this.x_direction = x_direction;
    }

    public int getY_direction() {
        return y_direction;
    }

    public void setY_direction(int y_direction) {
        this.y_direction = y_direction;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public boolean collides(Cat otherSprite) {
        return otherSprite.getBounds().overlaps(bounds);
    }

    public void dispose() {
        catAnimation.getFrame().dispose();
    }
}
