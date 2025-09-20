package com.cxxi.customfluidinteraction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import slimeknights.tconstruct.fluids.TinkerFluids;

@Mod("customfluidinteraction")
public class CustomFluidInteraction {

    public CustomFluidInteraction() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onNeighborNotify(BlockEvent.NeighborNotifyEvent event) {
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        FluidState fluidState = state.getFluidState();

        if (fluidState.getType() == Fluids.WATER) {
            for (Direction dir : Direction.values()) {
                BlockPos neighborPos = pos.relative(dir);
                FluidState neighborFluid = level.getFluidState(neighborPos);

                if (neighborFluid.getType() == TinkerFluids.moltenObsidian.get()) {
                    level.setBlockAndUpdate(pos, Blocks.DEEPSLATE.defaultBlockState());
                    return;
                }
            }
        }
    }
}