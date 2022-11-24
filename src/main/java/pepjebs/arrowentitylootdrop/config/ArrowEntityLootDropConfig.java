package pepjebs.arrowentitylootdrop.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import pepjebs.arrowentitylootdrop.ArrowEntityLootDropMod;

@Config(name = ArrowEntityLootDropMod.MOD_ID)
public class ArrowEntityLootDropConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip()
    @Comment("The chance an Arrow stored in an Entity will NOT drop. Range: 0-100")
    public int arrowBreakingChance = 0;

    @ConfigEntry.Gui.Tooltip()
    @Comment("When this is true, Spectral + Tipped Arrows will drop. If false, they will drop as regular Arrows instead")
    public boolean preserveNonDefaultArrows = true;

}
