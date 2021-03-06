package com.ragegamingpe.ironfurnaces.common;

import com.ragegamingpe.ironfurnaces.common.block.base.ModBlock;
import com.ragegamingpe.ironfurnaces.common.block.te.TileEntityHeatAbsorber;
import com.ragegamingpe.ironfurnaces.common.block.te.TileEntityHeatGenerator;
import com.ragegamingpe.ironfurnaces.common.block.te.TileEntityIronFurnace;
import com.ragegamingpe.ironfurnaces.common.item.base.ModItem;
import com.ragegamingpe.ironfurnaces.common.lib.LibMisc;
import com.ragegamingpe.ironfurnaces.common.lib.ModBlocks;
import com.ragegamingpe.ironfurnaces.common.lib.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy
{
    protected static boolean registeredEvents = false;

    public void preInit(FMLPreInitializationEvent event)
    {
        IronFurnaces.logger = event.getModLog();
        if (!registeredEvents) {
            MinecraftForge.EVENT_BUS.register(this);
            registeredEvents = true;
        }
    }

    public void init(FMLInitializationEvent event)
    {
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    }

    public void serverStarting(FMLServerStartingEvent event)
    {
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(ModBlocks.ALL_BLOCKS.toArray(new ModBlock[ModBlocks.ALL_BLOCKS.size()]));

        GameRegistry.registerTileEntity(TileEntityIronFurnace.class, LibMisc.MOD_ID + ":furnace");
        GameRegistry.registerTileEntity(TileEntityHeatAbsorber.class, LibMisc.MOD_ID + ":heat_absorber");
        GameRegistry.registerTileEntity(TileEntityHeatGenerator.class, LibMisc.MOD_ID + ":heat_generator");
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        ItemBlock[] itemBlocks = new ItemBlock[ModBlocks.ALL_BLOCKS.size()];
        for (int i = 0; i < ModBlocks.ALL_BLOCKS.size(); i++) {
            Block block = ModBlocks.ALL_BLOCKS.get(i);
            itemBlocks[i] = (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName());
        }
        event.getRegistry().registerAll(itemBlocks);

        event.getRegistry().registerAll(ModItems.ALL_ITEMS.toArray(new ModItem[ModItems.ALL_ITEMS.size()]));

    }
}
