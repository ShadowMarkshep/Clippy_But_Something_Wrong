package ru.markshep.clippy_but_something_wrong.client;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.markshep.clippy_but_something_wrong.client.animations.clippy.RandomAnimation;
import ru.markshep.clippy_but_something_wrong.client.animations.clippy.Statement;
import ru.markshep.clippy_but_something_wrong.client.animations.clippy.dialogue.DialogueAnimation;
import ru.markshep.clippy_but_something_wrong.client.screens.XPMainMenuScreen;
import ru.markshep.clippy_but_something_wrong.client.utils.windows.User32Ext;

import static ru.markshep.clippy_but_something_wrong.client.utils.Utils.id;
import static ru.markshep.clippy_but_something_wrong.client.utils.Utils.translationKey;

public class Clippy_but_something_wrongClient implements ClientModInitializer {

    public static final String modId = "clippy_but_something_wrong";
    public static final Logger modLogger = LogManager.getLogger(modId);


    private static final Identifier CLIPPY_LAYER = id("hud-clippy-layer");
    public static Statement statement = Statement.IDLE;

    private static KeyBinding keyBinding;

    public static MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
        modLogger.info("Clippy загружается!");
        XPMainMenuScreen.startMenuIsActive = false;
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                translationKey("test"),
                InputUtil.GLFW_KEY_Y,
                "Clippy"
        ));

        User32Ext.INSTANCE.MessageBoxW(Pointer.NULL, new WString("Внимание! Мод находится в активной разработке\n" +
                "\n" +
                "Эта версия является ранней альфа-сборкой и может содержать критические баги, недоработанные функции и нестабильную работу. Используйте на свой страх и риск.\n" +
                "\n" +
                "Возможны:\n" +
                "- Вылеты игры и потеря прогресса\n" +
                "- Конфликты с другими модами\n" +
                "- Неожиданное поведение функций\n" +
                "- Изменения в механиках без предупреждения\n" +
                "Не рекомендуется использовать в основных мирах!" +
                "\n\nЕсли вы нашли недочёт, то напишите в тг личные сообщения: @m_shadowless"), new WString("Clippy, but something is wrong"), User32Ext.MB_OK | User32Ext.MB_ICONERROR);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding.wasPressed()) {
                DialogueAnimation.startDialogue("ААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААА");
            }
        });
        ScreenEvents.AFTER_INIT.register((minecraftClient, screen, i, i1) -> {
            if (screen instanceof TitleScreen) {
                client.setScreen(new XPMainMenuScreen());
            }
        });

        HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.HOTBAR_AND_BARS, CLIPPY_LAYER, Clippy_but_something_wrongClient::render));
    }

    private static void render(DrawContext context, RenderTickCounter tickCounter) {
        RandomAnimation.checkState(context);
        DialogueAnimation.animate(context);
    }
}
