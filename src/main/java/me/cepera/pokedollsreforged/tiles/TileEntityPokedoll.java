//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\thoma\OneDrive\Documents\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.cepera.pokedollsreforged.tiles;

import net.minecraft.tileentity.*;
import com.pixelmonmod.pixelmon.blocks.tileEntities.*;
import com.pixelmonmod.pixelmon.enums.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import com.pixelmonmod.pixelmon.client.models.*;
import net.minecraft.nbt.*;

public class TileEntityPokedoll extends TileEntity implements ISpecialTexture
{
    private EnumSpecies pokemon;
    private boolean isShiny;
    private ResourceLocation texture;
    private ModelHolder<ModelBase> model;
    private long lastActivate;

    public TileEntityPokedoll() {
        this.lastActivate = 0L;
    }

    public TileEntityPokedoll(final EnumSpecies pokemon, final boolean isShiny) {
        this.lastActivate = 0L;
        this.pokemon = pokemon;
        this.isShiny = isShiny;
        this.getModelAndTexture();
    }

    public void getModelAndTexture() {
        if (this.pokemon == null) {
            this.pokemon = EnumSpecies.Charizard;
        }
        String pokName = this.pokemon.name.toLowerCase();
        pokName = pokName.replace("-", "");
        this.texture = new ResourceLocation("pokedollsreforged", "textures/blocks/pokedolls/" + (this.isShiny ? "shiny/" : "") + pokName + ".png");
        this.model = (ModelHolder<ModelBase>)new AsyncGenericModelHolder(new ResourceLocation("pixelmon", "models/pokedolls/" + pokName + ".pqc"));
    }

    public long getLastActivate() {
        return this.lastActivate;
    }

    public void activate() {
        this.lastActivate = System.currentTimeMillis();
    }

    public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        if (this.pokemon != null) {
            nbt.setInteger("pokemon", this.pokemon.ordinal());
            nbt.setBoolean("shiny", this.isShiny);
        }
        return nbt;
    }

    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("pokemon")) {
            this.pokemon = EnumSpecies.values()[nbt.getInteger("pokemon")];
            this.isShiny = nbt.getBoolean("shiny");
        }
        else {
            this.pokemon = EnumSpecies.Charizard;
        }
    }

    public ResourceLocation getTexture() {
        if (this.texture == null) {
            this.getModelAndTexture();
        }
        return this.texture;
    }

    public ModelHolder<ModelBase> getModel() {
        if (this.model == null) {
            this.getModelAndTexture();
        }
        return this.model;
    }
}
