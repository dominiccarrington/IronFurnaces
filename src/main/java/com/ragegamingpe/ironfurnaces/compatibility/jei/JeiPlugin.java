package com.ragegamingpe.ironfurnaces.compatibility.jei;

import com.ragegamingpe.ironfurnaces.common.lib.ModBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JeiPlugin implements IModPlugin
{
    @Override
    public void register(IModRegistry registry)
    {
        for (ItemStack i : new ItemStack[]{
                new ItemStack(ModBlocks.IRON_FURNACE),
                new ItemStack(ModBlocks.GOLD_FURNACE),
                new ItemStack(ModBlocks.DIAMOND_FURNACE),
                new ItemStack(ModBlocks.EMERALD_FURNACE),
                new ItemStack(ModBlocks.OBSIDIAN_FURNACE)}) {
            registry.addRecipeCatalyst(i, VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);
        }
    }
}
