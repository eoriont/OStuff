package net.oriont.ostuff;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.oriont.ostuff.item.ModItems;

public class OStuffEventHandler {

    //entity joins world
    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent e) {
        if(e.getEntity() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) e.getEntity();
            //p.inventory.addItemStackToInventory(new ItemStack(Items.SHEARS));
        }
    }

    //entity dies and drops items
    @SubscribeEvent
    public void livingDrops(LivingDropsEvent e) {
        if(e.getEntity() instanceof EntityMob) {
            e.getDrops().add(new EntityItem(e.getEntity().world, e.getEntity().posX, e.getEntity().posY, e.getEntity().posZ, new ItemStack(ModItems.test)));
        }
    }

    //entity hurt
    @SubscribeEvent
    public void livingHurt(LivingHurtEvent e) {
        if(e.getSource().getEntity() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) e.getSource().getEntity();
            if(p.getHeldItemMainhand().getItem() == Items.APPLE) {
                e.setAmount(10);
                e.getEntity().setFire(10);
            }
        }
    }

    //Player Picking up items
    @SubscribeEvent
    public void itemPickup(EntityItemPickupEvent e) {
        if(e.getItem().getEntityItem().getItem() == Items.DIAMOND) {
            e.getEntityPlayer().setFire(5);
            e.getEntityPlayer().inventory.clear();
        }
    }

    //Ticks
    @SubscribeEvent
    public void livingUpdate(LivingEvent.LivingUpdateEvent e) {
//        if(e.getEntity().isSneaking()) {
//            e.getEntity().performHurtAnimation();
//        }

        if(e.getEntity() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) e.getEntity();
            if(p.getHeldItemMainhand() != null) {
                if (p.getHeldItemMainhand().getItem() == Items.APPLE) {
                    p.setFire(5);
                }
            }
        }
    }

    //changing item tooltips
    @SubscribeEvent
    public void itemTooltip(ItemTooltipEvent e) {
        if(e.getItemStack().getItem() == Items.APPLE) {
            e.getToolTip().add("§4Flaming Apple");
        }
    }

    //block breaking
    @SubscribeEvent
    public void breakEvent(BlockEvent.BreakEvent e) {
        if(e.getState().getBlock() == Blocks.DIRT) {
            e.setExpToDrop(10);
            BlockPos pos = e.getPos();
            e.getWorld().spawnEntity(new EntityItem(e.getWorld(), pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.DIAMOND)));
        }
    }

}
