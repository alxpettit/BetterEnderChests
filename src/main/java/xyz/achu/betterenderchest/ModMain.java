package xyz.achu.betterenderchest;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

@Mod("betterenderchest")
public class ModMain {

    public static final Logger LOGGER = LogManager.getLogger();
    final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

//    static {
//        MY_ENDER_CHEST = Registry.register(Registry.BLOCK, "ender_chest", new EnderChestBlock();
//    }

    public ModMain() {

        // This is our mod's event bus, used for things like registry or lifecycle events
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        // This listener is fired on both client and server during setup.
        MOD_BUS.addListener(this::commonSetup);
        // This listener is only fired during client setup, so we can use client-side methods here.
        MOD_BUS.addListener(this::clientSetup);

        // Most other events are fired on Forge's bus.
        // If we want to use annotations to register event listeners,
        // we need to register our object like this!
        MinecraftForge.EVENT_BUS.register(this);

        // For more information on how to deal with events in Forge,
        // like automatically subscribing an entire class to an event bus
        // or using static methods to listen to events,
        // feel free to check out the Forge wiki!


        registerCommonEvents(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
       // LOGGER.info("Hello from common setup! This is *after* registries are done, so we can do this:");
       // LOGGER.info("Look, I found a {}!", Items.DIAMOND.getRegistryName());
//
//        try {
////            BlockBehaviour.Properties newEnderChestProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(-1, 600.0F).lightLevel((arg) -> {
////                return 7;
////            });
////            blockProperties = BlockBehaviour.class.getDeclaredField("properties");
////            blockProperties.setAccessible(true);
////            blockProperties.set(BlockBehaviour.Properties.class, newEnderChestProperties);
//            blockProperties = BlockBehaviour.Properties.class.getDeclaredField("destroyTime");
//            blockProperties.setAccessible(true);
//            blockProperties.setInt(ENDER_CHEST.properties, -1);
//
//        } catch (Exception e) {
//            ModMain.LOGGER.error("Failure while attempting to set harvest level?");
//            e.printStackTrace();
//        }
       // Registry.BLOCK, (String)string, arg);

    }

    private void clientSetup(final FMLClientSetupEvent event) {
       // LOGGER.info("Hey, we're on Minecraft version {}!", Minecraft.getInstance().getLaunchedVersion());
    }

    @SubscribeEvent
    public void kaboom(ExplosionEvent.Detonate event) {
       // LOGGER.info("Kaboom! Something just blew up in {}!", event.getWorld());
    }

    public void registerCommonEvents(IEventBus eventBus) {
        eventBus.register(StartupCommon.class);
    }
}
