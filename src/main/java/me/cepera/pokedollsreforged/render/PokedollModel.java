//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\thoma\OneDrive\Documents\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.cepera.pokedollsreforged.render;

import me.cepera.pokedollsreforged.blocks.*;
import net.minecraft.util.*;
import java.util.*;
import com.google.common.collect.*;
import net.minecraft.client.renderer.vertex.*;
import java.util.function.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraftforge.client.model.*;
import net.minecraftforge.common.model.*;

public class PokedollModel implements IModel {
    protected BlockPokedoll pokedoll;
    protected ResourceLocation texture;
    protected String model_str;
    protected ModelBlock model;

    public PokedollModel(final BlockPokedoll pokedoll) {
        this.pokedoll = pokedoll;
        String pokName = pokedoll.getPokemon().name.toLowerCase();
        pokName = pokName.replace("-", "");
        this.texture = new ResourceLocation(
                "pokedollsreforged",
                "items/pokedolls/" + (pokedoll.isShiny() ? "shiny/" : "") + pokName + "_pokedoll"
        );
        this.model_str = ("{'parent':'pokedollsreforged:item/pokedoll','textures': {'layer0':'"
                + this.texture.toString() + "'}}").replaceAll("'", "\"");
    }

    @Override
    public IModel retexture(final ImmutableMap<String, String> textures) {
        // on ignore les textures custom, même comportement qu'avant
        return new PokedollModel(this.pokedoll);
    }

    @Override
    public IModel process(final ImmutableMap<String, String> customData) {
        // on ignore les données custom, même comportement qu'avant
        return new PokedollModel(this.pokedoll);
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        // Pas de cast en Object -> liste typée de ResourceLocation
        return ImmutableList.of(new ResourceLocation("pokedollsreforged", "item/pokedoll"));
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        // Builder typé correctement
        ImmutableSet.Builder<ResourceLocation> builder = ImmutableSet.builder();
        builder.add(this.texture);
        return builder.build();
    }

    @Override
    public IBakedModel bake(final IModelState state,
                            final VertexFormat format,
                            final Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        this.model = ModelBlock.deserialize(this.model_str);
        return new ItemLayerModel(this.model).bake(state, format, bakedTextureGetter);
    }

    @Override
    public IModelState getDefaultState() {
        return TRSRTransformation.identity();
    }
}
