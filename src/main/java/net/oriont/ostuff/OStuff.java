package net.oriont.ostuff;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.oriont.ostuff.block.ModBlocks;
import net.oriont.ostuff.item.ModItems;
import net.oriont.ostuff.proxy.CommonProxy;
import net.oriont.ostuff.tab.TabOStuff;

@Mod(modid = OStuff.MODID, version = OStuff.VERSION)
public class OStuff {
    public static final String MODID = "ostuff";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "net.oriont.ostuff.proxy.ClientProxy", serverSide = "net.oriont.ostuff.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static OStuff instance;

    public static TabOStuff tabOStuff;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        tabOStuff = new TabOStuff(CreativeTabs.getNextID(), "tab_ostuff");
        ModItems.preInit();
        ModBlocks.preInit();
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
