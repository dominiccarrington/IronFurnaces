package com.ragegamingpe.ironfurnaces.common.item.base;

import com.ragegamingpe.ironfurnaces.common.block.base.IModBlock;
import net.minecraft.client.renderer.ItemMeshDefinition;

public interface IModItem extends IModBlock
{
    default ItemMeshDefinition registerCustomMeshDefinition()
    {
        return null;
    }
}
