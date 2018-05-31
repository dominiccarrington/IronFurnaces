package com.ragegamingpe.ironfurnaces.common.block.te;

import com.ragegamingpe.ironfurnaces.common.block.BlockHeatConduit;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class TileEntityHeatContainer extends TileEntity implements ITickable
{
    private int ticksBeforeSearch;
    private float currentStorage;
    private TileEntityHeatContainer[] acceptors;

    public TileEntityHeatContainer()
    {

    }

    @Override
    public void update()
    {
        World world = this.world;
        if (!world.isRemote) {
            switch (this.getContainerType()) {
                case PRODUCER:
                    if (this.currentStorage < getMaxStorage()) {
                        this.currentStorage = Math.min(this.currentStorage + getProducedHeat(), getMaxStorage());
                    }

                    this.ticksBeforeSearch++;
                    if (this.ticksBeforeSearch >= 50) {
                        this.ticksBeforeSearch = 0;
                        this.acceptors = searchForAcceptors(this.getWorld(), this.getPos());
                    } else if (this.acceptors == null) {
                        this.acceptors = searchForAcceptors(this.getWorld(), this.getPos());
                    }

                    float transfer = 0.05F;
                    for (TileEntityHeatContainer acceptor : this.acceptors) {
                        if (this.currentStorage <= transfer) break;

                        if (acceptor.isInvalid()) continue;

                        if (!acceptor.isFull()) this.currentStorage -= transfer;

                        this.currentStorage += acceptor.addHeat(transfer);
                    }

                    break;

                case ACCEPTOR:
                    if (this.currentStorage > 0) {
                        this.currentStorage = Math.max(0, this.currentStorage - preformAcceptorAction());
                    }
                    break;
            }
        }
    }

    public static TileEntityHeatContainer[] searchForAcceptors(World world, BlockPos pos)
    {
        if (world == null || pos == null) return null;

        if (!(world.getTileEntity(pos) instanceof TileEntityHeatContainer)) return null;

        List<TileEntityHeatContainer> acceptors = new ArrayList<>(); // What I am finding

        List<BlockPos> checkedLocations = new ArrayList<>(); // List of checked BlockPoses
        Queue<BlockPos> currentChecks = new PriorityQueue<>(); // Queue of checks

        currentChecks.add(pos);

        while (currentChecks.size() > 0) {
            BlockPos current = currentChecks.peek();
            if (current == null) break; // WTF? Really, should not happen

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        BlockPos check = current.add(i, j, k);

                        if (checkedLocations.contains(check)) continue;

                        TileEntity te = world.getTileEntity(check);
                        Block block = world.getBlockState(check).getBlock();
                        if (block instanceof BlockHeatConduit ||
                                (te instanceof TileEntityHeatContainer && ((TileEntityHeatContainer) te).getContainerType() == ContainerType.ACCEPTOR)) {

                            if (te instanceof TileEntityHeatContainer)
                                acceptors.add((TileEntityHeatContainer) world.getTileEntity(check));

                            checkedLocations.add(check);
                            currentChecks.add(check);
                        }
                    }
                }
            }

            currentChecks.remove(current);
        }

        return acceptors.toArray(new TileEntityHeatContainer[0]);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setFloat("CurrentStorage", this.currentStorage);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.currentStorage = compound.getFloat("CurrentStorage");
    }

    public abstract ContainerType getContainerType();

    public abstract float getMaxStorage();

    public float getProducedHeat()
    {
        if (getContainerType() == ContainerType.PRODUCER) throw new RuntimeException("Must override getProducedHeat.");
        return 0;
    }

    public float preformAcceptorAction()
    {
        if (getContainerType() == ContainerType.ACCEPTOR)
            throw new RuntimeException("Must override preformAcceptorAction.");
        return 0;
    }

    public float addHeat(float transfer)
    {
        if (getContainerType() == ContainerType.ACCEPTOR) {
            this.currentStorage += transfer;
            if (this.currentStorage > this.getMaxStorage()) {
                this.currentStorage = this.getMaxStorage();
                return this.getMaxStorage() - this.currentStorage;
            }
        }

        return 0;
    }

    public boolean isFull()
    {
        return this.getCurrentStorage() >= this.getMaxStorage();
    }

    public float getCurrentStorage()
    {
        return this.currentStorage;
    }

    public enum ContainerType
    {
        PRODUCER,
        ACCEPTOR;
    }
}
