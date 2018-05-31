package com.ragegamingpe.ironfurnaces.common.block;

import com.ragegamingpe.ironfurnaces.common.block.base.ModBlockContainer;
import com.ragegamingpe.ironfurnaces.common.block.te.TileEntityHeatGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockHeatGenerator extends ModBlockContainer
{
    public BlockHeatGenerator()
    {
        super(Material.IRON, "heat_generator");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityHeatGenerator();
    }
}
