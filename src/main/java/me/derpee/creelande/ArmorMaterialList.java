package me.derpee.creelande;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum ArmorMaterialList implements IArmorMaterial {
    // oh yesssss!
    SUPREME("supreme", 1, new int[]{1, 1, 1, 1}, 2, Items.LEATHER, "item.armor.equip_leather", 0.0f);

    private static final int[] max_damage_array = new int[]{13, 15, 16, 11};
    private String name;
    private int durability;
    private int[] damageReductionAmounts;
    private int enchantability;
    private Item repairItem;
    private String equipSound;
    private float toughness;

    private ArmorMaterialList(String name, int durability, int[] damageReductionAmounts, int enchantability, Item repairItem, String equipSound, float toughness) {
        this.name = name;
        this.equipSound = equipSound;
        this.durability = durability;
        this.enchantability = enchantability;
        this.repairItem = repairItem;
        this.damageReductionAmounts = damageReductionAmounts;
        this.toughness = toughness;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return max_damage_array[slotIn.getIndex()] * this.durability;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmounts[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return new SoundEvent(new ResourceLocation(equipSound));
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(this.repairItem);
    }

    @Override
    public String getName() {
        return "creelande" + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }
}
