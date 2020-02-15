package me.derpee.creelande;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Credit_Card extends Item {
    public Credit_Card(String registryName, int MaxStackSize, ItemGroup group) {
        super(new Item.Properties().maxStackSize(MaxStackSize).group(group));
        this.setRegistryName("creelande", registryName);
    }
}
