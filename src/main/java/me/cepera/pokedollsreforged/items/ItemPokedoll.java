//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\thoma\OneDrive\Documents\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.cepera.pokedollsreforged.items;

import me.cepera.pokedollsreforged.blocks.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.text.translation.*;

public class ItemPokedoll extends ItemBlock
{
    private BlockPokedoll pokedoll;

    public ItemPokedoll(final Block block) {
        super(block);
        this.pokedoll = (BlockPokedoll)block;
    }

    public String getItemStackDisplayName(final ItemStack stack) {
        return I18n.translateToLocalFormatted(this.pokedoll.isShiny() ? "item.shinypokedoll.name" : "item.pokedoll.name", new Object[] { I18n.translateToLocal("pixelmon." + this.pokedoll.getPokemon().name.toLowerCase() + ".name") });
    }
}