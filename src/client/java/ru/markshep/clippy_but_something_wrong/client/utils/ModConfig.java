package ru.markshep.clippy_but_something_wrong.client.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("ClippyButSomethingWrong.json");

    public void save() {
        try {
            Files.writeString(CONFIG_PATH, GSON.toJson(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ModConfig load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH);
                return GSON.fromJson(json, ModConfig.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModConfig config = new ModConfig();
        config.save();
        return config;
    }
}
