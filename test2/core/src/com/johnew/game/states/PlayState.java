package com.johnew.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.johnew.game.FlappyTest;
import com.johnew.game.sprites.Cat;

public class PlayState extends State {
    private Cat cat;
    private Cat cat2;
    private Texture bg;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cat = new Cat(50, 200);
        cat2 = new Cat(100, 600);
        bg = new Texture("crying.jpg");
    }

    @Override
    protected void handleInput() {


    }

    @Override
    public void update(float dt) {
        handleInput();

        if (cat.getPosition().y > FlappyTest.HEIGHT - cat.getCat().getHeight() || cat.getPosition().y < 0) {
            cat.setY_direction(cat.getY_direction() * -1);
        }
        if (cat.getPosition().x > (FlappyTest.WIDTH - cat.getCat().getWidth()) || cat.getPosition().x < 0) {
            cat.setX_direction(cat.getX_direction() * -1);
        }

        if (cat2.getPosition().y > FlappyTest.HEIGHT - cat2.getCat().getHeight() || cat2.getPosition().y < 0) {
            cat2.setY_direction(cat2.getY_direction() * -1);
        }
        if (cat2.getPosition().x > (FlappyTest.WIDTH - cat2.getCat().getWidth()) || cat2.getPosition().x < 0) {
            cat2.setX_direction(cat2.getX_direction() * -1);
        }

        if (cat.collides(cat2)) {
            cat.setY_direction(cat.getY_direction() * -1);
            cat2.setY_direction(cat2.getY_direction() * -1);

            cat.setX_direction(cat.getX_direction() * -1);
            cat2.setX_direction(cat2.getX_direction() * -1);
        }


        cat.update(dt);
        cat2.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        //sb.draw(cat.getCat(), cat.getPosition().x, cat.getPosition().y);

        Sprite sprite = new Sprite(cat.getCat());
        boolean flipped = (cat.getX_direction() == 1) ? true : false;
        sprite.flip(flipped, false);

        Sprite sprite2 = new Sprite(cat2.getCat());
        boolean flipped2 = (cat2.getX_direction() == 1) ? true : false;
        sprite2.flip(flipped2, false);

        sb.draw(bg,0, 0, FlappyTest.WIDTH, FlappyTest.HEIGHT);

        sb.draw(sprite, cat.getPosition().x, cat.getPosition().y);
        sb.draw(sprite2, cat2.getPosition().x, cat2.getPosition().y);


        //sb.draw(, sprite.getPosition().x, flipped.getPosition().);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        cat.dispose();
    }
}

