package wile.engineersdecor;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import wile.engineersdecor.blocks.BlockDecorFull;
import wile.engineersdecor.blocks.BlockDecorLadder;
import wile.engineersdecor.blocks.BlockDecorStairs;
import wile.engineersdecor.blocks.BlockDecorWall;
import wile.engineersdecor.items.ItemDecorSimple;
import wile.engineersdecor.items.ItemBlockDecorMeta;

public final class ModContent
{
    private static final List<Block> REGISTERED_BLOCKS = new ArrayList<Block>();
    private static final List<Item> REGISTERED_ITEMS = new ArrayList<Item>();

    public static BlockDecorFull CLINKER_BRICK_BLOCK;
    public static BlockDecorFull CLINKER_BRICK_STAINED_BLOCK;
    public static BlockDecorFull SLAG_BRICK_BLOCK;
    public static BlockDecorFull REBAR_CONCRETE_BLOCK;
    public static BlockDecorFull REBAR_CONCRETE_TILE;
    public static BlockDecorFull GAS_CONCRETE_BLOCK;
    public static BlockDecorFull TREATED_WOOD_FLOOR;
    public static BlockDecorStairs CLINKER_BRICK_STAIRS;
    public static BlockDecorStairs SLAG_BRICK_STAIRS;
    public static BlockDecorStairs CLINKER_BRICK_STAINED_STAIRS;
    public static BlockDecorStairs REBAR_CONCRETE_STAIRS;
    public static BlockDecorStairs REBAR_CONCRETE_TILE_STAIRS;
    public static BlockDecorStairs GAS_CONCRETE_STAIRS;
    public static BlockDecorLadder METAL_RUNG_LADDER;
    public static BlockDecorLadder METAL_RUNG_STEPS;
    public static BlockDecorLadder TREATED_WOOD_LADDER;
    public static BlockDecorWall CLINKER_BRICK_WALL;
    public static BlockDecorWall SLAG_BRICK_WALL;
    public static BlockDecorWall CONCRETE_WALL;
    public static BlockDecorWall REBAR_CONCRETE_WALL;
    public static BlockDecorWall GAS_CONCRETE_WALL;
    public static ItemDecorSimple SIGN_MODLOGO;

    private ModContent() {}

    public static void preInit()
    {
        registerBlocks();
        registerItems();
        registerTileEntities();
    }

    public static void init()
    {
        // Reserved for recipe and handler registration in upcoming steps.
    }

    public static void postInit()
    {
        // Reserved for integration hooks in upcoming steps.
    }

    public static List<Block> getRegisteredBlocks()
    {
        return Collections.unmodifiableList(REGISTERED_BLOCKS);
    }

    public static List<Item> getRegisteredItems()
    {
        return Collections.unmodifiableList(REGISTERED_ITEMS);
    }

    private static void registerBlocks()
    {
        CLINKER_BRICK_BLOCK = (BlockDecorFull)registerBlock(
            new BlockDecorFull("clinker_brick_block", Material.rock, new String[]{
                "engineersdecor:clinker_brick_texture0",
                "engineersdecor:clinker_brick_texture1",
                "engineersdecor:clinker_brick_texture2",
                "engineersdecor:clinker_brick_texture3",
                "engineersdecor:clinker_brick_texture4",
                "engineersdecor:clinker_brick_texture5",
                "engineersdecor:clinker_brick_texture6",
                "engineersdecor:clinker_brick_texture7"
            }).setHardness(2.0F).setResistance(15.0F).setStepSound(Block.soundTypeStone),
            "clinker_brick_block",
            ItemBlockDecorMeta.class
        );

        CLINKER_BRICK_STAINED_BLOCK = (BlockDecorFull)registerBlock(
            new BlockDecorFull("clinker_brick_stained_block", Material.rock, new String[]{
                "engineersdecor:clinker_brick_stained_texture0",
                "engineersdecor:clinker_brick_stained_texture1",
                "engineersdecor:clinker_brick_stained_texture2",
                "engineersdecor:clinker_brick_stained_texture3",
                "engineersdecor:clinker_brick_stained_texture4",
                "engineersdecor:clinker_brick_stained_texture5",
                "engineersdecor:clinker_brick_stained_texture6",
                "engineersdecor:clinker_brick_stained_texture7"
            }).setHardness(2.0F).setResistance(15.0F).setStepSound(Block.soundTypeStone),
            "clinker_brick_stained_block",
            ItemBlockDecorMeta.class
        );

        SLAG_BRICK_BLOCK = (BlockDecorFull)registerBlock(
            new BlockDecorFull("slag_brick_block", Material.rock, new String[]{
                "engineersdecor:slag_brick_texture0",
                "engineersdecor:slag_brick_texture1",
                "engineersdecor:slag_brick_texture2",
                "engineersdecor:slag_brick_texture3",
                "engineersdecor:slag_brick_texture4",
                "engineersdecor:slag_brick_texture5",
                "engineersdecor:slag_brick_texture6",
                "engineersdecor:slag_brick_texture7"
            }).setHardness(2.0F).setResistance(15.0F).setStepSound(Block.soundTypeStone),
            "slag_brick_block",
            ItemBlockDecorMeta.class
        );

        REBAR_CONCRETE_BLOCK = (BlockDecorFull)registerBlock(
            new BlockDecorFull("rebar_concrete", Material.rock, new String[]{
                "engineersdecor:rebar_concrete_texture0",
                "engineersdecor:rebar_concrete_texture1",
                "engineersdecor:rebar_concrete_texture2",
                "engineersdecor:rebar_concrete_texture3",
                "engineersdecor:rebar_concrete_texture4",
                "engineersdecor:rebar_concrete_texture5",
                "engineersdecor:rebar_concrete_texture6",
                "engineersdecor:rebar_concrete_texture7"
            }).setHardness(5.0F).setResistance(2000.0F).setStepSound(Block.soundTypeStone),
            "rebar_concrete",
            ItemBlockDecorMeta.class
        );

        REBAR_CONCRETE_TILE = (BlockDecorFull)registerBlock(
            new BlockDecorFull("rebar_concrete_tile", Material.rock, new String[]{
                "engineersdecor:rebar_concrete_tile_texture0",
                "engineersdecor:rebar_concrete_tile_texture1",
                "engineersdecor:rebar_concrete_tile_texture2",
                "engineersdecor:rebar_concrete_tile_texture3",
                "engineersdecor:rebar_concrete_tile_texture4",
                "engineersdecor:rebar_concrete_tile_texture5",
                "engineersdecor:rebar_concrete_tile_texture6",
                "engineersdecor:rebar_concrete_tile_texture7"
            }).setHardness(5.0F).setResistance(2000.0F).setStepSound(Block.soundTypeStone),
            "rebar_concrete_tile",
            ItemBlockDecorMeta.class
        );

        GAS_CONCRETE_BLOCK = (BlockDecorFull)registerBlock(
            new BlockDecorFull("gas_concrete", Material.rock, new String[]{
                "engineersdecor:gas_concrete_texture0",
                "engineersdecor:gas_concrete_texture1",
                "engineersdecor:gas_concrete_texture2",
                "engineersdecor:gas_concrete_texture3",
                "engineersdecor:gas_concrete_texture4",
                "engineersdecor:gas_concrete_texture5",
                "engineersdecor:gas_concrete_texture6",
                "engineersdecor:gas_concrete_texture7"
            }).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone),
            "gas_concrete",
            ItemBlockDecorMeta.class
        );

        TREATED_WOOD_FLOOR = (BlockDecorFull)registerBlock(
            new BlockDecorFull("treated_wood_floor", Material.wood, new String[]{
                "engineersdecor:treated_wood_floor_texture_1",
                "engineersdecor:treated_wood_floor_texture_2",
                "engineersdecor:treated_wood_floor_texture_3",
                "engineersdecor:treated_wood_floor_texture_4"
            }).setHardness(0.5F).setResistance(10.0F).setStepSound(Block.soundTypeWood),
            "treated_wood_floor",
            ItemBlockDecorMeta.class
        );

        CLINKER_BRICK_STAIRS = (BlockDecorStairs)registerBlock(
            new BlockDecorStairs(
                "clinker_brick_stairs",
                CLINKER_BRICK_BLOCK,
                0,
                "engineersdecor:clinker_brick_texture0"
            ),
            "clinker_brick_stairs"
        );

        SLAG_BRICK_STAIRS = (BlockDecorStairs)registerBlock(
            new BlockDecorStairs(
                "slag_brick_stairs",
                SLAG_BRICK_BLOCK,
                0,
                "engineersdecor:slag_brick_texture0"
            ),
            "slag_brick_stairs"
        );

        CLINKER_BRICK_STAINED_STAIRS = (BlockDecorStairs)registerBlock(
            new BlockDecorStairs(
                "clinker_brick_stained_stairs",
                CLINKER_BRICK_STAINED_BLOCK,
                0,
                "engineersdecor:clinker_brick_stained_texture0"
            ),
            "clinker_brick_stained_stairs"
        );

        REBAR_CONCRETE_STAIRS = (BlockDecorStairs)registerBlock(
            new BlockDecorStairs(
                "rebar_concrete_stairs",
                REBAR_CONCRETE_BLOCK,
                0,
                "engineersdecor:rebar_concrete_texture0"
            ),
            "rebar_concrete_stairs"
        );

        REBAR_CONCRETE_TILE_STAIRS = (BlockDecorStairs)registerBlock(
            new BlockDecorStairs(
                "rebar_concrete_tile_stairs",
                REBAR_CONCRETE_TILE,
                0,
                "engineersdecor:rebar_concrete_tile_texture0"
            ),
            "rebar_concrete_tile_stairs"
        );

        GAS_CONCRETE_STAIRS = (BlockDecorStairs)registerBlock(
            new BlockDecorStairs(
                "gas_concrete_stairs",
                GAS_CONCRETE_BLOCK,
                0,
                "engineersdecor:gas_concrete_texture0"
            ),
            "gas_concrete_stairs"
        );

        METAL_RUNG_LADDER = (BlockDecorLadder)registerBlock(
            new BlockDecorLadder(
                "metal_rung_ladder",
                Material.iron,
                0.5F,
                20.0F,
                "engineersdecor:steel_texture"
            ),
            "metal_rung_ladder"
        );

        METAL_RUNG_STEPS = (BlockDecorLadder)registerBlock(
            new BlockDecorLadder(
                "metal_rung_steps",
                Material.iron,
                0.5F,
                20.0F,
                "engineersdecor:steel_texture"
            ),
            "metal_rung_steps"
        );

        TREATED_WOOD_LADDER = (BlockDecorLadder)registerBlock(
            new BlockDecorLadder(
                "treated_wood_ladder",
                Material.wood,
                0.5F,
                10.0F,
                "engineersdecor:treated_wood_rough_texture"
            ),
            "treated_wood_ladder"
        );

        CLINKER_BRICK_WALL = (BlockDecorWall)registerBlock(
            new BlockDecorWall(
                "clinker_brick_wall",
                CLINKER_BRICK_BLOCK,
                2.0F,
                20.0F,
                "engineersdecor:clinker_brick_wall0",
                "engineersdecor:clinker_brick_top"
            ),
            "clinker_brick_wall"
        );

        SLAG_BRICK_WALL = (BlockDecorWall)registerBlock(
            new BlockDecorWall(
                "slag_brick_wall",
                SLAG_BRICK_BLOCK,
                2.0F,
                15.0F,
                "engineersdecor:slag_brick_wall0",
                "engineersdecor:slag_brick_top"
            ),
            "slag_brick_wall"
        );

        CONCRETE_WALL = (BlockDecorWall)registerBlock(
            new BlockDecorWall(
                "concrete_wall",
                REBAR_CONCRETE_BLOCK,
                5.0F,
                20.0F,
                "engineersdecor:stone_decoration_concrete_by_blusunrize"
            ),
            "concrete_wall"
        );

        REBAR_CONCRETE_WALL = (BlockDecorWall)registerBlock(
            new BlockDecorWall(
                "rebar_concrete_wall",
                REBAR_CONCRETE_BLOCK,
                5.0F,
                2000.0F,
                "engineersdecor:rebar_concrete_texture0"
            ),
            "rebar_concrete_wall"
        );

        GAS_CONCRETE_WALL = (BlockDecorWall)registerBlock(
            new BlockDecorWall(
                "gas_concrete_wall",
                GAS_CONCRETE_BLOCK,
                1.5F,
                10.0F,
                "engineersdecor:gas_concrete_texture0"
            ),
            "gas_concrete_wall"
        );
    }

    private static void registerItems()
    {
        SIGN_MODLOGO = (ItemDecorSimple)registerItem(
            new ItemDecorSimple("sign_modlogo", "engineersdecor:sign_modlogo", false),
            "sign_modlogo"
        );
    }

    private static void registerTileEntities()
    {
        // Intentionally empty for this foundation step.
    }

    public static Block registerBlock(final Block block, final String registryName)
    {
        GameRegistry.registerBlock(block, registryName);
        REGISTERED_BLOCKS.add(block);
        return block;
    }

    public static Block registerBlock(
        final Block block,
        final String registryName,
        final Class<? extends ItemBlock> itemBlockClass
    ) {
        GameRegistry.registerBlock(block, itemBlockClass, registryName);
        REGISTERED_BLOCKS.add(block);
        return block;
    }

    public static Item registerItem(final Item item, final String registryName)
    {
        GameRegistry.registerItem(item, registryName);
        REGISTERED_ITEMS.add(item);
        return item;
    }

    public static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String registryName)
    {
        GameRegistry.registerTileEntity(tileEntityClass, ModEngineersDecor.MODID + ":" + registryName);
    }
}

