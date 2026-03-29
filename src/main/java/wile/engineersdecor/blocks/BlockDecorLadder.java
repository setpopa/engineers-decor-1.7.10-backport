package wile.engineersdecor.blocks;

import net.minecraft.block.BlockLadder;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import wile.engineersdecor.ModEngineersDecor;

import java.util.List;

public class BlockDecorLadder extends BlockLadder
{
    public BlockDecorLadder(
        final String registryName,
        final Material material,
        final float hardness,
        final float resistance,
        final String textureName
    ) {
        super();
        setBlockName(ModEngineersDecor.MODID + "." + registryName);
        setBlockTextureName(textureName.contains(":") ? textureName : (ModEngineersDecor.MODID + ":" + textureName));
        setCreativeTab(ModEngineersDecor.CREATIVE_TAB_ENGINEERSDECOR);
        setHardness(hardness);
        setResistance(resistance);
        setStepSound((material == Material.wood) ? soundTypeWood : soundTypeMetal);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void getSubBlocks(final Item item, final CreativeTabs tab, final List list)
    {
        list.add(new ItemStack(item, 1, 0));
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return ModEngineersDecor.proxy.getDecorRenderId();
    }

    @Override
    public void setBlockBoundsBasedOnState(final IBlockAccess world, final int x, final int y, final int z)
    {
        final float thickness = 0.125F;
        final int meta = world.getBlockMetadata(x, y, z);

        if (meta == 2) {
            setBlockBounds(0.0F, 0.0F, 1.0F - thickness, 1.0F, 1.0F, 1.0F);
        } else if (meta == 3) {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, thickness);
        } else if (meta == 4) {
            setBlockBounds(1.0F - thickness, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else if (meta == 5) {
            setBlockBounds(0.0F, 0.0F, 0.0F, thickness, 1.0F, 1.0F);
        } else {
            setBlockBounds(0.0F, 0.0F, 1.0F - thickness, 1.0F, 1.0F, 1.0F);
        }
    }
}
