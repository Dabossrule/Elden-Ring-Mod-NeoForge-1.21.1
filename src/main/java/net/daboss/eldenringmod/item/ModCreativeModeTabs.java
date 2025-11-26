package net.daboss.eldenringmod.item;

import net.daboss.eldenringmod.EldenRingMod;
import net.daboss.eldenringmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EldenRingMod.MOD_ID);

    public static final Supplier<CreativeModeTab> EL_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("el_rn_items_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativemodetab.daboss_items_tab"))
                    .icon(() -> new ItemStack(ModItems.BLOOD.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.METEORIC_SHARD);
                        pOutput.accept(ModItems.RAW_METEORIC);
                        pOutput.accept(ModItems.BLOOD);

                    }).build());


    public static final Supplier<CreativeModeTab> ELD_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("eld_rng_items_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativemodetab.daboss_blocks_tab"))
                    .icon(() -> new ItemStack(ModBlocks.BLOOD_BLOCK.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.BLOOD_BLOCK);
                        pOutput.accept(ModBlocks.METEORIC_ORE);
                        pOutput.accept(ModBlocks.DEEPSLATE_METEORIC_ORE);
                        pOutput.accept(ModBlocks.RAW_METEORIC_BLOCK);

                    }).build());
}
