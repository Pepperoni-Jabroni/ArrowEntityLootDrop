package pepjebs.arrowentitylootdrop.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pepjebs.arrowentitylootdrop.ArrowEntityLootDropMod;

import java.util.ArrayList;
import java.util.Random;

@Mixin(LivingEntity.class)
public class ArrowDropLivingEntityMixin {

    private LivingEntity entity = ((LivingEntity) (Object) this);
    public final ArrayList<ItemStack> arrowsInEntity = new ArrayList<>();


    @Inject(method = "applyDamage", at = @At("TAIL"))
    public void addArrowsOnDamage(DamageSource source, float amount, CallbackInfo ci) {
        var srcEntity = source.getSource();
        // Ensure arrow
        if (!(srcEntity instanceof ArrowEntity || srcEntity instanceof SpectralArrowEntity)
                || ((PersistentProjectileEntity) srcEntity).pickupType
                    == PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY) {
            return;
        }
        // Don't add if it "breaks"
        if (ArrowEntityLootDropMod.CONFIG != null
                && (new Random()).nextFloat() < ArrowEntityLootDropMod.CONFIG.arrowBreakingChance) {
            return;
        }
        // Only add default arrow if set
        if (ArrowEntityLootDropMod.CONFIG != null
                && (new Random()).nextFloat() < (1.0f - ArrowEntityLootDropMod.CONFIG.preserveNonDefaultChance)) {
            addToArrowsInEntity(new ItemStack(Items.ARROW));
            return;
        }
        // Preserve arrow type
        if (srcEntity instanceof ArrowEntity) {
            ItemStack damagingArrow = ((ArrowEntity) srcEntity).getItemStack();
            addToArrowsInEntity(damagingArrow);
        } else {
            addToArrowsInEntity(new ItemStack(Items.SPECTRAL_ARROW));
        }
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    public void dropArrowsOnKill(DamageSource damageSource, CallbackInfo ci) {
        if (!arrowsInEntity.isEmpty()) {
            for (var stack : arrowsInEntity) {
                entity.getWorld().spawnEntity(new ItemEntity(
                        entity.getWorld(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        stack
                ));
            }
        }
    }

    private void addToArrowsInEntity(ItemStack stack) {
        boolean didCompress = false;
        for (var i : arrowsInEntity) {
            if (i.isOf(stack.getItem()) && i.getCount() + stack.getCount() <= i.getMaxCount()) {
                i.increment(stack.getCount());
                didCompress = true;
                break;
            }
        }
        if (!didCompress) {
            arrowsInEntity.add(stack);
        }
    }
}
