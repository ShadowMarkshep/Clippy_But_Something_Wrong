package ru.markshep.clippy_but_something_wrong.client.screens.buttons;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;

import static ru.markshep.clippy_but_something_wrong.client.Clippy_but_something_wrongClient.client;
import static ru.markshep.clippy_but_something_wrong.client.utils.Utils.id;

public class PowerOffButton extends ClickableWidget {

    private boolean isClicked = false;

    public PowerOffButton(int x, int y, int width, int height) {
        super(x, y, width, height, Text.empty());
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        context.drawText(client.textRenderer, Text.translatable("menu.quit"), this.getX(), this.getY(), 0x000000, true);
        if(this.isHovered()) {
            context.drawTexture(RenderLayer::getGuiTextured,
                    id("screens/start_menu_buttons/power_off_hovered.png"),
                    this.getX(),
                    this.getY(),
                    0,
                    0,
                    this.width,
                    this.height,
                    this.width,
                    this.height);
        } else {
            context.drawTexture(RenderLayer::getGuiTextured,
                    id("screens/start_menu_buttons/power_off_base.png"),
                    this.getX(),
                    this.getY(),
                    0,
                    0,
                    this.width,
                    this.height,
                    this.width ,
                    this.height);
        }
    }
    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isMouseOver(mouseX, mouseY)) {
            this.isClicked = true;
            try {
                wait(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            client.scheduleStop();
            return true;
        }
        return false;
    }

}
