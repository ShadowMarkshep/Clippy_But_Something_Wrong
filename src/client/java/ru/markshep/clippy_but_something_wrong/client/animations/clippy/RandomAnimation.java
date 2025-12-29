    package ru.markshep.clippy_but_something_wrong.client.animations.clippy;

import net.minecraft.client.gui.DrawContext;

import java.util.Random;

import static ru.markshep.clippy_but_something_wrong.client.Clippy_but_something_wrongClient.statement;
import static ru.markshep.clippy_but_something_wrong.client.animations.clippy.ClippyAnimation.*;

public class RandomAnimation {

    public static Random random = new Random();

    public static Statement randomEventAnimation(){
        int percent = random.nextInt(100) + 1;
        if (percent == 1) {
            return Statement.SLEEP;
        } else if (percent == 2) {
            return Statement.WTF;
        } else if (percent == 3) {
            return Statement.POINT_DOWN;
        } else if (percent == 4) {
            return Statement.POINT_UP;
        } else if (percent == 5) {
            return Statement.POINT_LEFT;
        } else if (percent == 6) {
            return Statement.POINT_RIGHT;
        } else if (percent == 7) {
            return Statement.WATCH_FRONT;
        } else if (percent == 8) {
            return Statement.WATCH_LEFT;
        } else if (percent == 9) {
            return Statement.WATCH_RIGHT;
        } else if (percent == 10) {
            return Statement.THINK;
        } else if (percent == 11) {
            return Statement.SHOVEL;
        } else if (percent == 12) {
            return Statement.OFFICE;
        } else if (percent == 13) {
            return Statement.TIRED;
        } else if (percent == 14) {
            return Statement.SPYGLASS;
        } else if (percent == 15) {
            return Statement.AIRPLANE;
        } else if (percent == 16) {
            return Statement.CHECK;
        } else if (percent == 17) {
            return Statement.EYES;
        } else if (percent == 18) {
            return Statement.LISTEN;
        } else if (percent == 19) {
            return Statement.MUSIC;
        } else if (percent == 20) {
            return Statement.NOTED;
        } else if (percent == 21) {
            return Statement.READ;
        } else {
            return Statement.IDLE;
        }
    }

    public static void checkState (DrawContext context) {
        switch (statement) {
            case AIRPLANE, CHECK, EYES, WATCH_FRONT, WATCH_RIGHT, WATCH_LEFT, POINT_RIGHT, POINT_LEFT, POINT_DOWN,
                 SPYGLASS, POINT_UP, WAKE_UP, SHOVEL, LISTEN, OFFICE, TIRED, THINK, NOTED, MUSIC, READ, WTF -> {
                animate(context);
                if (currentFrame > totalFrames) {
                    currentFrame = 0;
                    statement = Statement.IDLE;
                }
            }
            case IDLE -> {
                animate(context);
                if (currentFrame > totalFrames) {
                    currentFrame = 0;
                    statement = randomEventAnimation();
                }
            }
            case SLEEP -> {
                animate(context);
                if (currentFrame > totalFrames) {
                    currentFrame = 0;
                    statement = Statement.WAKE_UP;
                }
            }
        }
    }
}
