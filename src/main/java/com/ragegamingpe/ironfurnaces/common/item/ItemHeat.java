package com.ragegamingpe.ironfurnaces.common.item;

import com.ragegamingpe.ironfurnaces.common.block.te.TileEntityHeatContainer;
import com.ragegamingpe.ironfurnaces.common.item.base.ModItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemHeat extends ModItem
{
    public ItemHeat()
    {
        super("heat");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote) {
            TileEntity entity = worldIn.getTileEntity(pos);
            if (entity instanceof TileEntityHeatContainer) {
                player.sendMessage(new TextComponentString("Heat stored: " + ((TileEntityHeatContainer) entity).getCurrentStorage()));
            }
        }
        return EnumActionResult.SUCCESS;
    }
}
