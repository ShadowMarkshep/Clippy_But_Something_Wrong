package ru.markshep.clippy_but_something_wrong.client.screens;

import com.sun.jna.platform.win32.Advapi32Util;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ru.markshep.clippy_but_something_wrong.client.screens.buttons.IconButton;
import ru.markshep.clippy_but_something_wrong.client.screens.buttons.PowerOffButton;
import ru.markshep.clippy_but_something_wrong.client.screens.buttons.StartMenuBlueButton;
import ru.markshep.clippy_but_something_wrong.client.screens.buttons.XpStartButton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ru.markshep.clippy_but_something_wrong.client.screens.MainMenuVariables.*;
import static ru.markshep.clippy_but_something_wrong.client.utils.Utils.id;

public class XPMainMenuScreen extends Screen {

    public XPMainMenuScreen() {
        super(Text.of("Главное меню"));
    }

    @Override
    protected void init() {
        assert client != null;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.options"), (button) -> this.client.setScreen(new OptionsScreen(this, this.client.options))).dimensions(this.width / 2 - 100, 10, 98, 20).build());

        XpStartButton startMenuButton = new XpStartButton(0, this.height - 17, 55, 17);
        this.addDrawableChild(startMenuButton);

        IconButton singlePlayerButton = new IconButton(32, 8, 32, 32, id("screens/icons/singleplayer.png"), "singleplayer.exe", () -> this.client.setScreen(new SelectWorldScreen(this)));
        this.addDrawableChild(singlePlayerButton);

        IconButton multiPlayerButton = new IconButton(32, 64, 32, 32, id("screens/icons/multiplayer.png"), "multiplayer.exe", () -> this.client.setScreen(new MultiplayerScreen(this)));
        this.addDrawableChild(multiPlayerButton);

        if (startMenuIsActive){
            PowerOffButton powerOffButton = new PowerOffButton(200 - 45 - 16 - client.textRenderer.getWidth(Text.of("Turn off")), this.height - 17 - 17, 16, 16);
            this.addDrawableChild(powerOffButton);
            StartMenuBlueButton settings = new StartMenuBlueButton(200, 200, id("screens/icons/settings.png"), "MC Settings", () -> this.client.setScreen(new OptionsScreen(this, this.client.options)));
            this.addDrawableChild(settings);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        //Обои
        if (wallpaperFrame > wallpaperTotalFrames) {
            wallpaperFrame = 0;
        }
        if (client != null) {
            wallpaperTotalCounter = client.getCurrentFps() / 10;
        }
        wallpaperName = String.format("%03d.png", wallpaperFrame);
        Identifier wallpaperTexture = id("screens/wallpaper/" + wallpaperName);

        context.drawTexture(RenderLayer::getGuiTextured, wallpaperTexture, 0, 0, 0, 0, this.width, this.height, this.width, this.height);

        wallpaperCounter++;
        if (wallpaperCounter > wallpaperTotalCounter) {
            wallpaperCounter = 0;
            wallpaperFrame++;
        }

        //Task Bar
        context.drawTexture(RenderLayer::getGuiTextured, id("screens/taskbar.png"), 0, this.height - 17, 0, 0, this.width, 17, this.width, 17);

        context.drawText(client.textRenderer, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), this.width - client.textRenderer.getWidth(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))) - 10, this.height - client.textRenderer.fontHeight - 3, 0xFFFFFF, true);

        //Start Menu
        if (startMenuIsActive) {
            context.drawTexture(RenderLayer::getGuiTextured, id("screens/avatar.png"), 3, this.height - 200 - 17 + 3, 0, 0, 21, 21, 21, 21);

            context.drawTexture(RenderLayer::getGuiTextured, id("screens/start_menu.png"), 0, this.height - 200 - 17, 0, 0, 160, 200, 160, 200);

            context.drawText(client.textRenderer, Advapi32Util.getUserName(), 30, (this.height - 200 - 17) + client.textRenderer.fontHeight, 0xFFFFFF, true);
        }

        //Кнопки
        super.render(context, mouseX, mouseY, delta);
    }

    public void renderBackground(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
    }
}
