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
import vowxky.deathmessageplus.Deathmessageplus;

import java.util.Objects;

//Pueden usar el codigo agusto , al final de cuentas fue algo basico para entretenerme un rato xD
@Mixin(ServerPlayer.class)
public class DeathMessageMixin {
    @Inject(method = "die", at = @At("HEAD"), cancellable = true)
    private void cancelDeathMessage(DamageSource damageSource , CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer) (Object) this;
        boolean showDeathMessages = Objects.requireNonNull(player.getServer()).getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES);
        boolean displayScreen =  Deathmessageplus.SERVER_CONFIG.displayscreen.get();
        int colorNumber = Deathmessageplus.SERVER_CONFIG.chatColor.get();
        ChatFormatting chatFormatting = switch (colorNumber) {
            case 1 -> ChatFormatting.RED;
            case 2 -> ChatFormatting.DARK_BLUE;
            case 3 -> ChatFormatting.DARK_GREEN;
            case 4 -> ChatFormatting.DARK_AQUA;
            case 5 -> ChatFormatting.DARK_PURPLE;
            default ->
                    ChatFormatting.RED;
        };

        if (showDeathMessages) {
            Component deathMessage = damageSource.getLocalizedDeathMessage(player);
            String deathMessageString = deathMessage.getString();
            Component deathMessageFinal = Component.nullToEmpty(chatFormatting + deathMessageString);
            player.displayClientMessage(deathMessageFinal, displayScreen);
        }

        ci.cancel();
    }
}
