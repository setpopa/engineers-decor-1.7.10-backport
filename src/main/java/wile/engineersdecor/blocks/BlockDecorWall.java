package wile.engineersdecor.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import wile.engineersdecor.ModEngineersDecor;

import java.util.List;

public class BlockDecorWall extends BlockWall
{
    private final String sideTexturePath;
    private final String topTexturePath;
    private IIcon sideIcon;
    private IIcon topIcon;

    public BlockDecorWall(
        final String registryName,
        final Block modelBlock,
        final float hardness,
        final float resistance,
        final String textureName
    )
    {
        this(registryName, modelBlock, hardness, resistance, textureName, null);
    }

    public BlockDecorWall(
        final String registryName,
        final Block modelBlock,
        final float hardness,
        final float resistance,
        final String sideTextureName,
        final String topTextureName
    )
    {
        super(modelBlock);
        sideTexturePath = sideTextureName.contains(":")
            ? sideTextureName
            : (ModEngineersDecor.MODID + ":" + sideTextureName);
        topTexturePath = (topTextureName == null || topTextureName.isEmpty())
            ? sideTexturePath
            : (topTextureName.contains(":") ? topTextureName : (ModEngineersDecor.MODID + ":" + topTextureName));
        setBlockName(ModEngineersDecor.MODID + "." + registryName);
        setBlockTextureName(sideTexturePath);
        setCreativeTab(ModEngineersDecor.CREATIVE_TAB_ENGINEERSDECOR);
        setHardness(hardness);
        setResistance(resistance);
        setStepSound(modelBlock.stepSound);
        setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister iconRegister)
    {
        this.sideIcon = iconRegister.registerIcon(sideTexturePath);
        this.topIcon = iconRegister.registerIcon(topTexturePath);
        this.blockIcon = sideIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta)
    {
        return (side == 0 || side == 1) ? topIcon : sideIcon;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void getSubBlocks(final Item item, final CreativeTabs tab, final List list)
    {
        list.add(new ItemStack(item, 1, 0));
    }

    @Override
    public int damageDropped(final int meta)
    {
        return 0;
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
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void addCollisionBoxesToList(
        final World world,
        final int x,
        final int y,
        final int z,
        final AxisAlignedBB mask,
        final List list,
        final Entity entity
    ) {
        final boolean north = canConnectToNeighbor(world, x, y, z - 1);
        final boolean south = canConnectToNeighbor(world, x, y, z + 1);
        final boolean west = canConnectToNeighbor(world, x - 1, y, z);
        final boolean east = canConnectToNeighbor(world, x + 1, y, z);

        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);

        if (north) {
            this.setBlockBounds(0.25F, 0.0F, 0.0F, 0.75F, 1.0F, 0.25F);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }
        if (south) {
            this.setBlockBounds(0.25F, 0.0F, 0.75F, 0.75F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }
        if (west) {
            this.setBlockBounds(0.0F, 0.0F, 0.25F, 0.25F, 1.0F, 0.75F);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }
        if (east) {
            this.setBlockBounds(0.75F, 0.0F, 0.25F, 1.0F, 1.0F, 0.75F);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }

        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
    }

    private boolean canConnectToNeighbor(final IBlockAccess world, final int x, final int y, final int z)
    {
        final Block block = world.getBlock(x, y, z);
        if (block == null) {
            return false;
        }
        if (block == this || block instanceof BlockDecorWall || block instanceof BlockWall) {
            return true;
        }
        final Material material = block.getMaterial();
        return material.isOpaque() && block.renderAsNormalBlock() && material != Material.gourd;
    }
}
