package me.cepera.pokedollsreforged.tiles;

import net.minecraft.tileentity.TileEntity;
import com.pixelmonmod.pixelmon.blocks.tileEntities.ISpecialTexture;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.model.ModelBase;
import com.pixelmonmod.pixelmon.client.models.ModelHolder;
import com.pixelmonmod.pixelmon.client.models.AsyncGenericModelHolder;
import net.minecraft.nbt.NBTTagCompound;
import me.cepera.pokedollsreforged.blocks.BlockPokedoll;

public class TileEntityPokedoll extends TileEntity implements ISpecialTexture {
    private EnumSpecies pokemon;
    private BlockPokedoll.BlockCustom customBlock;
    private boolean isShiny;
    private boolean isCustom;
    private ResourceLocation texture;
    private ModelHolder<ModelBase> model;
    private long lastActivate;

    public TileEntityPokedoll() {
        this.lastActivate = 0L;
    }

    // Constructeur pour Pokémon
    public TileEntityPokedoll(EnumSpecies pokemon, boolean isShiny) {
        this.lastActivate = 0L;
        this.pokemon = pokemon;
        this.isShiny = isShiny;
        this.isCustom = false;
        this.getModelAndTexture();
    }

    // Constructeur pour bloc custom
    public TileEntityPokedoll(BlockPokedoll.BlockCustom customBlock) {
        this.lastActivate = 0L;
        this.customBlock = customBlock;
        this.isCustom = true;
        this.isShiny = false;
        this.getModelAndTexture();
    }

    public void getModelAndTexture() {
        if (this.isCustom) {
            String customName = this.customBlock.getName();
            this.texture = new ResourceLocation("pokedollsreforged", "textures/blocks/custom/" + customName + ".png");
            // Utilisation du même chemin que les Pokémon mais dans votre mod
            this.model = (ModelHolder<ModelBase>) new AsyncGenericModelHolder(new ResourceLocation("pokedollsreforged", "models/pokedolls/" + customName + ".pqc"));
        } else {
            // Logique existante pour les Pokémon
            if (this.pokemon == null) {
                this.pokemon = EnumSpecies.Charizard;
            }
            String pokName = this.pokemon.name.toLowerCase();
            pokName = pokName.replace("-", "");
            this.texture = new ResourceLocation("pokedollsreforged", "textures/blocks/pokedolls/" + (this.isShiny ? "shiny/" : "") + pokName + ".png");
            this.model = (ModelHolder<ModelBase>) new AsyncGenericModelHolder(new ResourceLocation("pixelmon", "models/pokedolls/" + pokName + ".pqc"));
        }
    }

    public long getLastActivate() {
        return this.lastActivate;
    }

    public void activate() {
        this.lastActivate = System.currentTimeMillis();
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        if (this.isCustom) {
            nbt.setBoolean("isCustom", true);
            nbt.setString("customBlock", this.customBlock.getName());
        } else {
            if (this.pokemon != null) {
                nbt.setBoolean("isCustom", false);
                nbt.setInteger("pokemon", this.pokemon.ordinal());
                nbt.setBoolean("shiny", this.isShiny);
            }
        }
        return nbt;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("isCustom") && nbt.getBoolean("isCustom")) {
            String customName = nbt.getString("customBlock");
            for (BlockPokedoll.BlockCustom custom : BlockPokedoll.BlockCustom.values()) {
                if (custom.getName().equals(customName)) {
                    this.customBlock = custom;
                    break;
                }
            }
            this.isCustom = true;
            this.isShiny = false;
        } else {
            if (nbt.hasKey("pokemon")) {
                this.pokemon = EnumSpecies.values()[nbt.getInteger("pokemon")];
                this.isShiny = nbt.getBoolean("shiny");
                this.isCustom = false;
            } else {
                this.pokemon = EnumSpecies.Charizard;
                this.isCustom = false;
            }
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