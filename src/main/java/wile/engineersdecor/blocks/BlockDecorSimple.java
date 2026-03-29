package wile.engineersdecor.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import wile.engineersdecor.ModEngineersDecor;

public class BlockDecorSimple extends Block
{
    private final String texturePath;
    private IIcon icon;

    public BlockDecorSimple(final String registryName, final Material material)
    {
        this(registryName, material, registryName);
    }

    public BlockDecorSimple(final String registryName, final Material material, final String textureName)
    {
        super(material);
        this.texturePath = textureName.contains(":") ? textureName : (ModEngineersDecor.MODID + ":" + textureName);
        setBlockName(ModEngineersDecor.MODID + "." + registryName);
        setBlockTextureName(texturePath);
        setCreativeTab(ModEngineersDecor.CREATIVE_TAB_ENGINEERSDECOR);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister iconRegister)
    {
        this.icon = iconRegister.registerIcon(texturePath);
        this.blockIcon = icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta)
    {
        return icon;
    }
}
