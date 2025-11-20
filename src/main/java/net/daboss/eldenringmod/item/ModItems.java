package net.daboss.eldenringmod.item;

import net.daboss.eldenringmod.EldenRingMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EldenRingMod.MOD_ID);

    public static final DeferredItem<Item> METEORIC_SHARD = ITEMS.register("meteoric_shard",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_METEORIC = ITEMS.register("raw_meteoric",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
