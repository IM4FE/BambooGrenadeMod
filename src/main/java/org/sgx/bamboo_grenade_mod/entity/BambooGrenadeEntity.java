package org.sgx.bamboo_grenade_mod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.sgx.bamboo_grenade_mod.init.ModEntities;
import org.sgx.bamboo_grenade_mod.init.ModItems;

public class BambooGrenadeEntity extends ThrowableItemProjectile {
    public BambooGrenadeEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public BambooGrenadeEntity(LivingEntity owner, Level level) {
        super(ModEntities.BAMBOO_GRENADE, owner, level, new ItemStack(ModItems.BAMBOO_GRENADE));
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide()) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3.0f, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BAMBOO_GRENADE;
    }
}
