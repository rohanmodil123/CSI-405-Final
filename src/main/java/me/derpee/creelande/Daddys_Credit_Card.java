package me.derpee.creelande;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

// Initialize a new Item class.
public class Daddys_Credit_Card extends Item {
    // Accept registryName (creelande:registry_name)
    // Accept maxStackSize (Default: 1)
    // Accept group (Example: ItemGroup.CREATIVE)
    public Daddys_Credit_Card(String registryName, int MaxStackSize, ItemGroup group) {
        super(new Item.Properties().maxStackSize(MaxStackSize).group(group));
        this.setRegistryName("creelande", registryName);
    }
}
