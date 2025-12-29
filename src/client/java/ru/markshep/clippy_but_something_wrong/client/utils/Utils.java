package ru.markshep.clippy_but_something_wrong.client.utils;

import net.minecraft.util.Identifier;

import static ru.markshep.clippy_but_something_wrong.client.Clippy_but_something_wrongClient.modId;

public class Utils {

    public static Identifier id(String path) {return Identifier.of(modId, path);}

    public static String translationKey(String codeName){return "key." + modId + "." + codeName;}

}
