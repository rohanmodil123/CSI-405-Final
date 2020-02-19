package me.derpee.creelande;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Plausible_Burger extends Item{
    // Accept registryName (creelande:registry_name)
    // Accept maxStackSize (Default: 1)
    // Accept group (Example: ItemGroup.CREATIVE)

    public Plausible_Burger(String registryName, int MaxStackSize, ItemGroup group) {
        // Associates this Plausible_Burger with the Food Property PLAUSIBLE_BURGER
      super(new Item.Properties().maxStackSize(MaxStackSize).group(group).food(ModFoods.PLAUSIBLE_BURGER));

        this.setRegistryName("creelande", registryName);
    }
}