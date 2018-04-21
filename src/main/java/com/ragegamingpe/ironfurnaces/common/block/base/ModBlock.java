package com.ragegamingpe.ironfurnaces.common.block.base;

import com.ragegamingpe.ironfurnaces.client.model.ModelLoader;
import com.ragegamingpe.ironfurnaces.common.lib.LibMisc;
import com.ragegamingpe.ironfurnaces.common.lib.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlock extends Block implements IModBlock
{
    public ModBlock(Material materialIn, String regName)
    {
        super(materialIn);

        this.setUnlocalizedName(regName);
        this.setRegistryName(LibMisc.MOD_ID, regName);
        ModBlocks.ALL_BLOCKS.add(this);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s:%s", LibMisc.MOD_ID, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public ModBlock setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public void registerRender()
    {
        ModelLoader.registerItemModel(this, 0, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }
}
