package ru.markshep.clippy_but_something_wrong.client.animations.screamers;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import static ru.markshep.clippy_but_something_wrong.client.Clippy_but_something_wrongClient.*;
import static ru.markshep.clippy_but_something_wrong.client.utils.Utils.id;

public class ScreamerAnimation {

    private static int currentFrame = 0;
    private static int counter = 0;

    public static void animateScreamer(DrawContext context, int totalFrames, String path, int fps) {
        if (currentFrame > totalFrames) {
            currentFrame = 0;
        }
        int finalCounter = MinecraftClient.getInstance().getCurrentFps() / fps;
        Identifier texture = id(path);
            context.drawTexture(RenderLayer::getGuiTextured, texture, 0, 0, 0, 360 * currentFrame, client.currentScreen.width, client.currentScreen.height, 640, 5760);
        counter++;
        if (counter > finalCounter) {
            currentFrame++;
            counter = 0;
        }
    }

}
