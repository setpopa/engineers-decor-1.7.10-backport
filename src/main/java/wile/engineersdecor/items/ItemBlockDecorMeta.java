package wile.engineersdecor.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDecorMeta extends ItemBlock
{
    public ItemBlockDecorMeta(final Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(final int metadata)
    {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(final ItemStack stack)
    {
        return field_150939_a.getUnlocalizedName();
    }
}

