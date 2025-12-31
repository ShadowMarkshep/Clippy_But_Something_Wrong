package ru.markshep.clippy_but_something_wrong.client.screens.buttons;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static ru.markshep.clippy_but_something_wrong.client.Clippy_but_something_wrongClient.client;
import static ru.markshep.clippy_but_something_wrong.client.screens.MainMenuVariables.selectedButton;

public class StartMenuBlueButton extends ClickableWidget {

    private final Identifier texture;
    private final Runnable onClickAction;

    public StartMenuBlueButton(int x, int y, Identifier texture, String text, Runnable onClickAction) {
        super(x, y, 16 + 32 + client.textRenderer.getWidth(text) + 1, 16 + 1, Text.of(text));
        this.texture = texture;
        this.onClickAction = onClickAction;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        if (this.isHovered()) {

            context.fill(this.getX() - 1, this.getY() - 1, this.getX() + this.getWidth(), this.getY() + this.getHeight(), 0xFF0052CC);

            context.drawTexture(RenderLayer::getGuiTextured, this.texture, this.getX(), this.getY(), 0, 0, 16, 16, 16, 16);

            context.drawText(client.textRenderer, this.getMessage(), this.getX() + 32, this.getY() + 4, 0xFFFFFF, true);

        } else {

            context.drawTexture(RenderLayer::getGuiTextured, this.texture, this.getX(), this.getY(), 0, 0, 16, 16, 16, 16);

            context.drawText(client.textRenderer, this.getMessage(), this.getX() + 32, this.getY() + 4, 0x696969, true);

        }
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isMouseOver(mouseX, mouseY) && this.onClickAction != null) {
            this.onClickAction.run();
            return true;
        } else {
            return false;
        }
    }
}
