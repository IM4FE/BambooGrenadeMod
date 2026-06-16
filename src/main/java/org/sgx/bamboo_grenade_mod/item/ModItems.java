package org.sgx.bamboo_grenade_mod.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import org.sgx.bamboo_grenade_mod.BambooGrenadeMod;

import java.util.function.Function;

public class ModItems {

    public static final Item BAMBOO_GRENADE = registerItem("bamboo_grenade", Item::new);

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(BambooGrenadeMod.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BambooGrenadeMod.MOD_ID, name)))));
    }

    public static void register(){
        BambooGrenadeMod.LOGGER.debug("Registering items for:" + BambooGrenadeMod.MOD_ID);
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> output.accept(BAMBOO_GRENADE));
    }
}
