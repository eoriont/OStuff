package net.oriont.ostuff.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.oriont.ostuff.OStuff;

public class BlockTestBlock extends Block {

    public BlockTestBlock(Material blockMaterialIn, String name) {
        super(blockMaterialIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(OStuff.tabOStuff);
        this.setHardness(10f);
        this.setResistance(1000F);
        this.setSoundType(SoundType.SLIME);
    }
}
