package com.ragegamingpe.ironfurnaces.common.lib;

import com.ragegamingpe.ironfurnaces.common.block.BlockHeatAbsorber;
import com.ragegamingpe.ironfurnaces.common.block.BlockHeatConduit;
import com.ragegamingpe.ironfurnaces.common.block.BlockHeatGenerator;
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
    public static final ModBlock HEAT_ABSORBER;
    public static final ModBlock HEAT_CONDUIT;
    public static final ModBlock HEAT_GENERATOR;

    static {
        IRON_FURNACES = ModBlockVariants.constructVariants(BlockIronFurnace.class, BlockIronFurnace.Variant.class);
        HEAT_ABSORBER = new BlockHeatAbsorber();
        HEAT_CONDUIT = new BlockHeatConduit();
        HEAT_GENERATOR = new BlockHeatGenerator();
    }
}
