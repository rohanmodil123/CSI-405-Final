package me.derpee.creelande;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Ayre_Armor extends ArmorItem {

    // Main constructor, returns a different type of ArmorItem based on EquipmentSlotType.
    // Material determines the textures and enum to look for.
    public Ayre_Armor(IArmorMaterial material, EquipmentSlotType equipmentSlot, String registryName, ItemGroup group) {
        // Set registryName and make ArmorItem.
        super(material, equipmentSlot, new Item.Properties().group(group));
        this.setRegistryName("creelande", registryName);
    }
}
