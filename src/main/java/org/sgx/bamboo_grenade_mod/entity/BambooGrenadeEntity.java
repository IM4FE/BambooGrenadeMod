package org.sgx.bamboo_grenade_mod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.HitResult;
import org.sgx.bamboo_grenade_mod.init.ModEntities;
import org.sgx.bamboo_grenade_mod.init.ModItems;

import java.util.Vector;

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
            //BlockPos.betweenClosedStream(this.blockPosition(), this.blockPosition().north(3)).forEach(pos->{
            RandomSource random = this.level().getRandom();
            for (int i = 0; i <= random.nextInt(4, 16); i++){
                int offsetX = random.nextInt(-4, 4);
                int offsetZ = random.nextInt(-4,4);

                BlockPos targetPos = this.blockPosition().offset(offsetX, 0, offsetZ);
                targetPos = targetPos.atY(this.level().getHeight(Heightmap.Types.MOTION_BLOCKING, targetPos.getX(), targetPos.getZ()));
                if (!this.level().isOutsideBuildHeight(targetPos)) {
                    this.level().setBlockAndUpdate(targetPos, Blocks.BAMBOO_SAPLING.defaultBlockState());
                }
            }
            //});
            //this.level().explode(this, this.getX(), this.getY(), this.getZ(), 100.0f, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BAMBOO_GRENADE;
    }
}
