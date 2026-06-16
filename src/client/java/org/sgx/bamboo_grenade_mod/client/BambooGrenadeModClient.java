package org.sgx.bamboo_grenade_mod.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import org.sgx.bamboo_grenade_mod.init.ModEntities;

public class BambooGrenadeModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRenderers.register(ModEntities.BAMBOO_GRENADE, ThrownItemRenderer::new);
	}
}
