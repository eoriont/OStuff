package net.oriont.ostuff.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.oriont.ostuff.OStuff;
import net.oriont.ostuff.client.gui.GuiOStuff;

import java.util.Random;

public class BlockTestBlock extends Block {

    public BlockTestBlock(Material blockMaterialIn, String name) {
        super(blockMaterialIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(OStuff.tabOStuff);
        this.setHardness(10f);
        this.setResistance(1000F);
        this.setSoundType(SoundType.SLIME);
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.setBlockState(pos, Blocks.TNT.getDefaultState());
        super.onBlockDestroyedByPlayer(worldIn, pos, state);
    }

    @Override
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
        worldIn.spawnEntity(new EntityBlaze(worldIn));
        super.onBlockDestroyedByExplosion(worldIn, pos, explosionIn);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 5F, false);
        super.onBlockAdded(worldIn, pos, state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiOStuff());
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.APPLE;
    }

    @Override
    public int quantityDropped(Random random) {
        return 3;
    }

    @Override
    public void dropXpOnBlockBreak(World worldIn, BlockPos pos, int amount) {
        amount = 5;
        super.dropXpOnBlockBreak(worldIn, pos, amount);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.sendMessage(new TextComponentString("You walked on test block"));
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        playerIn.inventory.addItemStackToInventory(new ItemStack(worldIn.getBlockState(pos).getBlock()));
        super.onBlockClicked(worldIn, pos, playerIn);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        player.setFire(3);
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }
}
