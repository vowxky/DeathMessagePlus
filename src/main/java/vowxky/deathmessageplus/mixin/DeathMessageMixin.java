package vowxky.deathmessageplus.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Pueden usar el codigo agusto , al final de cuentas fue algo basico para entretenerme un rato xD
@Mixin(ServerPlayer.class)
public class DeathMessageMixin {
    @Inject(method = "die", at = @At("HEAD"), cancellable = true)
    private void cancelDeathMessage(DamageSource damageSource , CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer) (Object) this;
        boolean showDeathMessages = player.getServer().getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES);

        if (showDeathMessages) {
            Component deathMessage = damageSource.getLocalizedDeathMessage(player);
            String deathMessageString = deathMessage.getString();
            Component deathMessageFinal = Component.nullToEmpty(ChatFormatting.RED + deathMessageString);
            player.displayClientMessage(deathMessageFinal, true);
        }

        ci.cancel();
    }
}
