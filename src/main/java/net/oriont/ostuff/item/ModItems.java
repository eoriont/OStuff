package net.oriont.ostuff.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.oriont.ostuff.OStuff;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static Item test;
    public static Item chestplate;

    public static List<Item> itemsList = new ArrayList<Item>();

    public static void preInit() {

        itemsList.add(test = new ItemTestItem(EnumHelper.addToolMaterial("test", 3, 4000, 4.0f, 4000f, 1000), "test_item"));
        itemsList.add(chestplate = new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, EntityEquipmentSlot.CHEST).setUnlocalizedName("chestplate").setCreativeTab(OStuff.tabOStuff));

        registerItems();
    }

    public static void registerItems() {
        for(int i = 0; i < itemsList.size(); i++) {
            GameRegistry.register(itemsList.get(i), new ResourceLocation(OStuff.MODID, itemsList.get(i).getUnlocalizedName().substring(5)));
        }
        //GameRegistry.register(test, new ResourceLocation(OStuff.MODID, "test_item"));
    }

    public static void registerRenders() {
        for(int i = 0; i < itemsList.size(); i++) {
            registerRender(itemsList.get(i));
        }
        //registerRender(test);
    }

    public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(OStuff.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

}
