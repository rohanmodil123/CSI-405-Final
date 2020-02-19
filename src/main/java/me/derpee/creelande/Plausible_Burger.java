package me.derpee.creelande;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Plausible_Burger extends Item{
    // Accept registryName (creelande:registry_name)
    // Accept maxStackSize (Default: 1)
    // Accept group (Example: ItemGroup.CREATIVE)

    public Plausible_Burger(String registryName, int MaxStackSize, ItemGroup group) {
      super(new Item.Properties().maxStackSize(MaxStackSize).group(group));
        this.setRegistryName("creelande", registryName);
    }
}