package com.johnew.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.johnew.game.FlappyTest;
import com.johnew.game.sprites.Cat;

import java.awt.Font;
import java.util.ArrayList;

public class PlayState extends State implements InputProcessor {
    private Texture bg;
    private ArrayList<Cat> sprites;

    public static int player1Points = 0;
    public static int player2Points = 0;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        sprites = new ArrayList<Cat>();
        sprites.add(new Cat(50, 20, "paddle3.png"));
        sprites.add(new Cat(750, 0, "paddle3.png"));
        sprites.add(new Cat(100, 400, "pong.png"));

        Gdx.input.setInputProcessor(this);
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

        Cat COM = sprites.get(1);
        if (COM.getPosition().y < sprites.get(2).getPosition().y) {
            COM.setY_direction(1);
        } else if (COM.getPosition().y > sprites.get(2).getPosition().y) {
            COM.setY_direction(-1);
        }


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

        BitmapFont f = new BitmapFont();
        f.setColor(Color.WHITE);
        f.getData().setScale(2);
        String str = player1Points + " | " + player2Points;
        f.draw(sb, str, 380,  470);

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

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.DOWN){
            this.sprites.get(0).setY_direction(-1);
        }
        else if(keycode == Input.Keys.UP){
            this.sprites.get(0).setY_direction(1);
        }

        if (keycode == Input.Keys.S) {
            this.sprites.get(1).setY_direction(-1);
        }
        else if(keycode == Input.Keys.W) {
            this.sprites.get(1).setY_direction(1);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        this.sprites.get(0).setY_direction(0);
        this.sprites.get(1).setY_direction(0);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Cat pl1 = this.sprites.get(0);
        String str = "x: " + screenX + "| y: " + screenY + " || " + pl1.getPosition().y;
        System.out.println(str);

        if (400 - screenY < pl1.getPosition().y) {
            this.sprites.get(0).setY_direction(-1);
        }

        else if (400 - screenY > pl1.getPosition().y) {
            this.sprites.get(0).setY_direction(1);
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        this.sprites.get(0).setY_direction(0);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

