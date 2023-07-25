package vowxky.deathmessageplus.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigSetup {

    public ForgeConfigSpec.BooleanValue displayscreen;
    public final ForgeConfigSpec.IntValue chatColor;

    public ConfigSetup(ForgeConfigSpec.Builder serverConfigBuilder) {
        displayscreen = serverConfigBuilder.comment("Para habilitar el mensaje en pantalla o en el chat || El default es true significara que mostrara el mensaje por pantalla")
                .define("DisplayScreen", true);

        chatColor = serverConfigBuilder.comment("Puedes agregar el color que mas gustes al mensaje de muerte , dejare una lista de variantes que por cierto deben estar escritas en mayusculas total para evitar errores" +
                        "\n" +
                        "1 --> ROJO\n" +
                        "2 --> AZUL\n" +
                        "3 --> VERDE\n" +
                        "4 --> AQUA\n" +
                        "5 --> MORADO")
                .defineInRange("MessageColor", 1, 1, 5);
    }
}
