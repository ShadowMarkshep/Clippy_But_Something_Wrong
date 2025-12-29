package ru.markshep.clippy_but_something_wrong.client.animations.clippy.dialogue;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.font.TextRenderer;
import ru.markshep.clippy_but_something_wrong.client.utils.exceptions.OutOfDialogueBoxException;
import static ru.markshep.clippy_but_something_wrong.client.Clippy_but_something_wrongClient.client;
import static ru.markshep.clippy_but_something_wrong.client.Clippy_but_something_wrongClient.modLogger;

public class Splitter {

    private static boolean errorLogged = false;

    public static List<String> splitText(String text) throws OutOfDialogueBoxException {
        List<String> lines = new ArrayList<>();
        TextRenderer font = client.textRenderer;
        int maxWidth = DialogueAnimation.dialogueWidth;
        StringBuilder currentLine = new StringBuilder();

        for (String paragraph : text.split("\n")) {
            currentLine.setLength(0);
            for (String word : paragraph.split(" ")) {
                String candidate = currentLine.length() == 0
                        ? word
                        : currentLine + " " + word;
                if (font.getWidth(candidate) <= maxWidth) {
                    if (currentLine.length() > 0) currentLine.append(' ');
                    currentLine.append(word);
                } else {
                    if (currentLine.length() > 0) {
                        lines.add(currentLine.toString());
                    }
                    currentLine.setLength(0);
                    if (font.getWidth(word) > maxWidth) {
                        StringBuilder part = new StringBuilder();
                        for (char c : word.toCharArray()) {
                            if (font.getWidth(part.toString() + c) <= maxWidth) {
                                part.append(c);
                            } else {
                                lines.add(part.toString());
                                part.setLength(0);
                                part.append(c);
                            }
                        }
                        if (part.length() > 0) {
                            currentLine.append(part);
                        }
                    } else {
                        currentLine.append(word);
                    }
                }
            }
            if (currentLine.length() > 0) {
                lines.add(currentLine.toString());
            }
            lines.add("");
        }

        if (!lines.isEmpty() && lines.get(lines.size() - 1).isEmpty()) {
            lines.remove(lines.size() - 1);
        }

        if (lines.size() >= 5) {
            if (!errorLogged) {
                modLogger.error("пустота");
                errorLogged = true;
            }
            throw new OutOfDialogueBoxException();
        }

        return lines;
    }
}
