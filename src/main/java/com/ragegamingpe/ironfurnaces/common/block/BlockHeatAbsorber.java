package com.ragegamingpe.ironfurnaces.common.block;

import com.ragegamingpe.ironfurnaces.common.block.base.ModBlockContainer;
import com.ragegamingpe.ironfurnaces.common.block.te.TileEntityHeatAbsorber;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockHeatAbsorber extends ModBlockContainer
{
    public BlockHeatAbsorber()
    {
        super(Material.ROCK, MapColor.BLACK, "heat_absorber");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityHeatAbsorber();
    }
}
