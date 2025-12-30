package ru.markshep.clippy_but_something_wrong.client.animations.clippy.dialogue;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.SoundEvents;
import ru.markshep.clippy_but_something_wrong.client.utils.exceptions.OutOfDialogueBoxException;

import java.util.List;

import static ru.markshep.clippy_but_something_wrong.client.animations.clippy.ClippyAnimation.clippyScale;
import static ru.markshep.clippy_but_something_wrong.client.utils.Utils.id;
import static ru.markshep.clippy_but_something_wrong.client.Clippy_but_something_wrongClient.client;

public class DialogueAnimation {

    private static float scaleHeight = 0.275f;
    private static float scaleWidth = 0.35f;
    public static int dialogueWidth = (int) (328 * scaleWidth);
    public static int dialogueHeight = (int) (229 * scaleHeight);
    public static int dialogueX = 20;
    public static int dialogueY = (int) (150 - (64 * clippyScale / 2) - dialogueHeight + 10);

    private static String fullText = "";
    private static boolean visible = false;

    private static int totalChars = 0;
    private static int displayedChars = 0;
    private static int frameCounter = 0;
    private static int framesPerChar;

    private static int delayFramesCounter = 0;
    private static final int DISPLAY_SECONDS = 3;

    public static void startDialogue(String text) {
        try {
            Splitter.splitText(text);
        } catch (OutOfDialogueBoxException e) {
            return;
        }
        fullText = text;
        visible = true;
        totalChars = text.length();
        displayedChars = 0;
        frameCounter = 0;
        delayFramesCounter = 0;
    }

    public static void hideDialogue() {
        visible = false;
    }

    public static void animate(DrawContext context) {
        if (!visible) return;

        context.drawTexture(
                RenderLayer::getGuiTextured,
                id("dialogue/dialogue_box.png"),
                dialogueX, dialogueY,
                0, 0,
                dialogueWidth, dialogueHeight,
                dialogueWidth, dialogueHeight
        );

        int fps = Math.max(1, client.getCurrentFps());
        framesPerChar = fps / 10;
        frameCounter++;
        if (frameCounter >= framesPerChar && displayedChars < totalChars) {
            displayedChars++;
            frameCounter = 0;
            playTypeSound();
        }

        String visibleText = fullText.substring(0, displayedChars);

        List<String> lines;
        try {
            lines = Splitter.splitText(visibleText);
        } catch (OutOfDialogueBoxException e) {
            hideDialogue();
            return;
        }

        TextRenderer font = client.textRenderer;
        int lineHeight = font.fontHeight + 2;
        int textX = dialogueX + 5;
        int textY = dialogueY + 5;

        for (int i = 0; i < lines.size(); i++) {
            context.drawText(
                    font,
                    lines.get(i),
                    textX,
                    textY + i * lineHeight,
                    0x000000,
                    false
            );
        }

        if (displayedChars >= totalChars) {
            delayFramesCounter++;
            int framesNeeded = fps * DISPLAY_SECONDS;

            if (delayFramesCounter >= framesNeeded) {
                hideDialogue();
            }
        }

    }

    private static void playTypeSound() {
        MinecraftClient.getInstance().getSoundManager().play(
                PositionedSoundInstance.master(
                        SoundEvents.BLOCK_NOTE_BLOCK_HAT,
                        1.0f
                )
        );
    }
}
