package xyz.achu.betterenderchest.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import xyz.achu.betterenderchest.ModMain;

import java.lang.reflect.Field;


@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviorMixin {
    SoundSource source = SoundSource.NEUTRAL;
    @Shadow protected abstract Block asBlock();

    @Shadow public abstract Item asItem();

    @Inject(method = "attack", at = @At("HEAD"))
    public void attack(BlockState blockState, Level level, BlockPos blockPos, Player player, CallbackInfo ci) {
        Block block = this.asBlock();
        if (block instanceof EnderChestBlock) {
            ItemStack itemStack = new ItemStack(asItem());
            boolean addedChestToInventory = player.getInventory().add(itemStack);
            if (addedChestToInventory) {
                level.removeBlock(blockPos, true);
                level.playSound((Player)null, blockPos, SoundEvents.ITEM_FRAME_ADD_ITEM, source, 1.0F, 1.0F);
            } else {
                level.playSound((Player)null, blockPos,  SoundEvents.PLAYER_ATTACK_NODAMAGE, source, 1.0F, 1.0F);
            }
        }
    }
}
