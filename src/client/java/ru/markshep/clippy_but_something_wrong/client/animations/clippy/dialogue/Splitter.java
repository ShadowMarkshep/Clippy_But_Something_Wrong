package ru.markshep.clippy_but_something_wrong.client.animations.clippy.dialogue;

import java.util.ArrayList;
import java.util.List;
import ru.markshep.clippy_but_something_wrong.client.utils.exceptions.OutOfDialogueBoxException;

public final class Splitter {

    private Splitter() {
        throw new IllegalStateException("Утилитарный класс");
    }

    private static final int maxSymbols = 18;
    private static final int maxLines = 4;

    public static List<String> splitText(String text) throws OutOfDialogueBoxException {
        List<String> lines = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (String word : text.split(" ")) {
            if (word.length() > maxSymbols) {
                List<String> longWord = breakLongWord(word);
                for (String part : longWord) {
                    if (part.length() +  builder.length() > maxSymbols) {
                        lines.add(builder.toString());
                        builder = new StringBuilder();
                        builder.append(part).append(" ");
                        if (lines.size() > maxLines) {
                            throw new OutOfDialogueBoxException();
                        }
                    } else {
                        builder.append(part).append(" ");
                    }
                }
                continue;
            }
            if (word.length() +  builder.length() > maxSymbols) {
                lines.add(builder.toString());
                builder = new StringBuilder();
                builder.append(word).append(" ");
                if (lines.size() > maxLines) {
                    throw new OutOfDialogueBoxException();
                }
            } else {
                builder.append(word).append(" ");
            }
        }
        if (!builder.isEmpty()) {
            lines.add(builder.toString());
            if (lines.size() > maxLines) {
                throw new OutOfDialogueBoxException();
            }
        }

        return lines;
    }

    private static List<String> breakLongWord(String word) {
        List<String> parts = new ArrayList<>();
        int start = 0;

        while (start < word.length()) {
            int end = Math.min(start + maxSymbols, word.length());
            parts.add(word.substring(start, end));
            start = end;
        }

        return parts;
    }
}
