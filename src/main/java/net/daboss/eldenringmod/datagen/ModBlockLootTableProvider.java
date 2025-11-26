package net.daboss.eldenringmod.datagen;

import net.daboss.eldenringmod.block.ModBlocks;
import net.daboss.eldenringmod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {

        this.add(ModBlocks.METEORIC_ORE.get(),
                block -> createOreDrop(ModBlocks.METEORIC_ORE.get(), ModItems.RAW_METEORIC.get()));
        this.add(ModBlocks.DEEPSLATE_METEORIC_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_METEORIC_ORE.get(), ModItems.RAW_METEORIC.get(), 2, 5));

        this.add(ModBlocks.RAW_METEORIC_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.RAW_METEORIC_BLOCK.get(), ModItems.RAW_METEORIC.get(), 2, 5));

        this.add(ModBlocks.BLOOD_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.BLOOD_BLOCK.get(), ModItems.BLOOD.get(), 1, 3));

    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
