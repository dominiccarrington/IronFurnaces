package com.ragegamingpe.ironfurnaces.common.block;

import com.google.gson.JsonObject;
import com.ragegamingpe.ironfurnaces.client.model.ModelCreator;
import com.ragegamingpe.ironfurnaces.common.block.base.ModBlock;
import com.ragegamingpe.ironfurnaces.common.block.te.TileEntityHeatContainer;
import com.ragegamingpe.ironfurnaces.common.lib.LibMisc;
import com.ragegamingpe.ironfurnaces.common.lib.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class BlockHeatConduit extends ModBlock
{
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool WEST = PropertyBool.create("west");

    public BlockHeatConduit()
    {
        super(Material.IRON, "heat_conduit");

        this.setDefaultState(this.blockState
                .getBaseState()
                .withProperty(UP, false)
                .withProperty(DOWN, false)
                .withProperty(NORTH, false)
                .withProperty(SOUTH, false)
                .withProperty(EAST, false)
                .withProperty(WEST, false)
        );
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state
                .withProperty(UP, checkBlock(EnumFacing.DOWN, worldIn, pos))
                .withProperty(DOWN, checkBlock(EnumFacing.UP, worldIn, pos))
                .withProperty(NORTH, checkBlock(EnumFacing.NORTH, worldIn, pos))
                .withProperty(SOUTH, checkBlock(EnumFacing.SOUTH, worldIn, pos))
                .withProperty(EAST, checkBlock(EnumFacing.EAST, worldIn, pos))
                .withProperty(WEST, checkBlock(EnumFacing.WEST, worldIn, pos));
    }

    public static boolean checkBlock(EnumFacing direction, IBlockAccess world, BlockPos pos)
    {
        BlockPos check = pos.offset(direction);
        TileEntity entity = world.getTileEntity(check);
        IBlockState block = world.getBlockState(check);

        return block.getBlock() == ModBlocks.HEAT_CONDUIT || entity instanceof TileEntityHeatContainer;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, DOWN, EAST, NORTH, SOUTH, UP, WEST);
    }

    public static IProperty[] getAllProperties()
    {
        return new IProperty[]{DOWN, EAST, NORTH, SOUTH, UP, WEST};
    }

    public static JsonObject generateItemModel(Boolean down, Boolean east, Boolean north, Boolean south, Boolean up, Boolean west)
    {
        return ModelCreator.generateDefaultItemModel("heat_conduit/heat_conduit_mnsewud");
    }

    public static JsonObject generateBlockState(Boolean down, Boolean east, Boolean north, Boolean south, Boolean up, Boolean west)
    {
        JsonObject ret = new JsonObject();

        String fileName = "heat_conduit_m";
        if (north) fileName += "n";
        if (south) fileName += "s";
        if (east) fileName += "e";
        if (west) fileName += "w";
        if (up) fileName += "u";
        if (down) fileName += "d";

        ret.addProperty("model", LibMisc.MOD_ID + ":heat_conduit/" + fileName);

        return ret;
    }

    public static Pair<String, String> generateBlockModel(Boolean down, Boolean east, Boolean north, Boolean south, Boolean up, Boolean west)
    {
        String fileName = "heat_conduit_m";

        String fileContents = "{\n" +
                "  \"credit\": \"Made with Blockbench\",\n" +
                "  \"textures\": {\n" +
                "    \"0\": \"minecraft:blocks/iron_block\",\n" +
                "    \"particle\": \"minecraft:blocks/iron_block\"\n" +
                "  },\n" +
                "  \"elements\": [\n" +
                "    {\n" +
                "      \"name\": \"Middle\",\n" +
                "      \"from\": [6, 6, 6],\n" +
                "      \"to\": [10, 10, 10],\n" +
                "      \"faces\": {\n" +
                "        \"north\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                "        \"east\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                "        \"south\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                "        \"west\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                "        \"up\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                "        \"down\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"}\n" +
                "      }\n" +
                "    }\n";

        if (north) {
            fileName += "n";
            fileContents += "    ,{\n" +
                    "      \"name\": \"North\",\n" +
                    "      \"from\": [6, 6, 0],\n" +
                    "      \"to\": [10, 10, 6],\n" +
                    "      \"faces\": {\n" +
                    "        \"north\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"east\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"south\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"west\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"up\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"down\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"}\n" +
                    "      }\n" +
                    "    }\n";
        }

        if (south) {
            fileName += "s";
            fileContents += "    ,{\n" +
                    "      \"name\": \"South\",\n" +
                    "      \"from\": [6, 6, 10],\n" +
                    "      \"to\": [10, 10, 16],\n" +
                    "      \"faces\": {\n" +
                    "        \"north\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"east\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"south\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"west\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"up\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"down\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"}\n" +
                    "      }\n" +
                    "    }\n";
        }
        if (east) {
            fileName += "e";
            fileContents += "    ,{\n" +
                    "      \"name\": \"East\",\n" +
                    "      \"from\": [10, 6, 6],\n" +
                    "      \"to\": [16, 10, 10],\n" +
                    "      \"faces\": {\n" +
                    "        \"north\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"east\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"south\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"west\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"up\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 90},\n" +
                    "        \"down\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 270}\n" +
                    "      }\n" +
                    "    }\n";
        }

        if (west) {
            fileName += "w";
            fileContents += "    ,{\n" +
                    "      \"name\": \"West\",\n" +
                    "      \"from\": [0, 6, 6],\n" +
                    "      \"to\": [6, 10, 10],\n" +
                    "      \"faces\": {\n" +
                    "        \"north\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"east\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"south\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"west\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"up\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 90},\n" +
                    "        \"down\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 270}\n" +
                    "      }\n" +
                    "    }\n";
        }

        if (up) {
            fileName += "u";
            fileContents += "    ,{\n" +
                    "      \"name\": \"Up\",\n" +
                    "      \"from\": [6, 0, 6],\n" +
                    "      \"to\": [10, 6, 10],\n" +
                    "      \"faces\": {\n" +
                    "        \"north\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 90},\n" +
                    "        \"east\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"south\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 270},\n" +
                    "        \"west\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 180},\n" +
                    "        \"up\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 270},\n" +
                    "        \"down\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 270}\n" +
                    "      }\n" +
                    "    }\n";
        }

        if (down) {
            fileName += "d";
            fileContents += "    ,{\n" +
                    "      \"name\": \"Down\",\n" +
                    "      \"from\": [6, 10, 6],\n" +
                    "      \"to\": [10, 16, 10],\n" +
                    "      \"faces\": {\n" +
                    "        \"north\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 90},\n" +
                    "        \"east\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\"},\n" +
                    "        \"south\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 270},\n" +
                    "        \"west\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 180},\n" +
                    "        \"up\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 270},\n" +
                    "        \"down\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#0\", \"rotation\": 270}\n" +
                    "      }\n" +
                    "    }\n";
        }
        fileContents += "  ]}";

        return new ImmutablePair<>("heat_conduit/" + fileName, fileContents);
    }
}
