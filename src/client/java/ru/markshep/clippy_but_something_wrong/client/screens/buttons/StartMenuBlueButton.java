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

    public StartMenuBlueButton(int x, int y, int width, int height, Identifier texture, String text, Runnable onClickAction) {
        super(x, y, width, height, Text.of(text));
        this.texture = texture;
        this.onClickAction = onClickAction;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        context.drawTexture(RenderLayer::getGuiTextured,
                this.texture,
                this.getX(),
                this.getY(),
                0,
                0,
                16,
                16,
                this.width,
                this.height);

//        float scale = 0.75f;
//
//        int textW = (int) (client.textRenderer.getWidth(this.getMessage()) * scale);
//        int textH = (int) (client.textRenderer.fontHeight * scale);
//        int tx = this.getX() + (this.width - textW) / 2;
//        int ty = this.getY() + this.getHeight() + 2;
//
//        if (this.isHovered()) {
//            context.fill(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), 0x553399FF);
//
//            context.fill(tx - 1, ty - 1, tx + textW + 1, ty + textH + 1, 0xFF3399FF);
//        }
//
//        context.getMatrices().push();
//        context.getMatrices().scale(scale, scale, 1);
//
//        int color = 0xFFFFFF;
//
//        int textX = (int) (tx / 0.75f);
//        int textY = (int) (ty / 0.75f);
//
//        context.drawText(client.textRenderer, this.getMessage(), textX, textY, color, true);
//
//        context.getMatrices().pop();
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
