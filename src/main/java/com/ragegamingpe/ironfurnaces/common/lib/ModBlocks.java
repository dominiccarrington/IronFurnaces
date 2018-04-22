package com.ragegamingpe.ironfurnaces.common.lib;

import com.ragegamingpe.ironfurnaces.common.block.BlockIronFurnace;
import com.ragegamingpe.ironfurnaces.common.block.base.ModBlock;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks
{
    public static final List<ModBlock> ALL_BLOCKS = new ArrayList<>();

    public static final ModBlock IRON_FURNACE;
    public static final ModBlock GOLD_FURNACE;
    public static final ModBlock DIAMOND_FURNACE;
    public static final ModBlock EMERALD_FURNACE;
    public static final ModBlock OBSIDIAN_FURNACE;

    static {
        IRON_FURNACE = new BlockIronFurnace(BlockIronFurnace.Variant.IRON);
        GOLD_FURNACE = new BlockIronFurnace(BlockIronFurnace.Variant.GOLD);
        DIAMOND_FURNACE = new BlockIronFurnace(BlockIronFurnace.Variant.DIAMOND);
        EMERALD_FURNACE = new BlockIronFurnace(BlockIronFurnace.Variant.EMERALD);
        OBSIDIAN_FURNACE = new BlockIronFurnace(BlockIronFurnace.Variant.OBSIDIAN);


    }
}
