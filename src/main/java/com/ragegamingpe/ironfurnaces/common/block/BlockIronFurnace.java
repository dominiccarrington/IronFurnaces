package com.ragegamingpe.ironfurnaces.common.block;

import com.ragegamingpe.ironfurnaces.common.block.base.ModBlockVariants;
import com.ragegamingpe.ironfurnaces.common.block.te.TileEntityIronFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Random;

public class BlockIronFurnace extends ModBlockVariants implements ITileEntityProvider
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool LIT = PropertyBool.create("lit");
    private static boolean keepInventory;

    private Variant variant;

    public BlockIronFurnace(IBaseVariant variant)
    {
        super(Material.ROCK, "furnace", variant);
        this.variant = (Variant) variant;
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setDefaultState(makeDefaultState());
        this.setHardness(this.variant.getBaseBlock().getBlockHardness(null, null, null) <= 3.5F ? 3.5F : this.variant.getBaseBlock().getBlockHardness(null, null, null));
        this.setSoundType(SoundType.STONE);
    }

    private IBlockState makeDefaultState()
    {
        return this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LIT, false);
    }

    @Override
    public int getLightValue(IBlockState state)
    {
        return state.getValue(LIT) ? 15 : 0;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return getLightValue(state);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(state.getBlock(), 1, 0);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        setDefaultFacing(worldIn, pos, state);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityIronFurnace) {
                ((TileEntityIronFurnace) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState thisState)
    {
        if (!worldIn.isRemote) {
            IBlockState state = worldIn.getBlockState(pos.north());
            IBlockState state1 = worldIn.getBlockState(pos.south());
            IBlockState state2 = worldIn.getBlockState(pos.west());
            IBlockState state3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = thisState.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && state.isFullBlock() && !state1.isFullBlock())
                enumfacing = EnumFacing.SOUTH;
            else if (enumfacing == EnumFacing.SOUTH && state1.isFullBlock() && !state.isFullBlock())
                enumfacing = EnumFacing.NORTH;
            else if (enumfacing == EnumFacing.WEST && state2.isFullBlock() && !state3.isFullBlock())
                enumfacing = EnumFacing.EAST;
            else if (enumfacing == EnumFacing.EAST && state3.isFullBlock() && !state2.isFullBlock())
                enumfacing = EnumFacing.WEST;

            worldIn.setBlockState(pos, thisState.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityIronFurnace) {
                playerIn.displayGUIChest((TileEntityIronFurnace) tileentity);
                playerIn.addStat(StatList.FURNACE_INTERACTION);
            }
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (state.getValue(LIT)) {
            EnumFacing enumfacing = state.getValue(FACING);
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = (double) pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            if (rand.nextDouble() < 0.1D) {
                world.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            switch (enumfacing) {
                case WEST:
                    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    world.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case EAST:
                    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    world.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case NORTH:
                    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    break;
                case SOUTH:
                    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
                    world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!keepInventory) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityIronFurnace) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityIronFurnace) tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        EnumFacing facing = state.getValue(FACING);
        boolean lit = state.getValue(LIT);

        return facing.getIndex() | (lit ? 8 : 0);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta & 7);
        boolean lit = (meta & 8) > 0;

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) enumfacing = EnumFacing.NORTH;

        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(LIT, lit);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING, LIT);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityIronFurnace(this.variant);
    }


    protected boolean isInvalidNeighbor(World worldIn, BlockPos pos, EnumFacing facing)
    {
        return worldIn.getBlockState(pos.offset(facing)).getMaterial() == Material.CACTUS;
    }

    protected boolean hasInvalidNeighbor(World worldIn, BlockPos pos)
    {
        return this.isInvalidNeighbor(worldIn, pos, EnumFacing.NORTH) || this.isInvalidNeighbor(worldIn, pos, EnumFacing.SOUTH) || this.isInvalidNeighbor(worldIn, pos, EnumFacing.WEST) || this.isInvalidNeighbor(worldIn, pos, EnumFacing.EAST);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (te instanceof IWorldNameable && ((IWorldNameable) te).hasCustomName()) {
            player.addStat(StatList.getBlockStats(this));
            player.addExhaustion(0.005F);

            if (worldIn.isRemote) {
                return;
            }

            int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
            Item item = this.getItemDropped(state, worldIn.rand, i);

            if (item == Items.AIR) {
                return;
            }

            ItemStack itemstack = new ItemStack(item, this.quantityDropped(worldIn.rand));
            itemstack.setStackDisplayName(((IWorldNameable) te).getName());
            spawnAsEntity(worldIn, pos, itemstack);
        } else {
            super.harvestBlock(worldIn, player, pos, state, null, stack);
        }
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param)
    {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(id, param);
    }

    public static void setState(boolean burning, World world, BlockPos pos)
    {
        IBlockState currentState = world.getBlockState(pos);
        TileEntity te = world.getTileEntity(pos);
        keepInventory = true;
        world.setBlockState(pos, currentState.withProperty(LIT, burning), 1 | 2);
        keepInventory = false;

        if (te != null) {
            te.validate();
            world.setTileEntity(pos, te);
        }

    }

    public enum Variant implements IBaseVariant
    {
        IRON(2, Blocks.IRON_BLOCK),
        GOLD(3, Blocks.GOLD_BLOCK),
        DIAMOND(4, Blocks.DIAMOND_BLOCK),
        EMERALD(4, Blocks.EMERALD_BLOCK),
        OBSIDIAN(5, Blocks.OBSIDIAN);

        public static final HashMap<String, Variant> LOOKUP_TABLE = new HashMap<>();

        private final int speedFactor;
        private final Block baseBlock;

        Variant(int speedFactor, Block base)
        {
            this.speedFactor = speedFactor;
            this.baseBlock = base;
        }

        public int getSpeedFactor()
        {
            return this.speedFactor;
        }

        public Block getBaseBlock()
        {
            return this.baseBlock;
        }

        public static Variant fromString(String variant)
        {
            return LOOKUP_TABLE.getOrDefault(variant.toLowerCase(), IRON);
        }

        static {
            for (Variant v : values()) {
                LOOKUP_TABLE.put(v.name().toLowerCase(), v);
            }
        }
    }
}
