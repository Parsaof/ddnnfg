//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\thoma\OneDrive\Documents\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.cepera.pokedollsreforged.blocks;

import com.pixelmonmod.pixelmon.blocks.*;
import com.pixelmonmod.pixelmon.enums.*;
import net.minecraft.block.material.*;
import net.minecraft.block.*;
import me.cepera.pokedollsreforged.*;
import net.minecraft.block.state.*;
import net.minecraft.world.*;
import net.minecraft.tileentity.*;
import me.cepera.pokedollsreforged.tiles.*;

public class BlockPokedoll extends GenericRotatableModelBlock
{
    private EnumSpecies pokemon;
    private boolean shiny;

    public BlockPokedoll(final EnumSpecies pokemon, final boolean shiny) {
        super(Material.CLOTH);
        this.setSoundType(SoundType.CLOTH);
        this.setTranslationKey("pokedoll_" + pokemon.name.toLowerCase().replace("-", ""));
        this.setHardness(1.0f);
        this.setCreativeTab(PokedollsReforged.PROXY.creativeTab);
        this.pokemon = pokemon;
        this.shiny = shiny;
    }

    public EnumSpecies getPokemon() {
        return this.pokemon;
    }

    public boolean isShiny() {
        return this.shiny;
    }

    public boolean isOpaqueCube(final IBlockState state) {
        return false;
    }

    public boolean isFullCube(final IBlockState state) {
        return false;
    }

    public TileEntity createNewTileEntity(final World worldIn, final int meta) {
        return new TileEntityPokedoll(this.pokemon, this.shiny);
    }
}