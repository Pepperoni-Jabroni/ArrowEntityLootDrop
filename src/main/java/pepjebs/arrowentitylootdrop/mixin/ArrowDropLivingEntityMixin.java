package pepjebs.arrowentitylootdrop.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(LivingEntity.class)
public class ArrowDropLivingEntityMixin {

    private LivingEntity entity = ((LivingEntity) (Object) this);
    public final ArrayList<ItemStack> arrowsInEntity = new ArrayList<>();


    @Inject(method = "applyDamage", at = @At("TAIL"))
    public void addArrowsOnDamage(DamageSource source, float amount, CallbackInfo ci) {
        if (source.getSource() instanceof ArrowEntity) {
            ItemStack damagingArrow = ((InvokerArrowEntityMixin) source.getSource()).getArrowItemStack();
            arrowsInEntity.add(damagingArrow);
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
}
