package me.derpee.creelande;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Cash extends Item{
    // Accept registryName (creelande:registry_name)
    // Accept maxStackSize (Default: 1)
    // Accept group (Example: ItemGroup.CREATIVE)

    public Cash(String registryName, int MaxStackSize, ItemGroup group) {
        super(new Item.Properties().maxStackSize(MaxStackSize).group(group));
        this.setRegistryName("creelande", registryName);
    }

}
