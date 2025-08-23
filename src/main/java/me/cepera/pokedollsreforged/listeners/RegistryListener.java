//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\thoma\OneDrive\Documents\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.cepera.pokedollsreforged.listeners;

import me.cepera.pokedollsreforged.blocks.*;
import net.minecraftforge.event.*;
import net.minecraft.block.*;
import com.pixelmonmod.pixelmon.enums.*;
import me.cepera.pokedollsreforged.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.cepera.pokedollsreforged.items.*;
import com.pixelmonmod.pixelmon.config.*;
import java.util.*;

public class RegistryListener
{
    public static List<BlockPokedoll> pokedolls;
    
    @SubscribeEvent
    public void registerBlocks(final RegistryEvent.Register<Block> event) {
        this.registerBothPokedolls(EnumSpecies.Azurill);
        this.registerBothPokedolls(EnumSpecies.Baltoy);
        this.registerBothPokedolls(EnumSpecies.Blastoise);
        this.registerBothPokedolls(EnumSpecies.Charizard);
        this.registerBothPokedolls(EnumSpecies.Chikorita);
        this.registerBothPokedolls(EnumSpecies.Clefairy);
        this.registerBothPokedolls(EnumSpecies.Cyndaquil);
        this.registerBothPokedolls(EnumSpecies.Ditto);
        this.registerBothPokedolls(EnumSpecies.Duskull);
        this.registerBothPokedolls(EnumSpecies.Gulpin);
        this.registerBothPokedolls(EnumSpecies.Jigglypuff);
        this.registerBothPokedolls(EnumSpecies.Kecleon);
        this.registerBothPokedolls(EnumSpecies.Lapras);
        this.registerBothPokedolls(EnumSpecies.Lotad);
        this.registerBothPokedolls(EnumSpecies.Marill);
        this.registerBothPokedolls(EnumSpecies.Meowth);
        this.registerBothPokedolls(EnumSpecies.Mudkip);
        this.registerBothPokedolls(EnumSpecies.Pichu);
        this.registerBothPokedolls(EnumSpecies.Pikachu);
        this.registerBothPokedolls(EnumSpecies.Regice);
        this.registerBothPokedolls(EnumSpecies.Regirock);
        this.registerBothPokedolls(EnumSpecies.Registeel);
        this.registerBothPokedolls(EnumSpecies.Rhydon);
        this.registerBothPokedolls(EnumSpecies.Seedot);
        this.registerBothPokedolls(EnumSpecies.Skitty);
        this.registerBothPokedolls(EnumSpecies.Smoochum);
        this.registerBothPokedolls(EnumSpecies.Snorlax);
        this.registerBothPokedolls(EnumSpecies.Swablu);
        this.registerBothPokedolls(EnumSpecies.Togepi);
        this.registerBothPokedolls(EnumSpecies.Torchic);
        this.registerBothPokedolls(EnumSpecies.Totodile);
        this.registerBothPokedolls(EnumSpecies.Treecko);
        this.registerBothPokedolls(EnumSpecies.Venusaur);
        this.registerBothPokedolls(EnumSpecies.Wailmer);
        this.registerBothPokedolls(EnumSpecies.Wynaut);
        this.registerBothPokedolls(EnumSpecies.Arceus);
        this.registerBothPokedolls(EnumSpecies.Celebi);
        this.registerBothPokedolls(EnumSpecies.Cresselia);
        this.registerBothPokedolls(EnumSpecies.Cubone);
        this.registerBothPokedolls(EnumSpecies.Dialga);
        this.registerBothPokedolls(EnumSpecies.Eevee);
        this.registerBothPokedolls(EnumSpecies.Ekans);
        this.registerBothPokedolls(EnumSpecies.Krabby);
        this.registerBothPokedolls(EnumSpecies.Litten);
        this.registerBothPokedolls(EnumSpecies.Poliwhirl);
        this.registerBothPokedolls(EnumSpecies.Popplio);
        this.registerBothPokedolls(EnumSpecies.Rowlet);
        this.registerBothPokedolls(EnumSpecies.Sableye);
        this.registerBothPokedolls(EnumSpecies.Zeraora);
        this.registerBothPokedolls(EnumSpecies.Azelf);
        this.registerBothPokedolls(EnumSpecies.Articuno);
        this.registerBothPokedolls(EnumSpecies.Deoxys);
        this.registerBothPokedolls(EnumSpecies.Darkrai);
        this.registerBothPokedolls(EnumSpecies.Giratina);
        this.registerBothPokedolls(EnumSpecies.Entei);
        this.registerBothPokedolls(EnumSpecies.Groudon);
        this.registerBothPokedolls(EnumSpecies.Hooh);
        this.registerBothPokedolls(EnumSpecies.Heracross);
        this.registerBothPokedolls(EnumSpecies.Lugia);
        this.registerBothPokedolls(EnumSpecies.Latios);
        this.registerBothPokedolls(EnumSpecies.Latias);
        this.registerBothPokedolls(EnumSpecies.Kyogre);
        this.registerBothPokedolls(EnumSpecies.Jirachi);
        this.registerBothPokedolls(EnumSpecies.Mewtwo);
        this.registerBothPokedolls(EnumSpecies.Mew);
        this.registerBothPokedolls(EnumSpecies.Mesprit);
        this.registerBothPokedolls(EnumSpecies.Manaphy);
        this.registerBothPokedolls(EnumSpecies.Palkia);
        this.registerBothPokedolls(EnumSpecies.Moltres);
        this.registerBothPokedolls(EnumSpecies.Rayquaza);
        this.registerBothPokedolls(EnumSpecies.Raikou);
        this.registerBothPokedolls(EnumSpecies.Suicune);
        this.registerBothPokedolls(EnumSpecies.Shaymin);
        this.registerBothPokedolls(EnumSpecies.Regigigas);
        this.registerBothPokedolls(EnumSpecies.Uxie);
        this.registerBothPokedolls(EnumSpecies.Zapdos);
        PokedollsReforged.PROXY.registerPokedollItemModels();
    }
    
    private void registerBothPokedolls(final EnumSpecies pokemon) {
        this.registerPokedoll(pokemon, false);
        this.registerPokedoll(pokemon, true);
    }
    
    private void registerPokedoll(final EnumSpecies pokemon, final boolean isShiny) {
        final BlockPokedoll pokedoll = new BlockPokedoll(pokemon, isShiny);
        PixelmonBlocks.registerBlock((Block)pokedoll, (Class)ItemPokedoll.class, (isShiny ? "shiny" : "") + "pokedoll_" + pokemon.name.toLowerCase());
        RegistryListener.pokedolls.add(pokedoll);
    }
    
    static {
        RegistryListener.pokedolls = new ArrayList<BlockPokedoll>();
    }
}
