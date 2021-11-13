package com.wither.christmas_decorations;

import com.wither.christmas_decorations.block.ChristmasTreeBlock;
import com.wither.christmas_decorations.block.SnowglobeBlock;
import com.wither.christmas_decorations.block.SnowmanBlock;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.*;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {
    private static final String MOD_ID = "christmas_decorations";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public static Block CHRISTMAS_TREE;
    public static Block SNOWMAN;
    public static Block SNOWGLBOE;

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Christmas Decorations!");

        CHRISTMAS_TREE = Registry.register(
                Registry.BLOCK,
                new Identifier(MOD_ID, "christmas_tree"),
                new ChristmasTreeBlock(
                        AbstractBlock.Settings.of(Material.WOOD).nonOpaque()
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
                        AbstractBlock.Settings.of(Material.WOOD).nonOpaque()
                ));

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

        Registry.register(
                Registry.ITEM,
                new Identifier(MOD_ID, "christmas_ornament"),
                new Item(new Item.Settings().group(ItemGroup.MISC).maxCount(1))
        );

        LOGGER.info("Christmas Decorations initialized!");
    }
}
