package wile.engineersdecor.items;

import net.minecraft.item.Item;
import wile.engineersdecor.ModEngineersDecor;

public class ItemDecorSimple extends Item
{
    public ItemDecorSimple(final String registryName)
    {
        this(registryName, ModEngineersDecor.MODID + ":" + registryName, true);
    }

    public ItemDecorSimple(final String registryName, final String texturePath)
    {
        this(registryName, texturePath, true);
    }

    public ItemDecorSimple(final String registryName, final String texturePath, final boolean assignCreativeTab)
    {
        setUnlocalizedName(ModEngineersDecor.MODID + "." + registryName);
        setTextureName(texturePath);
        if (assignCreativeTab) {
            setCreativeTab(ModEngineersDecor.CREATIVE_TAB_ENGINEERSDECOR);
        }
    }
}
