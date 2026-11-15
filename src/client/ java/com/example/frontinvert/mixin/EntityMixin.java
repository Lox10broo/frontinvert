import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

Entity.class
public class EntityMixin {
    @ModifyVariable(method = "turn", at = "HEAD", argsOnly = true, ordinal = 0)
    private double frontInvertYaw(double yRot) {
        if ((Object) this instanceof LocalPlayer
                && Minecraft.getInstance().options.getCameraType().isMirrored()) {
            return -yRot;
        }
        return yRot;
    }
}
