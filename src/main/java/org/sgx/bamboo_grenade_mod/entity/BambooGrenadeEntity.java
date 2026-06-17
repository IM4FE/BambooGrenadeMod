package org.sgx.bamboo_grenade_mod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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
            ServerLevel serverLevel = (ServerLevel) this.level();
            RandomSource random = this.level().getRandom();
            for (int i = 0; i <= random.nextInt(4, 16); i++){
                int offsetX = random.nextInt(-4, 4);
                int offsetZ = random.nextInt(-4,4);

                BlockPos targetPos = this.blockPosition().offset(offsetX, 0, offsetZ);
                targetPos = targetPos.atY(this.level().getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, targetPos.getX(), targetPos.getZ()));

                BlockState saplingState = Blocks.BAMBOO_SAPLING.defaultBlockState();
                if (saplingState.canSurvive(this.level(), targetPos)) {
                    this.level().setBlockAndUpdate(targetPos, saplingState);
                    serverLevel.sendParticles(
                            ParticleTypes.HAPPY_VILLAGER,
                            targetPos.getX() + 0.5,
                            targetPos.getY() + 1.0,
                            targetPos.getZ() + 0.5,
                            5,    // count
                            0.3,  // spread X
                            0.3,  // spread Y
                            0.3,  // spread Z
                            0.0   // speed
                    );
                }
            }
            BlockPos currentBlockPos = this.blockPosition();

            this.level().playSound(null,
                    currentBlockPos.getX(), currentBlockPos.getY(), currentBlockPos.getZ(),
                    SoundEvents.GENERIC_EXPLODE,
                    SoundSource.BLOCKS,
                    0.5f,
                    1.5f
            ); //sfx
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BAMBOO_GRENADE;
    }
}
