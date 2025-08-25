// src/main/java/me/cepera/pokedollsreforged/render/PokedollModel.java
package me.cepera.pokedollsreforged.render;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import me.cepera.pokedollsreforged.blocks.BlockPokedoll;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

import java.util.Collection;
import java.util.function.Function;

public class PokedollModel implements IModel {

    private final BlockPokedoll pokedoll;
    private final ResourceLocation texture;   // layer0 de l'icône
    private final String modelStr;            // JSON inline utilisé par ItemLayerModel
    private ModelBlock model;

    public PokedollModel(BlockPokedoll b) {
        this.pokedoll = b;

        String texPath;
        if (b.isCustom()) {
            // Pour les blocs custom - utilise une icône spécifique
            texPath = "items/custom/" + b.getCustomBlock().getName();
        } else {
            // Pour les Pokémon - utilise le chemin correct pour les shiny
            String pokemonName = b.getPokemon().name.toLowerCase().replace("-", "");
            if (b.isShiny()) {
                texPath = "items/pokedolls/shiny/" + pokemonName + "_pokedoll";
            } else {
                texPath = "items/pokedolls/" + pokemonName + "_pokedoll";
            }
        }

        this.texture = new ResourceLocation("pokedollsreforged", texPath);

        this.modelStr = "{\n" +
                "  \"parent\": \"builtin/generated\",\n" +
                "  \"textures\": { \"layer0\": \"" + this.texture.toString() + "\" }\n" +
                "}";
    }

    @Override public IModel retexture(com.google.common.collect.ImmutableMap<String, String> textures) {
        return new PokedollModel(this.pokedoll);
    }

    @Override public IModel process(com.google.common.collect.ImmutableMap<String, String> customData) {
        return new PokedollModel(this.pokedoll);
    }

    @Override public Collection<ResourceLocation> getDependencies() {
        return ImmutableList.of(); // rien d’autre à charger
    }

    @Override public Collection<ResourceLocation> getTextures() {
        return ImmutableSet.of(this.texture); // **texture non nulle**
    }

    @Override
    public net.minecraft.client.renderer.block.model.IBakedModel bake(
            IModelState state,
            VertexFormat format,
            Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {

        this.model = ModelBlock.deserialize(this.modelStr);
        return new ItemLayerModel(this.model).bake(state, format, bakedTextureGetter);
    }

    @Override public IModelState getDefaultState() {
        return TRSRTransformation.identity();
    }
}
