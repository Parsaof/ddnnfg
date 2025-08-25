package me.cepera.pokedollsreforged.items;

import me.cepera.pokedollsreforged.blocks.BlockPokedoll;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemPokedoll extends ItemBlock {
    private BlockPokedoll pokedoll;

    public ItemPokedoll(Block block) {
        super(block);
        this.pokedoll = (BlockPokedoll)block;
    }

    public String getItemStackDisplayName(ItemStack stack) {
        if (pokedoll.isCustom()) {
            return I18n.translateToLocalFormatted("item.custom_pokedoll.name",
                    new Object[] { I18n.translateToLocal("pokedoll_custom." + pokedoll.getCustomBlock().getName() + ".name") });
        } else {
            return I18n.translateToLocalFormatted(this.pokedoll.isShiny() ? "item.shinypokedoll.name" : "item.pokedoll.name",
                    new Object[] { I18n.translateToLocal("pixelmon." + this.pokedoll.getPokemon().name.toLowerCase() + ".name") });
        }
    }
}