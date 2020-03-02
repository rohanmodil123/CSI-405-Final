package me.derpee.creelande;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class OakStreet {

    public void GenOakStreet(World world)

    {
        BlockPos position = new BlockPos((1), (1), (1)); BlockState state = Blocks.BARRIER.getDefaultState(); world.setBlockState(position, state);

        ClearThenPlaceOakStreet dhall = new ClearThenPlaceOakStreet();
        int z = -3000; int randX = 0;
        int x = -3000; int randZ = 0;
        int y = 255; BlockPos pos = new BlockPos(randX, y, randZ);
        while (z < 3000) {
            while (x < 3000) {
                // get random block within the 1000 by 1000 area
                randX = (int) (Math.random() * ((1000) + 1) + x);
                randZ = (int) (Math.random() * ((1000) + 1) + z);
                double chance = Math.random();
                if (chance < 0.7) {
                    y = getSurfaceY(world, randX, randZ);
                    dhall.clearPlaceOakStreet(world,randX,y,randZ);
                }
                x += 1000;
            } // while x
            x = -3000;
            z += 1000;
        } // while z


    }

    public int getSurfaceY(World world, int x, int z){
        int nowY = 255;
        // Creates new BlockPos
        BlockPos blok = new BlockPos(x,nowY,z);
        // Will keep running and lowering nowY, until blok is no longer air.
        while ((world.getBlockState(blok).getBlock() == Blocks.AIR)){
            nowY --;
            blok = new BlockPos(x, nowY, z);
        }

        return nowY;
    }
}

/*
    public static void worldGen(){

        int[] x1000 = new int[60000];
        int[] z1000 = new int[60000];
        boolean[] hasStructure = new boolean[60000];
        boolean isStructure = false;
        double randDoub;
        int h = 0;

        // for all the builds, there will be ints xReference, yReference, zReference        // the x1000 and z1000 is the lowest end of the square -> (0,0) instead of (1000,1000) (for area in between)

        for(int i=-30000000;i<30000000;i+=1000){
            // This sets each as a series of numbers, 1000 apart --> 0, 1000, 2000, etc.
            x1000[h] = i;
            z1000[h] = i;

            // Each one has a 80% chance to have a structure.
            randDoub = Math.random();
            if(randDoub<0.8){isStructure = true;}
            hasStructure[h] = isStructure;

            isStructure = false;
            h++;
        }

*/

