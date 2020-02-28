package me.derpee.creelande;


import java.util.Random;

public class OakStreet {


    public static void worldGen(){

        int[] x1000 = new int[60000];
        int[] z1000 = new int[60000];
        boolean[] hasStructure = new boolean[60000];
        boolean isStructure = false;
        double randDoub;
        int h = 0;

        // for all the builds, there will be ints xReference, yReference, zReference        // the x1000 and z1000 is the lowest end of the square -> (0,0) instead of (1000,1000) (for area in between)
        for(int i=-30000000;i<30000000;i+=1000){
            x1000[h] = i;
            z1000[h] = i;
            randDoub = Math.random();
            if(randDoub<0.8){isStructure = true;}
            hasStructure[h] = isStructure;
            isStructure = false;
            h++;
        }

    }
}
