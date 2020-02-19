package me.derpee.creelande;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file.
// This should be "creelande", if not, refactor.
@Mod("creelande")
public class Creelande
{
    // Directly reference a log4j logger, this is NOT a virus.
    private static final Logger LOGGER = LogManager.getLogger();

    public Creelande() {
        // Register the setup method for modloading.
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading.
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading.
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading.
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // Run on Preinit (Red loading bar on startup)
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Run code that can only be ran on client.
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // Dispath IMC to separate mod, use sendTo to send to a modId and run a method.
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // Receiving and process incoming IMC dispatches.
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // As server starts, run.
        // Context: A Forge server can be deployed with its own mods folder.
        // The mod will run this when it's ran from a server environment.
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    @ObjectHolder("creelande")

    public static class ModItems {
        // Set registerItems to register new Items upon call during init.
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(
                    // Register new Items.
                    new Phone("phone", 1, ItemGroup.REDSTONE),
                    new Classical_Diploma("classical_diploma", 1, ItemGroup.DECORATIONS),
                    new Plausible_Burger("plausible_burger", 1, ItemGroup.FOOD),
                    new Daddys_Credit_Card("daddys_credit_card", 1, ItemGroup.TOOLS),
                    new Superior_Armor(ArmorMaterialList.SUPERIOR, EquipmentSlotType.HEAD, "clout_goggles", ItemGroup.COMBAT),
                    new Superior_Armor(ArmorMaterialList.SUPERIOR, EquipmentSlotType.CHEST, "superior_chestplate", ItemGroup.COMBAT),
                    new Superior_Armor(ArmorMaterialList.SUPERIOR, EquipmentSlotType.LEGS, "adidas_pants", ItemGroup.COMBAT),
                    new Superior_Armor(ArmorMaterialList.SUPERIOR, EquipmentSlotType.FEET, "yeezys", ItemGroup.COMBAT)
            );
        }
    }
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            LOGGER.info("HELLO from Register Block");
        }

        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemsRegistryEvent) {
            LOGGER.info("HELLO! From Item Registration");
        }
    }
}
