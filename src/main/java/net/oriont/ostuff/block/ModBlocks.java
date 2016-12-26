package net.oriont.ostuff.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.oriont.ostuff.OStuff;

public class ModBlocks {
    public static Block test;

    public static void preInit() {
        test = new BlockTestBlock(Material.CLOTH, "test_block");

        registerBlocks();
    }

    public static void registerBlocks() {
        registerBlock(test, "test_block");
    }

    public static void registerBlock(Block block, String name) {
        GameRegistry.register(block, new ResourceLocation(OStuff.MODID, name));
        GameRegistry.register(new ItemBlock(block), new ResourceLocation(OStuff.MODID, name));
    }

    public static void registerRenders() {
        registerRender(test);
    }

    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(OStuff.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

}
