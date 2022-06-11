package com.unlikepaladin.pfm.blocks;

import com.unlikepaladin.pfm.PaladinFurnitureMod;
import com.unlikepaladin.pfm.blocks.blockentities.StoveBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import static com.unlikepaladin.pfm.blocks.KitchenDrawer.rotateShape;

public class KitchenCounterOven extends Stove{
    public KitchenCounterOven(Settings settings) {
        super(settings);
    }
    public static final BooleanProperty UP = Properties.UP;
    public static final BooleanProperty DOWN = Properties.DOWN;

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StoveBlockEntity(PaladinFurnitureMod.KITCHEN_COUNTER_OVEN_BLOCK_ENTITY, pos, state);
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP);
        builder.add(DOWN);
        super.appendProperties(builder);
    }
    public static boolean connectsVertical(Block block) {
        return block instanceof KitchenCounter || block instanceof KitchenCounterOven;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        boolean up = connectsVertical(world.getBlockState(blockPos.up()).getBlock());
        boolean down = connectsVertical(world.getBlockState(blockPos.down()).getBlock());
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(UP, up).with(DOWN, down);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getAxis().isVertical()) {
            boolean up = connectsVertical(world.getBlockState(pos.up()).getBlock());
            boolean down = connectsVertical(world.getBlockState(pos.down()).getBlock());
            return state.with(UP, up).with(DOWN, down);
        }
        else{
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(world, type, PaladinFurnitureMod.KITCHEN_COUNTER_OVEN_BLOCK_ENTITY);
    }

    protected static final VoxelShape COUNTER_OVEN = VoxelShapes.union(createCuboidShape(0, 1, 0, 16, 14, 14),createCuboidShape(0, 0, 0, 16, 1, 12),createCuboidShape(0, 14, 0, 16, 16, 16),createCuboidShape(1.8, 11.2, 14.54, 14.3, 11.8, 15.14),createCuboidShape(2.5, 11.2, 13.07, 3.1, 11.8, 14.57),createCuboidShape(12.6, 11.2, 13.07, 13.2, 11.8, 14.57),createCuboidShape(1.8, 1.9, 14.44, 14.3, 2.5, 15.04),createCuboidShape(2.5, 1.9, 12.47, 3.1, 2.5, 14.47),createCuboidShape(12.6, 1.9, 12.47, 13.2, 2.5, 14.47));
    protected static final VoxelShape COUNTER_OVEN_SOUTH = rotateShape(Direction.NORTH, Direction.SOUTH, COUNTER_OVEN);
    protected static final VoxelShape COUNTER_OVEN_EAST = rotateShape(Direction.NORTH, Direction.EAST, COUNTER_OVEN);
    protected static final VoxelShape COUNTER_OVEN_WEST = rotateShape(Direction.NORTH, Direction.WEST, COUNTER_OVEN);
    protected static final VoxelShape COUNTER_OVEN_BOTTOM = VoxelShapes.union(createCuboidShape(0, 1, 0,16, 16, 13),createCuboidShape(0, 0, 0,16, 1, 12),createCuboidShape(0, 1, 13,16, 15, 14),createCuboidShape(1.8, 11.2, 14.54,14.3, 11.8, 15.14),createCuboidShape(2.5, 11.2, 13.07,3.1, 11.8, 14.57),createCuboidShape(12.6, 11.2, 13.07,13.2, 11.8, 14.57),createCuboidShape(1.8, 1.9, 14.44,14.3, 2.5, 15.04),createCuboidShape(2.5, 1.9, 12.47,3.1, 2.5, 14.47),createCuboidShape(12.6, 1.9, 12.47,13.2, 2.5, 14.47));
    protected static final VoxelShape COUNTER_OVEN_MIDDLE = VoxelShapes.union(createCuboidShape(0, 0, 0,16, 16, 13),createCuboidShape(0, 1, 13,16, 15, 14),createCuboidShape(1.8, 11.2, 14.54,14.3, 11.8, 15.14),createCuboidShape(2.5, 11.2, 13.07,3.1, 11.8, 14.57),createCuboidShape(12.6, 11.2, 13.07,13.2, 11.8, 14.57),createCuboidShape(1.8, 1.9, 14.44,14.3, 2.5, 15.04),createCuboidShape(2.5, 1.9, 12.47,3.1, 2.5, 14.47),createCuboidShape(12.6, 1.9, 12.47,13.2, 2.5, 14.47));
    protected static final VoxelShape COUNTER_OVEN_BOTTOM_SOUTH = rotateShape(Direction.NORTH, Direction.SOUTH, COUNTER_OVEN_BOTTOM);
    protected static final VoxelShape COUNTER_OVEN_BOTTOM_EAST = rotateShape(Direction.NORTH, Direction.EAST, COUNTER_OVEN_BOTTOM);
    protected static final VoxelShape COUNTER_OVEN_BOTTOM_WEST = rotateShape(Direction.NORTH, Direction.WEST, COUNTER_OVEN_BOTTOM);
    protected static final VoxelShape COUNTER_OVEN_MIDDLE_SOUTH = rotateShape(Direction.NORTH, Direction.SOUTH, COUNTER_OVEN_MIDDLE);
    protected static final VoxelShape COUNTER_OVEN_MIDDLE_EAST = rotateShape(Direction.NORTH, Direction.EAST, COUNTER_OVEN_MIDDLE);
    protected static final VoxelShape COUNTER_OVEN_MIDDLE_WEST = rotateShape(Direction.NORTH, Direction.WEST, COUNTER_OVEN_MIDDLE);
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        boolean up = state.get(UP);
        boolean down = state.get(DOWN);
        if(up){
            return switch (dir){
                case NORTH -> COUNTER_OVEN_BOTTOM_SOUTH;
                case SOUTH -> COUNTER_OVEN_BOTTOM;
                case EAST -> COUNTER_OVEN_BOTTOM_WEST;
                default -> COUNTER_OVEN_BOTTOM_EAST;
            };
        }
        else if(down) {
            return switch (dir){
                case NORTH -> COUNTER_OVEN_MIDDLE_SOUTH;
                case SOUTH -> COUNTER_OVEN_MIDDLE;
                case EAST -> COUNTER_OVEN_MIDDLE_WEST;
                default -> COUNTER_OVEN_MIDDLE_EAST;
            };
        }
        else {
        return switch (dir) {
            case WEST -> COUNTER_OVEN_EAST;
            case NORTH -> COUNTER_OVEN_SOUTH;
            case SOUTH -> COUNTER_OVEN;
            default -> COUNTER_OVEN_WEST;
            };
        }
    }
}
