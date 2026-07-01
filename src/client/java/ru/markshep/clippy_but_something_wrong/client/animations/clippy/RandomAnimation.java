    package ru.markshep.clippy_but_something_wrong.client.animations.clippy;

import net.minecraft.client.gui.DrawContext;

import java.util.Random;

import static ru.markshep.clippy_but_something_wrong.client.Clippy_but_something_wrongClient.statement;
import static ru.markshep.clippy_but_something_wrong.client.animations.clippy.ClippyAnimation.*;

public class RandomAnimation {

    public static Random random = new Random();

    public static Statement randomEventAnimation(){
        int percent = random.nextInt(100) + 1;
        switch (percent) {
            case 1: return Statement.SLEEP;
            case 2: return Statement.WTF;
            case 3: return Statement.POINT_DOWN;
            case 4: return Statement.POINT_UP;
            case 5: return Statement.POINT_LEFT;
            case 6: return Statement.POINT_RIGHT;
            case 7: return Statement.WATCH_FRONT;
            case 8: return Statement.WATCH_LEFT;
            case 9: return Statement.WATCH_RIGHT;
            case 10: return Statement.THINK;
            case 11: return Statement.SHOVEL;
            case 12: return Statement.OFFICE;
            case 13: return Statement.TIRED;
            case 14: return Statement.SPYGLASS;
            case 15: return Statement.AIRPLANE;
            case 16: return Statement.CHECK;
            case 17: return Statement.EYES;
            case 18: return Statement.LISTEN;
            case 19: return Statement.MUSIC;
            case 20: return Statement.NOTED;
            case 21: return Statement.READ;
            default: return Statement.IDLE;
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
