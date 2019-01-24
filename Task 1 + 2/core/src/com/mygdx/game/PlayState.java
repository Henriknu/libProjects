package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.sprites.Helicopter;


public class PlayState extends State implements InputProcessor {

    private Helicopter helicopter;
    private Texture bg;

    public PlayState(GameStateManager gsm){
        super(gsm);
        System.out.println("PlayState started!");
        cam.setToOrtho(false, Exercise1.WIDTH, Exercise1.HEIGHT);
        helicopter = new Helicopter(300,700);
        bg = new Texture("bg.png");

        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {

        handleInput();
        helicopter.update(dt);
        cam.position.x = helicopter.getPosition().x;
        cam.position.y = helicopter.getPosition().y;
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg,0, 0, Exercise1.WIDTH,Exercise1.HEIGHT);
        sb.draw(helicopter.getSprite(), cam.position.x - cam.viewportWidth/2, cam.position.y - cam.viewportHeight/2);
        BitmapFont f = new BitmapFont();
        f.setColor(Color.WHITE);
        f.getData().setScale(1.5f);
        String str = "X: " + Math.round(helicopter.getPosition().x) + ", Y: " +  Math.round(helicopter.getPosition().y-200);
        f.draw(sb, str, 10, Exercise1.HEIGHT - 10);
        sb.end();
    }

    @Override
    public void dispose() {

        System.out.println("Disposed in playstate");

        helicopter.dispose();
        bg.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT){
            this.helicopter.setDirectionX(false);
        }
        else if(keycode == Input.Keys.RIGHT){
            this.helicopter.setDirectionX(true);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Y position of mouseClick: " + screenY);
        System.out.println("Cam height" + cam.viewportHeight);
        /**
        if (screenX < this.helicopter.getPosition().x){
            this.helicopter.setDirectionX(false);
        }
        else if (screenX > this.helicopter.getPosition().x){
            this.helicopter.setDirectionX(true);
        }
         */
        this.helicopter.setPosition(cam.viewportWidth*1/2 - this.helicopter.getSprite().getWidth()/2 + screenX, cam.viewportHeight * 3/2 - this.helicopter.getSprite().getHeight() - screenY,0);
        System.out.println("Helicopter height" + helicopter.getPosition().y);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
