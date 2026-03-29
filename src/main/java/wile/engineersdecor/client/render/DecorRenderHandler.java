package wile.engineersdecor.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import wile.engineersdecor.ModContent;
import wile.engineersdecor.blocks.BlockDecorLadder;
import wile.engineersdecor.blocks.BlockDecorWall;
import wile.engineersdecor.proxy.ClientProxy;

public class DecorRenderHandler implements ISimpleBlockRenderingHandler
{
    private static final float[][] WALL_CORE_BOXES = new float[][]{
        // clinker_brick_wall_post.json element (pixels): from [4,0,4] to [12,16,12]
        new float[]{4.0F, 0.0F, 4.0F, 12.0F, 16.0F, 12.0F}
    };

    private static final float[][] WALL_SIDE_BOXES = new float[][]{
        // clinker_brick_wall_side.json element (pixels): from [4.125,0,0] to [11.875,15.98,8]
        new float[]{4.125F, 0.0F, 0.0F, 11.875F, 15.98F, 8.0F}
    };

    private static final float[][] WALL_INVENTORY_BOXES = new float[][]{
        // clinker_brick_wall_inventory.json elements (pixels)
        new float[]{4.5F, 0.0F, 0.0F, 11.5F, 12.0F, 16.0F},
        new float[]{4.5F, 12.0F, 0.0F, 11.5F, 16.0F, 16.0F}
    };

    private static final float[][] METAL_RUNG_LADDER_BOXES = new float[][]{
        // metal_rung_ladder_model.json elements (pixels)
        new float[]{3.0F, 2.0F, 14.0F, 13.0F, 3.0F, 15.0F},
        new float[]{3.0F, 6.0F, 14.0F, 13.0F, 7.0F, 15.0F},
        new float[]{3.0F, 14.0F, 14.0F, 13.0F, 15.0F, 15.0F},
        new float[]{3.0F, 10.0F, 14.0F, 13.0F, 11.0F, 15.0F},
        new float[]{12.0F, 2.0F, 15.0F, 13.25F, 3.0F, 16.0F},
        new float[]{2.75F, 2.0F, 15.0F, 4.0F, 3.0F, 16.0F},
        new float[]{2.75F, 10.0F, 15.0F, 4.0F, 11.0F, 16.0F},
        new float[]{12.0F, 10.0F, 15.0F, 13.25F, 11.0F, 16.0F},
        new float[]{2.75F, 6.0F, 15.0F, 4.0F, 7.0F, 16.0F},
        new float[]{12.0F, 6.0F, 15.0F, 13.25F, 7.0F, 16.0F},
        new float[]{2.75F, 14.0F, 15.0F, 4.0F, 15.0F, 16.0F},
        new float[]{12.0F, 14.0F, 15.0F, 13.25F, 15.0F, 16.0F}
    };

    private static final float[][] METAL_RUNG_STEPS_BOXES = new float[][]{
        // metal_rung_steps_model.json elements (pixels)
        new float[]{3.0F, 2.0F, 14.0F, 7.0F, 3.0F, 15.0F},
        new float[]{9.0F, 6.0F, 14.0F, 13.0F, 7.0F, 15.0F},
        new float[]{9.0F, 14.0F, 14.0F, 13.0F, 15.0F, 15.0F},
        new float[]{3.0F, 10.0F, 14.0F, 7.0F, 11.0F, 15.0F},
        new float[]{6.0F, 2.0F, 15.0F, 7.25F, 3.0F, 16.0F},
        new float[]{2.75F, 2.0F, 15.0F, 4.0F, 3.0F, 16.0F},
        new float[]{2.75F, 10.0F, 15.0F, 4.0F, 11.0F, 16.0F},
        new float[]{6.0F, 10.0F, 15.0F, 7.25F, 11.0F, 16.0F},
        new float[]{8.75F, 6.0F, 15.0F, 10.0F, 7.0F, 16.0F},
        new float[]{12.0F, 6.0F, 15.0F, 13.25F, 7.0F, 16.0F},
        new float[]{8.75F, 14.0F, 15.0F, 10.0F, 15.0F, 16.0F},
        new float[]{12.0F, 14.0F, 15.0F, 13.25F, 15.0F, 16.0F}
    };

    private static final float[][] TREATED_WOOD_LADDER_BOXES = new float[][]{
        // treated_wood_ladder_model.json elements (pixels)
        new float[]{4.0F, 2.0F, 14.625F, 12.0F, 3.0F, 15.625F},
        new float[]{4.0F, 6.0F, 14.625F, 12.0F, 7.0F, 15.625F},
        new float[]{4.0F, 14.0F, 14.625F, 12.0F, 15.0F, 15.625F},
        new float[]{4.0F, 10.0F, 14.625F, 12.0F, 11.0F, 15.625F},
        new float[]{2.75F, 0.0F, 14.5F, 4.0F, 16.0F, 16.0F},
        new float[]{12.0F, 0.0F, 14.5F, 13.25F, 16.0F, 16.0F}
    };

    @Override
    public void renderInventoryBlock(final Block block, final int metadata, final int modelId, final RenderBlocks renderer)
    {
        if (modelId != getRenderId()) {
            return;
        }

        GL11.glPushMatrix();
        GL11.glTranslatef(-0.5F, -0.65F, -0.5F);
        GL11.glScalef(0.8F, 0.8F, 0.8F);
        GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);

        if (block instanceof BlockDecorWall) {
            renderInventoryBoxes(renderer, block, metadata, WALL_INVENTORY_BOXES);
        } else if (block instanceof BlockDecorLadder) {
            renderInventoryBoxes(renderer, block, metadata, getLadderBoxes(block));
        }

        renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(
        final IBlockAccess world,
        final int x,
        final int y,
        final int z,
        final Block block,
        final int modelId,
        final RenderBlocks renderer
    ) {
        if (modelId != getRenderId()) {
            return false;
        }

        if (block instanceof BlockDecorWall) {
            return renderWall(world, x, y, z, block, renderer);
        }
        if (block instanceof BlockDecorLadder) {
            return renderLadder(world, x, y, z, block, renderer);
        }
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(final int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return ClientProxy.decorRenderId;
    }

    private boolean renderWall(
        final IBlockAccess world,
        final int x,
        final int y,
        final int z,
        final Block block,
        final RenderBlocks renderer
    ) {
        final boolean north = canConnectWallTo(world, x, y, z - 1, block);
        final boolean east = canConnectWallTo(world, x + 1, y, z, block);
        final boolean south = canConnectWallTo(world, x, y, z + 1, block);
        final boolean west = canConnectWallTo(world, x - 1, y, z, block);
        final int previousUvRotateTop = renderer.uvRotateTop;
        renderer.uvRotateTop = 0;

        // Always render the wall core so isolated blocks and corners are solid.
        renderWorldPixelBoxes(renderer, block, x, y, z, WALL_CORE_BOXES, 0);

        if (north) {
            renderWorldPixelBoxes(renderer, block, x, y, z, WALL_SIDE_BOXES, 0);
        }
        if (east) {
            renderWorldPixelBoxes(renderer, block, x, y, z, WALL_SIDE_BOXES, 1);
        }
        if (south) {
            renderWorldPixelBoxes(renderer, block, x, y, z, WALL_SIDE_BOXES, 2);
        }
        if (west) {
            renderWorldPixelBoxes(renderer, block, x, y, z, WALL_SIDE_BOXES, 3);
        }

        renderer.uvRotateTop = previousUvRotateTop;
        renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        return true;
    }

    private boolean renderLadder(
        final IBlockAccess world,
        final int x,
        final int y,
        final int z,
        final Block block,
        final RenderBlocks renderer
    ) {
        final int meta = world.getBlockMetadata(x, y, z);
        final int rotation = getRotationFromLadderMeta(meta);
        renderWorldPixelBoxes(renderer, block, x, y, z, getLadderBoxes(block), rotation);
        renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        return true;
    }

    private static float[][] getLadderBoxes(final Block block)
    {
        if (block == ModContent.METAL_RUNG_STEPS) {
            return METAL_RUNG_STEPS_BOXES;
        }
        if (block == ModContent.TREATED_WOOD_LADDER) {
            return TREATED_WOOD_LADDER_BOXES;
        }
        return METAL_RUNG_LADDER_BOXES;
    }

    private static int getRotationFromLadderMeta(final int meta)
    {
        if (meta == 3) {
            return 2;
        }
        if (meta == 4) {
            return 3;
        }
        if (meta == 5) {
            return 1;
        }
        return 0;
    }

    private static void renderWorldPixelBoxes(
        final RenderBlocks renderer,
        final Block block,
        final int x,
        final int y,
        final int z,
        final float[][] pixelBoxes,
        final int rotation
    ) {
        for (float[] pixelBox : pixelBoxes) {
            final float[] box = rotatePixelBoxY(pixelBox, rotation);
            renderer.setRenderBounds(px(box[0]), px(box[1]), px(box[2]), px(box[3]), px(box[4]), px(box[5]));
            renderer.renderStandardBlock(block, x, y, z);
        }
    }

    private static void renderInventoryBoxes(
        final RenderBlocks renderer,
        final Block block,
        final int meta,
        final float[][] pixelBoxes
    ) {
        for (float[] box : pixelBoxes) {
            renderInventoryBox(
                renderer,
                block,
                meta,
                px(box[0]),
                px(box[1]),
                px(box[2]),
                px(box[3]),
                px(box[4]),
                px(box[5])
            );
        }
    }

    private static void renderInventoryBox(
        final RenderBlocks renderer,
        final Block block,
        final int meta,
        final float minX,
        final float minY,
        final float minZ,
        final float maxX,
        final float maxY,
        final float maxZ
    ) {
        renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);

        final Tessellator tessellator = Tessellator.instance;
        IIcon icon;

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        icon = block.getIcon(0, meta);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        icon = block.getIcon(1, meta);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        icon = block.getIcon(2, meta);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        icon = block.getIcon(3, meta);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        icon = block.getIcon(4, meta);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        icon = block.getIcon(5, meta);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
    }

    private static float[] rotatePixelBoxY(final float[] box, final int rotation)
    {
        switch (rotation & 3) {
            case 1:
                // +90 deg around Y
                return new float[]{16.0F - box[5], box[1], box[0], 16.0F - box[2], box[4], box[3]};
            case 2:
                // 180 deg around Y
                return new float[]{16.0F - box[3], box[1], 16.0F - box[5], 16.0F - box[0], box[4], 16.0F - box[2]};
            case 3:
                // -90 deg around Y
                return new float[]{box[2], box[1], 16.0F - box[3], box[5], box[4], 16.0F - box[0]};
            default:
                return new float[]{box[0], box[1], box[2], box[3], box[4], box[5]};
        }
    }

    private static float px(final float value)
    {
        return value / 16.0F;
    }

    private static boolean canConnectWallTo(final IBlockAccess world, final int x, final int y, final int z, final Block thisBlock)
    {
        final Block block = world.getBlock(x, y, z);
        if (block == null) {
            return false;
        }
        if (block == thisBlock || block instanceof BlockDecorWall || block instanceof BlockWall || block instanceof BlockFenceGate) {
            return true;
        }
        final Material material = block.getMaterial();
        return material.isOpaque() && block.renderAsNormalBlock() && (material != Material.gourd);
    }
}
