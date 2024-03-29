package com.wither.christmas_decorations;

import com.wither.christmas_decorations.block.ChristmasTreeBlock;
import com.wither.christmas_decorations.block.SnowglobeBlock;
import com.wither.christmas_decorations.block.SnowmanBlock;
import com.wither.christmas_decorations.item.EarmuffsArmorMaterial;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.*;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {
    //TODO: Add loot tables for blocks

    private static final String MOD_ID = "christmas_decorations";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public static Block CHRISTMAS_TREE;
    public static Block SNOWMAN;
    public static Block SNOWGLBOE;

    public static Item CHRISTMAS_ORNAMENT;
    public static Item EARMUFFS;

    public static final ArmorMaterial EARMUFFS_ARMOR_MATERIAL = new EarmuffsArmorMaterial();

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Christmas Decorations!");
        //===BLOCKS===
        CHRISTMAS_TREE = Registry.register(
                Registry.BLOCK,
                new Identifier(MOD_ID, "christmas_tree"),
                new ChristmasTreeBlock(
                        AbstractBlock.Settings.of(Material.WOOD)
                                .nonOpaque()
                                .luminance((state) -> {
                                    return 1;
                                })
                                .emissiveLighting((state, world, pos) -> {
                                    return state.get(ChristmasTreeBlock.HAS_ORNAMENT);
                                })
                ));
        SNOWMAN = Registry.register(
                Registry.BLOCK,
                new Identifier(MOD_ID, "snowman"),
                new SnowmanBlock(
                        AbstractBlock.Settings.of(Material.SNOW_BLOCK).nonOpaque()
                )
        );
        SNOWGLBOE = Registry.register(
                Registry.BLOCK,
                new Identifier(MOD_ID, "snowglobe"),
                new SnowglobeBlock(
                        AbstractBlock.Settings.of(Material.GLASS).nonOpaque()
                ));

        //===BLOCK ITEMS===
        Registry.register(
                Registry.ITEM,
                Registry.BLOCK.getId(CHRISTMAS_TREE),
                new BlockItem(CHRISTMAS_TREE, new Item.Settings().group(ItemGroup.DECORATIONS))
        );
        Registry.register(
                Registry.ITEM,
                Registry.BLOCK.getId(SNOWMAN),
                new BlockItem(SNOWMAN, new Item.Settings().group(ItemGroup.DECORATIONS))
        );
        Registry.register(Registry.ITEM,
                new Identifier(MOD_ID, "snowglobe"),
                new BlockItem(SNOWGLBOE, new Item.Settings().group(ItemGroup.DECORATIONS)))
        ;

        //===ITEMS===
        CHRISTMAS_ORNAMENT = Registry.register(
                Registry.ITEM,
                new Identifier(MOD_ID, "christmas_ornament"),
                new Item(new Item.Settings().group(ItemGroup.MISC).maxCount(1))
        );

        EARMUFFS = Registry.register(
                Registry.ITEM,
                new Identifier(MOD_ID, "earmuffs"),
                new ArmorItem(EARMUFFS_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(1))
        );

        LOGGER.info("Christmas Decorations initialized!");
    }
}
