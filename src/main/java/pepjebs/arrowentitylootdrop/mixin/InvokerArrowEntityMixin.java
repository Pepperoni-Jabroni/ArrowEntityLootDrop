package pepjebs.arrowentitylootdrop.mixin;

import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ArrowEntity.class)
public interface InvokerArrowEntityMixin {

    @Invoker("asItemStack")
    ItemStack getArrowItemStack();
}
