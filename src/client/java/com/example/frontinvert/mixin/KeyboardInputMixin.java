package com.example.frontinvert.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.phys.Vec2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

KeyboardInput.class
public class KeyboardInputMixin {
    method = "tick", at = [id4526022|@At("TAIL"])
    private void frontInvert(CallbackInfo ci) {
        if (!Minecraft.getInstance().options.getCameraType().isMirrored()) return;
        ClientInput self = (ClientInput) (Object) this;
        Input k = self.keyPresses;
        self.keyPresses = new Input(k.forward(), k.backward(), k.right(), k.left(),
                k.jump(), k.shift(), k.sprint());
        Vec2 m = self.moveVector;
        self.moveVector = new Vec2(-m.x, m.y);
    }
}
Имя: src/client/java/com/example/frontinvert/mixin/EntityMixin.java
package com.example.frontinvert.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

Entity.class
public class EntityMixin {
    @ModifyVariable(method = "turn", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private double frontInvertYaw(double yRot) {
        if ((Object) this instanceof LocalPlayer
                && Minecraft.getInstance().options.getCameraType().isMirrored()) {
            return -yRot;
        }
        return yRot;
    }
}
