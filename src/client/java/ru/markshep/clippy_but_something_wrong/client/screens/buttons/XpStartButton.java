package ru.markshep.clippy_but_something_wrong.client.screens.buttons;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import ru.markshep.clippy_but_something_wrong.client.screens.CustomMainMenuScreen;

import static ru.markshep.clippy_but_something_wrong.client.utils.Utils.id;

public class XpStartButton extends ClickableWidget {

    public XpStartButton(int x, int y, int width, int height) {
        super(x, y, width, height, Text.empty());
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        if (CustomMainMenuScreen.startMenuIsActive){
            context.drawTexture(RenderLayer::getGuiTextured,
                    id("screens/start_active.png"),
                    this.getX(),
                    this.getY(),
                    0,
                    0,
                    this.width,
                    this.height,
                    this.width ,
                    this.height);
        } else {
            if(this.isHovered()) {
                context.drawTexture(RenderLayer::getGuiTextured,
                        id("screens/start_hovered.png"),
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
                        id("screens/start_nonactive.png"),
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
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isMouseOver(mouseX, mouseY)) {
            CustomMainMenuScreen.startMenuIsActive = !CustomMainMenuScreen.startMenuIsActive;
            return true;
        }
        return false;
    }

}
