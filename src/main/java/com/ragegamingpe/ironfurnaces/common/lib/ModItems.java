package com.ragegamingpe.ironfurnaces.common.lib;

import com.ragegamingpe.ironfurnaces.common.item.ItemHeat;
import com.ragegamingpe.ironfurnaces.common.item.base.ModItem;

import java.util.ArrayList;

public class ModItems
{
    public static final ArrayList<ModItem> ALL_ITEMS = new ArrayList<>();

    public static final ModItem HEAT;

    static {
        HEAT = new ItemHeat();
    }
}
