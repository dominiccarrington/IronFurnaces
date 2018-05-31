package com.ragegamingpe.ironfurnaces.common.item.base;

import com.ragegamingpe.ironfurnaces.client.model.ModelLoader;
import com.ragegamingpe.ironfurnaces.common.lib.LibMisc;
import com.ragegamingpe.ironfurnaces.common.lib.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModItem extends Item implements IModItem
{
    public ModItem(String regName)
    {
        super();

        this.setUnlocalizedName(regName);
        this.setRegistryName(LibMisc.MOD_ID, regName);
        ModItems.ALL_ITEMS.add(this);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s:%s", LibMisc.MOD_ID, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return getUnlocalizedName();
    }

    @Override
    public ModItem setCreativeTab(CreativeTabs tab)
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
