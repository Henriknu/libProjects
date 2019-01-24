package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Exercise1;

public class Helicopter {

    private Sprite helicopter;
    private static final int MOVEMENT_X = 150;
    private static final int MOVEMENT_Y = 150;
    private boolean direction_x = true;
    private boolean direction_y = true;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private boolean faulty_x = false;
    private boolean faulty_y = false;

    public Helicopter(float x, float y){

        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        helicopter = new Sprite(new Texture("heli1.png"));
        this.flipSprite();
        bounds = new Rectangle(x,y,helicopter.getWidth(),helicopter.getHeight());
    }

    public void update(float dt){
        velocity.scl(dt);
        if (position.x < Exercise1.WIDTH + helicopter.getWidth()/2 && position.x > 0 + helicopter.getWidth()*3/2
                && position.y < Exercise1.HEIGHT + helicopter.getHeight()*7/2 && position.y > 0 + helicopter.getHeight()*6) {
            position.add(MOVEMENT_X * dt * this.translateDir(this.direction_x), MOVEMENT_Y * dt * this.translateDir(this.direction_y), 0);
            this.faulty_x = false;
            this.faulty_y = false;
            System.out.println("x,y: " + position.x + "," + position.y);
        }
        else{
            if (position.x < Exercise1.WIDTH + helicopter.getWidth()/2 && position.x > 0 + helicopter.getWidth()*3/2) {
                this.setDirectionY(!this.direction_y);
                if (this.faulty_y){
                    this.resetPosition();
                }
                else {
                    this.faulty_y = true;
                }
            }
            else{
                this.setDirectionX(!this.direction_x);
                if (this.faulty_x){
                    this.resetPosition();
                }
                else {
                    this.faulty_x = true;
                }
            }
            position.add(MOVEMENT_X * dt * this.translateDir(this.direction_x), MOVEMENT_Y * dt * this.translateDir(this.direction_y), 0);
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    private int translateDir(boolean direction) {
        if (direction){
            return 1;
        }
        else{
            return -1;
        }
    }

    public Sprite getSprite() {
        return helicopter;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(float x, float y, float z){
        this.position.set(x,y,z);
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose(){
        helicopter.getTexture().dispose();
    }

    public void flipSprite(){
        helicopter.flip(true,false);
    }

    public void setDirectionX(boolean direction){

        if(direction!=this.direction_x){
            this.flipSprite();
        }
        this.direction_x = direction;
    }

    public void setDirectionY(boolean direction){
        this.direction_y = direction;
    }

    public void resetPosition(){
        position.set(400,400,position.z);
        this.faulty_x = false;
        this.faulty_y = false;
    }
}
