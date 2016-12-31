package net.oriont.ostuff.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.oriont.ostuff.OStuff;

public class GuiButtonOStuff extends GuiButton {

    ResourceLocation texture = new ResourceLocation(OStuff.MODID, "textures/gui/book.png");
    int buttonWidth = 16;
    int buttonHeight = 14;
    int u = 175;
    int v = 1;

    public GuiButtonOStuff(int buttonId, int x, int y) {
        super(buttonId, x, y, 16, 14, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(visible) {
            mc.renderEngine.bindTexture(texture);
            hovered = mouseX >= xPosition && mouseX <= xPosition + width && mouseY >= yPosition && mouseY <= yPosition + height;

            if(hovered) {
                v = 18;
            } else {
                v = 1;
            }
            drawTexturedModalRect(xPosition, yPosition, u, v, width, height);
        }
    }

    @Override
    public void playPressSound(SoundHandler soundHandlerIn) {
        //super.playPressSound(soundHandlerIn);
        soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, 1.0F));
    }
}
