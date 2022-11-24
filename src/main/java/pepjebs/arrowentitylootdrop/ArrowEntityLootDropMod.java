package pepjebs.arrowentitylootdrop;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import pepjebs.arrowentitylootdrop.config.ArrowEntityLootDropConfig;

public class ArrowEntityLootDropMod implements ModInitializer {

    public static final String MOD_ID = "arrow_entity_loot_drop";
    public static ArrowEntityLootDropConfig CONFIG = null;

    @Override
    public void onInitialize() {
        if(FabricLoader.getInstance().isModLoaded("cloth-config")) {
            AutoConfig.register(ArrowEntityLootDropConfig.class, JanksonConfigSerializer::new);
            CONFIG = AutoConfig.getConfigHolder(ArrowEntityLootDropConfig.class).getConfig();
        }
    }
}
