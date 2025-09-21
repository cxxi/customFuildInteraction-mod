package com.cxxi.customefluidinteraction.datagen;

import com.cxxi.customfluidinteraction.CustomFluidInteraction;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import java.utils.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = CustomFluidInteraction.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@subscribeEvent
	public static void gatherData(GatherDataEvent event) {

		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));

	}
}