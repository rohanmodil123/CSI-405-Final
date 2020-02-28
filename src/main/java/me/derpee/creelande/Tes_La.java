package me.derpee.creelande;

import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MinecartItem;

public class Tes_La extends MinecartItem{
    public Tes_La(AbstractMinecartEntity.Type minecartType, Properties itemProperties) {
        super(minecartType, itemProperties);
        this.setRegistryName("creelande", "tes_la_car");
    }





}
