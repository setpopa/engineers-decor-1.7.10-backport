package wile.engineersdecor.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.LinkedHashSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import wile.engineersdecor.ModEngineersDecor;

public class BlockDecorFull extends Block
{
    private final String[] textureNames;
    private final int[] creativeMetas;
    private IIcon[] icons;

    public BlockDecorFull(final String registryName, final Material material, final String[] textureNames)
    {
        this(registryName, material, textureNames, new int[]{0});
    }

    public BlockDecorFull(
        final String registryName,
        final Material material,
        final String[] textureNames,
        final int[] creativeMetas
    )
    {
        super(material);
        this.textureNames = normalizeTextures(textureNames);
        this.creativeMetas = (creativeMetas == null || creativeMetas.length == 0) ? new int[]{0} : creativeMetas.clone();
        this.icons = new IIcon[textureNames.length];
        setBlockName(ModEngineersDecor.MODID + "." + registryName);
        setBlockTextureName(this.textureNames[0]);
        setCreativeTab(ModEngineersDecor.CREATIVE_TAB_ENGINEERSDECOR);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister iconRegister)
    {
        for (int i = 0; i < textureNames.length; ++i) {
            icons[i] = iconRegister.registerIcon(textureNames[i]);
        }
        this.blockIcon = icons[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta)
    {
        return icons[clampMeta(meta)];
    }

    @Override
    public int damageDropped(final int meta)
    {
        return clampMeta(meta);
    }

    @Override
    public int getDamageValue(final net.minecraft.world.World world, final int x, final int y, final int z)
    {
        return clampMeta(world.getBlockMetadata(x, y, z));
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void getSubBlocks(final Item item, final CreativeTabs tab, final List list)
    {
        final LinkedHashSet<Integer> exposed = new LinkedHashSet<Integer>();
        for (int meta : creativeMetas) {
            exposed.add(clampMeta(meta));
        }
        for (Integer meta : exposed) {
            list.add(new ItemStack(item, 1, meta.intValue()));
        }
    }

    private int clampMeta(final int meta)
    {
        return MathHelper.clamp_int(meta, 0, textureNames.length - 1);
    }

    private static String[] normalizeTextures(final String[] rawNames)
    {
        final String[] normalized = new String[rawNames.length];
        for (int i = 0; i < rawNames.length; ++i) {
            final String name = rawNames[i];
            normalized[i] = name.contains(":") ? name : (ModEngineersDecor.MODID + ":" + name);
        }
        return normalized;
    }
}
