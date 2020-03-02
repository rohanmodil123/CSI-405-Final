package me.derpee.creelande;

import net.minecraft.world.World;

/*
 Intellij might show this file as erroring. It is not. It runs perfectly fine.
 Intellij does this because it has a lack of brain function and is dumb.
 The actual reason for this is because ClearSpaceForOakStreet is over
 the maximum 2.56Kb for Intellij to do Syntax highlighting, which breaks a couple
 things. Do not worry, it works when ran.

 If you want to get rid of the annoying red underlines, click on the little man
 in the bottom right hand corner and drag the slider all the way to "none"

 That should only affect this file, but test it by putting gibberish in another file to
 see if it underlines. If so, you're good. If not, your Intellij is different from mine and you
 are going to have to live with the red underlines and switch the slider back to "Inspections"

 */

public class ClearThenPlaceOakStreet {
    public void clearPlaceOakStreet(World world, int xReference, int yReference, int zReference){

        ClearSpaceForOakStreet clear = new ClearSpaceForOakStreet();
        clear.clearSpaceForOakStreet(world,xReference,yReference,zReference);
        OakStreetSetBlock oak = new OakStreetSetBlock();
        oak.placeOakStreetDiningHall(world,xReference,yReference,zReference);


    }
}
