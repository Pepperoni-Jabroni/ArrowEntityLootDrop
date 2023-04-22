package pepjebs.arrowentitylootdrop.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import pepjebs.arrowentitylootdrop.ArrowEntityLootDropMod;

@Config(name = ArrowEntityLootDropMod.MOD_ID)
public class ArrowEntityLootDropConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip()
    @Comment("The chance an Arrow stored in an Entity will NOT drop. Range: 0-1 (0%-100%)")
    public float arrowBreakingChance = 0.25f;

    @ConfigEntry.Gui.Tooltip()
    @Comment("The chance of the non-default arrow persisting when dropped after entity kill. Range: 0-1 (0%-100%)")
    public float preserveNonDefaultChance = 0.25f;

}
