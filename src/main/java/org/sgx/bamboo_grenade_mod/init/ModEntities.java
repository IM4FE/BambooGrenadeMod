package org.sgx.bamboo_grenade_mod.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.sgx.bamboo_grenade_mod.BambooGrenadeMod;
import org.sgx.bamboo_grenade_mod.entity.BambooGrenadeEntity;

public class ModEntities {

    private static final Identifier BAMBOO_GRENADE_ID = Identifier.fromNamespaceAndPath(BambooGrenadeMod.MOD_ID, "bamboo_grenade");
    private static final ResourceKey<EntityType<?>> BAMBOO_GRENADE_KEY = ResourceKey.create(Registries.ENTITY_TYPE, BAMBOO_GRENADE_ID);

    public static final EntityType<BambooGrenadeEntity> BAMBOO_GRENADE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            BAMBOO_GRENADE_ID,
            FabricEntityTypeBuilder.<BambooGrenadeEntity>create(MobCategory.MISC, BambooGrenadeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                    .build(BAMBOO_GRENADE_KEY)
    );

    public static void register() {
        BambooGrenadeMod.LOGGER.debug("Registering entities for " + BambooGrenadeMod.MOD_ID);
    }
}
