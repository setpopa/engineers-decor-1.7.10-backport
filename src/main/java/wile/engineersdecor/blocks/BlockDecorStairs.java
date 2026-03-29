package wile.engineersdecor.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import wile.engineersdecor.ModEngineersDecor;

public class BlockDecorStairs extends BlockStairs
{
    public BlockDecorStairs(final String registryName, final Block modelBlock, final int modelMeta)
    {
        this(registryName, modelBlock, modelMeta, null);
    }

    public BlockDecorStairs(
        final String registryName,
        final Block modelBlock,
        final int modelMeta,
        final String textureName
    )
    {
        super(modelBlock, modelMeta);
        setBlockName(ModEngineersDecor.MODID + "." + registryName);
        if (textureName != null) {
            setBlockTextureName(textureName.contains(":") ? textureName : (ModEngineersDecor.MODID + ":" + textureName));
        }
        setCreativeTab(ModEngineersDecor.CREATIVE_TAB_ENGINEERSDECOR);
        setLightOpacity(0);
        useNeighborBrightness = true;
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
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }
}
