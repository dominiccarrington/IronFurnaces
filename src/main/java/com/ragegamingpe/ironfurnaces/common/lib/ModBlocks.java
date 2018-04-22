package com.ragegamingpe.ironfurnaces.common.lib;

import com.ragegamingpe.ironfurnaces.common.block.BlockIronFurnace;
import com.ragegamingpe.ironfurnaces.common.block.base.ModBlock;
import com.ragegamingpe.ironfurnaces.common.block.base.ModBlockVariants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModBlocks
{
    public static final List<ModBlock> ALL_BLOCKS = new ArrayList<>();

    public static final Map<BlockIronFurnace.Variant, BlockIronFurnace> IRON_FURNACES;

    static {
        IRON_FURNACES = ModBlockVariants.constructVariants(BlockIronFurnace.class, BlockIronFurnace.Variant.class);
    }
}
