package com.ragegamingpe.ironfurnaces.common.block.te;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileEntityHeatGenerator extends TileEntityHeatContainer
{
    @Override
    public float preformAcceptorAction()
    {
        float total = 0;
        for (EnumFacing direction : EnumFacing.values()) {
            BlockPos check = this.getPos().offset(direction);

            TileEntity teCheck = this.getWorld().getTileEntity(check);

            if (teCheck instanceof TileEntityFurnace) {
                if (((TileEntityFurnace) teCheck).getField(0) <= 1) {
                    ((TileEntityFurnace) teCheck).setField(0, 50);
                    total += 50;
                }
            } else if (teCheck instanceof TileEntityIronFurnace) {
                if (((TileEntityIronFurnace) teCheck).getField(0) <= 1) {
                    ((TileEntityIronFurnace) teCheck).setField(0, 50);
                    total += 50;
                }
            }
        }

        return total;
    }

    @Override
    public ContainerType getContainerType()
    {
        return ContainerType.ACCEPTOR;
    }

    @Override
    public float getMaxStorage()
    {
        return 100.0F;
    }
}
