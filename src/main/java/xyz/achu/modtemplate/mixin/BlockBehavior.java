package xyz.achu.modtemplate.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EnderChestBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(BlockBehaviour.class)
public abstract class BlockBehavior {
    @Shadow protected abstract Block asBlock();

    @Shadow public abstract Item asItem();

    @Inject(method = "attack", at = @At("HEAD"))
    public void attack(BlockState blockState, Level level, BlockPos blockPos, Player player, CallbackInfo ci) {
        if (this.asBlock() instanceof EnderChestBlock) {
            ItemStack itemStack = new ItemStack(asItem());
            boolean addedChestToInventory = player.getInventory().add(itemStack);
            if (addedChestToInventory) {
                level.removeBlock(blockPos, true);
            }
        }
    }
}
