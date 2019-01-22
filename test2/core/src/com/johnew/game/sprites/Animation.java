package com.johnew.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.io.Console;


public class Animation {
    private Array<Texture> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(int frameCount, float cycleTime) {
        frames = new Array<Texture>();

        for(int i = 0; i < 4; i++) {
            frames.add(new Texture("heli" + Integer.toString(i+1) + ".png"));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame = 0;
        }
    }

    public Texture getFrame() {
        return frames.get(frame);
    }


}
