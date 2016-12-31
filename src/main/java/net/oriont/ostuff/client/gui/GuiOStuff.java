package net.oriont.ostuff.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.oriont.ostuff.OStuff;
import net.oriont.ostuff.block.ModBlocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiOStuff extends GuiScreen {

    ResourceLocation texture = new ResourceLocation(OStuff.MODID, "textures/gui/book.png");
    int guiHeight = 228;
    int guiWidth = 175;

    GuiButton button1;
    GuiButtonOStuff arrow;
    GuiTextField textBox;

    final int BUTTON1 = 0, ARROW = 1;
    String title = "OStuff";

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        int centerX = (width / 2) - guiWidth / 2;
        int centerY = (height / 2) - guiHeight / 2;

        GlStateManager.pushMatrix();
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.color(1, 1, 1, 1);
            mc.renderEngine.bindTexture(texture);
            drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth, guiHeight);
        }
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        {
            GlStateManager.translate((width / 2) - fontRendererObj.getStringWidth(title), centerY + 10, 0);
            GlStateManager.scale(2, 2, 2);
            fontRendererObj.drawString(title, 0, 0, 0xFF0000);
        }
        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);
        //button1.drawButton(mc, mouseX, mouseY);
        ItemStack icon = new ItemStack(ModBlocks.test);

        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(centerX, centerY, 0);
            GlStateManager.scale(2, 2, 2);
            mc.getRenderItem().renderItemAndEffectIntoGUI(icon, 0, 0);
        }
        GlStateManager.popMatrix();

        List<String> text = new ArrayList<String>();
        text.add("Tooltip!");
        text.add(icon.getDisplayName());
        drawTooltip(text, mouseX, mouseY, centerX, centerY, 16*2, 16*2);
        textBox.drawTextBox();
    }

    public void drawTooltip(List<String> lines, int mouseX, int mouseY, int posX, int posY, int width, int height) {
        if(mouseX >= posX && mouseX <= posX + width && mouseY >= posY && mouseY <= posY + height) {
            drawHoveringText(lines, mouseX, mouseY);
        }
    }

    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(button1 = new GuiButton(BUTTON1, (width / 2) - 100 / 2, height-40, 100, 20, "Close"));
        buttonList.add(arrow = new GuiButtonOStuff(ARROW, 100, 100));
        textBox = new GuiTextField(0, fontRendererObj, 0, 0, 100, 20);
        updateButtons();
        super.initGui();
    }

    public void updateButtons() {
        button1.enabled = title.equals("OStuff");
    }

    public void updateTextBoxes() {
        if(!textBox.getText().isEmpty()) {
            if(!textBox.isFocused()) {
                title = textBox.getText();
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        switch (button.id) {
            case BUTTON1:
                mc.displayGuiScreen(null);
                break;
            case ARROW:
                mc.displayGuiScreen(new GuiInventory(mc.player));
        }
        updateButtons();
        super.actionPerformed(button);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        textBox.textboxKeyTyped(typedChar, keyCode);
        updateTextBoxes();
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        textBox.mouseClicked(mouseX, mouseY, mouseButton);
        updateTextBoxes();
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }
}
