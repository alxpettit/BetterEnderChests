package xyz.achu.betterenderchest.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EnderChestBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.ticket.AABBTicket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.achu.betterenderchest.ModMain;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {
    @Shadow
    public abstract Block getBlock();

    @Inject(method = "getDestroySpeed", at = @At("TAIL"), cancellable = true)
    public void getDestroySpeedMixin(BlockGetter blockGetter, BlockPos blockpos, CallbackInfoReturnable<Float> cir) {
        ModMain.LOGGER.info("Found: " + getBlock().getRegistryName());
        if (getBlock() instanceof EnderChestBlock) {
            ModMain.LOGGER.info("IS an ender chest.");
            ModMain.LOGGER.info("Overriding destroy speed getter.");
            cir.setReturnValue(-1.0F);
        }
    }
}
