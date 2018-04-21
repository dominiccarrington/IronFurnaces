package com.ragegamingpe.ironfurnaces.common.item.base;

import com.ragegamingpe.ironfurnaces.client.model.ModelLoader;
import net.minecraft.item.Item;

public class ModItem extends Item implements IModItem
{
    @Override
    public void registerRender()
    {
        ModelLoader.registerItemModel(this, 0, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }
}
