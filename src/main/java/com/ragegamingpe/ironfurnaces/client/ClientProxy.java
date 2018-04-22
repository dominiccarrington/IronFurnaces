package com.ragegamingpe.ironfurnaces.client;

import com.ragegamingpe.ironfurnaces.common.CommonProxy;
import com.ragegamingpe.ironfurnaces.common.block.base.IModBlock;
import com.ragegamingpe.ironfurnaces.common.item.base.IModItem;
import com.ragegamingpe.ironfurnaces.common.lib.ModBlocks;
import com.ragegamingpe.ironfurnaces.common.lib.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        CommonProxy.registeredEvents = true;
        super.preInit(event);
        ModBlocks.ALL_BLOCKS.forEach(IModBlock::registerModels);
        ModItems.ALL_ITEMS.forEach(IModItem::registerModels);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        ModBlocks.ALL_BLOCKS.forEach(IModBlock::registerRender);
        ModItems.ALL_ITEMS.forEach((item) -> {
            ItemMeshDefinition def = item.registerCustomMeshDefinition();

            if (def == null) item.registerRender();
            else Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, def);
        });
    }
}
