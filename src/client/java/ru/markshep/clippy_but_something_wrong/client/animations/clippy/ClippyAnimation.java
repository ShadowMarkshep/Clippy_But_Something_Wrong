package ru.markshep.clippy_but_something_wrong.client.animations.clippy;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import ru.markshep.clippy_but_something_wrong.client.utils.Utils;

import static ru.markshep.clippy_but_something_wrong.client.Clippy_but_something_wrongClient.statement;

public class ClippyAnimation {

    //Переменные для анимации
    public static int totalFrames;
    public static int currentFrame = 0;
    public static int counter = 0;
    public static int finalCounter;

    //Размер
    public static float clippyScale = 1.25F;


    public static void animate (DrawContext context) {
        finalCounter = MinecraftClient.getInstance().getCurrentFps() / 10;
        Identifier texture = Utils.id(statement.getPath());
        totalFrames = statement.getTotalFrames();
        context.drawTexture(RenderLayer::getGuiTextured,
                texture,
                45,
                (int) (150 - (64 * clippyScale / 2)),
                (int) (64 * clippyScale * currentFrame),
                0,
                (int) (64 * clippyScale),
                (int) (64 * clippyScale),
                (int) (statement.getWidth() * clippyScale),
                (int) (64 * clippyScale));
        counter++;
        if (counter > finalCounter) {
            currentFrame++;
            counter = 0;
        }
    }

}
