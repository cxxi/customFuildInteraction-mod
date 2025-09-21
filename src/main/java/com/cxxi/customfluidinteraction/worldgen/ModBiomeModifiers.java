package com.cxxi.customfluidinteraction.worldgen;

import com.cxxi.customfluidinteraction.CustomFluidInteraction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {

	public static final ResourceKey<BiomeModifier> ADD_RUBY_ORE = registerKey("add_ruby_ore");

	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		var biomes = context.lookup(Registries.BIOME);

		context.register(ADD_RUBY_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
			biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
			HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.RUBY_ORE_PLACED_KEY)),
			GenerationStep.Decoration.UNDERGROUND_ORES
		));
	}

	public static ResourceKey<BiomeModifier> registerKey(String name) {
		return ResourceKey.create(ForgeRegistries.keys.BIOME_MODIFIERS, new ResourceLocation(CustomFluidInteraction.MOD_ID, name));
	}
	
}