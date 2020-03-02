package me.derpee.creelande;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class onPlayerTick {
    @SubscribeEvent
    public static void OnPlayerTick(TickEvent.PlayerTickEvent event)
    {

        ClearThenPlaceOakStreet clear = new ClearThenPlaceOakStreet();
        World world = Minecraft.getInstance().world;
        clear.clearPlaceOakStreet(world,1,100,1);

    }
}


