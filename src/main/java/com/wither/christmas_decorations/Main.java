package com.wither.christmas_decorations;

import com.wither.christmas_decorations.block.CandleBlock;
import com.wither.christmas_decorations.block.ChristmasTreeBlock;
import com.wither.christmas_decorations.block.SnowmanBlock;
import com.wither.christmas_decorations.item.CandleWax;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
    private static final String MODID = "christmas_decorations";
    private static Block CHRISTMAS_TREE;
    private static Block SNOWMAN;
    private static Block CANDLE;

    @Override
    public void onInitialize() {
        CHRISTMAS_TREE = Registry.BLOCK.register(
                new Identifier(MODID, "christmas_tree"),
                new ChristmasTreeBlock(
                        FabricBlockSettings.of(Material.WOOD).build()
                ));
        SNOWMAN = Registry.BLOCK.register(
                new Identifier(MODID, "snowman"),
                new SnowmanBlock(
                        FabricBlockSettings.of(Material.SNOW_BLOCK).build()
                )
        );
        CANDLE = Registry.BLOCK.register(
                new Identifier(MODID, "candle"),
                new CandleBlock(
                        FabricBlockSettings.of(Material.WOOL, MaterialColor.WHITE).lightLevel(12).build()
                )
        );

        Registry.ITEM.register(
                Registry.BLOCK.getId(CHRISTMAS_TREE),
                new BlockItem(CHRISTMAS_TREE, new Item.Settings().itemGroup(ItemGroup.DECORATIONS))
        );
        Registry.ITEM.register(
                Registry.BLOCK.getId(SNOWMAN),
                new BlockItem(SNOWMAN, new Item.Settings().itemGroup(ItemGroup.DECORATIONS))
        );
        Registry.ITEM.register(
                Registry.BLOCK.getId(CANDLE),
                new BlockItem(CANDLE, new Item.Settings().itemGroup(ItemGroup.DECORATIONS))
        );

        Registry.ITEM.register(
                new Identifier(MODID, "candle_wax"),
                new CandleWax(2, 0.5F, false, new Item.Settings().itemGroup(ItemGroup.MATERIALS))
        );
    }
}
