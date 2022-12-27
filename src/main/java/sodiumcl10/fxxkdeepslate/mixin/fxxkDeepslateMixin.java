package sodiumcl10.fxxkdeepslate.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.chunk.ChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkSection.class)
public abstract class fxxkDeepslateMixin {
    BlockState deepslate = Blocks.DEEPSLATE.getDefaultState();
    BlockState stone = Blocks.STONE.getDefaultState();
    BlockState tuff = Blocks.TUFF.getDefaultState();
    BlockState andesite = Blocks.ANDESITE.getDefaultState();

    @Shadow
    public abstract BlockState setBlockState(int x, int y, int z, BlockState state, boolean flag);

    @Inject(method = "setBlockState(IIILnet/minecraft/block/BlockState;Z)Lnet/minecraft/block/BlockState;", at = @At("HEAD"), cancellable = true)
    private void replaceState(int x, int y, int z, BlockState state, boolean flag, CallbackInfoReturnable<BlockState> cir) {
        if (flag) return;
        if (state == deepslate) cir.setReturnValue(stone);
        if (state == tuff) cir.setReturnValue(andesite);
    }
}
