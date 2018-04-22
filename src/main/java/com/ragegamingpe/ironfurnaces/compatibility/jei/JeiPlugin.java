package com.ragegamingpe.ironfurnaces.compatibility.jei;

import com.ragegamingpe.ironfurnaces.common.block.BlockIronFurnace;
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
        for (BlockIronFurnace block : ModBlocks.IRON_FURNACES.values()) {
            registry.addRecipeCatalyst(new ItemStack(block), VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);
        }
    }
}
