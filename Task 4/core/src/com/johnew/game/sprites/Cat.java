package com.johnew.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.johnew.game.FlappyTest;
import com.johnew.game.states.PlayState;

public class Cat {
    private static final int GRAVITY = 15;
    private static final int SPEED = 10;
    private float x_direction = 0;
    private float y_direction = 0;
    private String name;

    private Vector3 position;
    private Rectangle bounds;
    private Animation catAnimation;
    private int points = 0;

    public Cat(int x, int y, String texture) {
        position = new Vector3(x, y, 0);
        catAnimation = new Animation(4,  0.1f, texture);
        bounds = new Rectangle(x, y, catAnimation.getFrame().getWidth(), catAnimation.getFrame().getHeight());

        name = texture;
        if (texture.equals("pong.png")) {
            x_direction = 1.5f;
            y_direction = 1.5f;
        }
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


    public float getX_direction() {
        return x_direction;
    }

    public float getY_direction() {return y_direction;}

    public void setX_direction(int i) {this.x_direction = i;}

    public void setY_direction(int i) {this.y_direction = i;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public boolean collides(Cat otherSprite) {
        return otherSprite.getBounds().overlaps(bounds);
    }

    public void handleWallCollision() {

        if (name.equals("pong.png") && (position.y > FlappyTest.HEIGHT - getCat().getHeight() || position.y < 0)) {
            y_direction = y_direction * -1;
        }

        if (position.y > FlappyTest.HEIGHT - getCat().getHeight()) {
            position.y = FlappyTest.HEIGHT - getCat().getHeight();
        }
        else if (position.y  < 0) {
            position.y = 0;
        }

        if (position.x > (FlappyTest.WIDTH - getCat().getWidth()))  {
            PlayState.player1Points += 1;
            position.x = 200;
            position.y = 200;
        }
        else if (position.x < 0) {
            PlayState.player2Points += 1;
            position.x = 600;
            position.y = 200;
        }

    }

    public void copterCollision() {
        x_direction = x_direction * -1;
    }

    public void dispose() {
        catAnimation.getFrame().dispose();
    }
}
