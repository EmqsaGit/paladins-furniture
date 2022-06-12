package com.unlikepaladin.pfm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class ArmChair extends BasicChair {

    public ArmChair(Settings settings) {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }
    protected static final VoxelShape FACE_WEST = VoxelShapes.union(createCuboidShape(0.3, 9.46, 3,3.6, 25.46, 13), createCuboidShape(1.42, 7.46, 2.5, 5.72, 25.16, 13.3), createCuboidShape(0.3, 9.46, 13, 6.6, 25.46, 16), createCuboidShape(0.3, 9.46, 0, 6.6, 25.46, 3), createCuboidShape(6.6, 9.46, 13, 17.3, 13.71, 16) ,  createCuboidShape(6.6, 9.46, 0, 17.3, 13.71, 3), createCuboidShape(2.3, 8, 1, 17, 10.5, 14.6), createCuboidShape(0.3, 2, 0, 17.3, 9.5, 16), createCuboidShape(12, 0, 12, 14.5, 3, 14.5), createCuboidShape(12, 0, 1, 14.5, 3, 3.5), createCuboidShape(1, 0, 12, 3.5, 3, 14.5), createCuboidShape(1, 0, 1, 3.5, 3, 3.5));
    protected static final VoxelShape FACE_SOUTH = VoxelShapes.union(createCuboidShape(3, 9.46, 12.4,13, 25.46, 15.7), createCuboidShape(2.5, 7.46, 10.28, 13.3, 25.16, 14.58), createCuboidShape(13, 9.46, 9.4, 16, 25.46, 15.7), createCuboidShape(0, 9.46, 9.4, 3, 25.46, 15.7), createCuboidShape(13, 9.46, -1.3, 16, 13.71, 9.4), createCuboidShape(0, 9.46, -1.3, 3, 13.71, 9.4), createCuboidShape(1, 8, -1, 14.6, 10.5, 13.7), createCuboidShape(0, 2, -1.3,16, 9.5, 15.7), createCuboidShape(12, 0, 1.5, 14.5, 3, 4), createCuboidShape(1, 0, 1.5, 3.5, 3, 4), createCuboidShape(12, 0, 12.5, 14.5, 3, 15), createCuboidShape(1, 0, 12.5, 3.5, 3, 15) );
    protected static final VoxelShape FACE_NORTH = VoxelShapes.union(createCuboidShape(3, 9.46, 0.3,13, 25.46, 3.6), createCuboidShape(2.7, 7.46, 1.42, 13.5, 25.16, 5.72), createCuboidShape(0, 9.46, 0.3, 3, 25.46, 6.6), createCuboidShape(13, 9.46, 0.3, 16, 25.46, 6.6), createCuboidShape(0, 9.46, 6.6, 3, 13.71, 17.3), createCuboidShape(13, 9.46, 6.6, 16, 13.71, 17.3), createCuboidShape(1.4, 8, 2.3, 15, 10.5, 17), createCuboidShape(0, 2, 0.3, 16, 9.5, 17.3), createCuboidShape(1.5, 0, 12, 4, 3, 14.5), createCuboidShape(12.5, 0, 12, 15, 3, 14.5), createCuboidShape(1.5, 0, 1, 4, 3, 3.5), createCuboidShape(12.5, 0, 1, 15, 3, 3.5));
    protected static final VoxelShape FACE_EAST = VoxelShapes.union(createCuboidShape(12.4, 9.46, 3 ,15.7, 25.46, 13),createCuboidShape(10.28, 7.46, 2.7,14.58, 25.16, 13.5), createCuboidShape(9.4, 9.46, 0, 15.7, 25.46, 3), createCuboidShape(9.4, 9.46, 13, 15.7, 25.46, 16), createCuboidShape(-1.3, 9.46, 0, 9.4, 13.71, 3), createCuboidShape(-1.3, 9.46, 13, 9.4, 13.71, 16), createCuboidShape(-1, 8, 1.4, 13.7, 10.5, 15), createCuboidShape(-1.3, 2, 0, 15.7, 9.5, 16), createCuboidShape(1.5, 0, 1.5, 4, 3, 4), createCuboidShape(1.5, 0, 12.5, 4, 3, 15), createCuboidShape(12.5, 0, 1.5, 15, 3, 4), createCuboidShape(12.5, 0, 12.5, 15, 3, 15) );

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        switch(dir) {
            case WEST:
                return FACE_WEST;
            case NORTH:
                return FACE_NORTH;
            case SOUTH:
                return FACE_SOUTH;
            case EAST:
            default:
                return FACE_EAST;


        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }


}



