package com.johnew.game.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.johnew.game.FlappyTest;
import com.johnew.game.sprites.Cat;

import java.awt.Font;
import java.util.ArrayList;

public class PlayState extends State {
    private Texture bg;

    private ArrayList<Cat> sprites;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        sprites = new ArrayList<Cat>();
        sprites.add(new Cat(50, 600, "heli"));
        sprites.add(new Cat(100, 30, "heli"));
        sprites.add(new Cat(100, 300, "heli"));
        sprites.add(new Cat(10, 400, "pepe2.png"));

    }

    @Override
    protected void handleInput() {


    }

    @Override
    public void update(float dt) {
        handleInput();

        for (Cat c: sprites) {
            c.handleWallCollision();
        }
        ArrayList<Cat> temp = new ArrayList<Cat>(sprites);
        temp.remove(0);

        for (Cat a: sprites) {
            for (Cat b: temp) {
                if (a.collides(b)) {
                    a.copterCollision();
                    b.copterCollision();
                }
            }
            if (!(temp.isEmpty())) {
                temp.remove(0);
            }
        }


        /*if (cat.collides(cat2)) {
            cat.copterCollision();
            cat2.copterCollision();
        } */

        for (Cat c: sprites) {
            c.update(dt);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();


        ArrayList<Sprite> sp = new ArrayList<Sprite>(); // make a new list with sprites instead of cats.
        for (Cat c: sprites) {
            sp.add(new Sprite(c.getCat()));
        }

        for (Sprite s: sp) {
            Cat c = sprites.get(sp.indexOf(s));                     // get Cat object
            s.flip((c.getX_direction() == 1), false);            // consider flipping sprite.
            sb.draw(s, c.getPosition().x, c.getPosition().y);       // draw sprite.
        }

        sb.end();
    }

    @Override
    public void dispose() {
        for (Cat c: sprites) {
            c.dispose();
        }

    }

    public Sprite flipAndConvertTexture(Texture t) {
        Sprite sprite = new Sprite(t);
        sprite.flip(true, false);
        return sprite;
    }
}

