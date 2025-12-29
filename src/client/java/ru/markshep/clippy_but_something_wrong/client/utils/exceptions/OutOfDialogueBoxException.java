package ru.markshep.clippy_but_something_wrong.client.utils.exceptions;

public class OutOfDialogueBoxException extends RuntimeException {
    public OutOfDialogueBoxException() {
        super("Слишком большая фраза для диалога");
    }
}
