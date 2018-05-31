package com.ragegamingpe.ironfurnaces.common.block.te;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityHeatAbsorber extends TileEntityHeatContainer
{
    @Override
    public ContainerType getContainerType()
    {
        return ContainerType.PRODUCER;
    }

    @Override
    public float getMaxStorage()
    {
        return 10;
    }

    @Override
    public float getProducedHeat()
    {
        World world = this.getWorld();
        BlockPos pos = this.getPos();

        float retValue = 0;
        Block checkBlock = world.getBlockState(pos.offset(EnumFacing.DOWN, 1)).getBlock();
        if (checkBlock == Blocks.LAVA) retValue += 0.1F;
        else if (checkBlock == Blocks.FIRE) retValue += 0.05F;
        else if (world.getBiome(pos).getTemperature(pos) >= 2.0F) retValue += 0.1F;

        return retValue;
    }
}
