package org.sgx.bamboo_grenade_mod;

import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import org.sgx.bamboo_grenade_mod.init.ModEntities;
import org.sgx.bamboo_grenade_mod.init.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BambooGrenadeMod implements ModInitializer {
	public static final String MOD_ID = "bamboo_grenade_mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItems.register();
		ModEntities.register();
		LOGGER.info("Hello Fabric world!");
	}
}