package net.daboss.eldenringmod.datagen;

import net.daboss.eldenringmod.EldenRingMod;
import net.daboss.eldenringmod.block.ModBlocks;
import net.daboss.eldenringmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> METEORIC_SMELTABLES = List.of(ModItems.RAW_METEORIC);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_METEORIC_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_METEORIC.get())
                .unlockedBy("has_raw_meteoric", has(ModItems.RAW_METEORIC.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOOD_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.BLOOD.get())
                .unlockedBy("has_blood", has(ModItems.BLOOD.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_METEORIC.get(), 1)
                .requires(ModBlocks.RAW_METEORIC_BLOCK.get())
                .unlockedBy("has_raw_meteoric_block", has(ModBlocks.RAW_METEORIC_BLOCK.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLOOD.get(), 9)
                .requires(ModBlocks.BLOOD_BLOCK.get())
                .unlockedBy("has_blood_block", has(ModBlocks.BLOOD_BLOCK.get())).save(pRecipeOutput);

        //oreSmelting(pRecipeOutput, METEORIC_SMELTABLES, RecipeCategory.MISC, //ModItems.METEORIC_SHARD.get(), 0.25f, 200, "black_opal");

        oreBlasting(pRecipeOutput, METEORIC_SMELTABLES, RecipeCategory.MISC, ModItems.METEORIC_SHARD.get(), 0.25f, 100, "meteoric_shard");
    }

    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, EldenRingMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
