package net.oriont.ostuff.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.oriont.ostuff.OStuff;

public class ModItems {

    public static Item test;

    public static void preInit() {
        test = new ItemTestItem(EnumHelper.addToolMaterial("test", 3, 4000, 4.0f, 20.0f, 20), "test_item");

        registerItems();
    }

    public static void registerItems() {
        GameRegistry.register(test, new ResourceLocation(OStuff.MODID, "test_item"));
    }

    public static void registerRenders() {
        registerRender(test);
    }

    public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(OStuff.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

}
