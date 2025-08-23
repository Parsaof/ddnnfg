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

public class PokedollModel implements IModel
{
    protected BlockPokedoll pokedoll;
    protected ResourceLocation texture;
    protected String model_str;
    protected ModelBlock model;
    
    public PokedollModel(final BlockPokedoll pokedoll) {
        this.pokedoll = pokedoll;
        String pokName = pokedoll.getPokemon().name.toLowerCase();
        pokName = pokName.replace("-", "");
        this.texture = new ResourceLocation("pokedollsreforged", "items/pokedolls/" + (pokedoll.isShiny() ? "shiny/" : "") + pokName + "_pokedoll");
        this.model_str = ("{'parent':'pokedollsreforged:item/pokedoll','textures': {'layer0':'" + this.texture.toString() + "'}}").replaceAll("'", "\"");
    }
    
    public IModel retexture(final ImmutableMap<String, String> textures) {
        return (IModel)new PokedollModel(this.pokedoll);
    }
    
    public IModel process(final ImmutableMap<String, String> customData) {
        return (IModel)new PokedollModel(this.pokedoll);
    }
    
    public Collection<ResourceLocation> getDependencies() {
        return (Collection<ResourceLocation>)ImmutableList.of((Object)new ResourceLocation("pokedollsreforged:item/pokedoll"));
    }
    
    public Collection<ResourceLocation> getTextures() {
        final ImmutableSet.Builder<ResourceLocation> builder = (ImmutableSet.Builder<ResourceLocation>)ImmutableSet.builder();
        builder.add((Object)this.texture);
        return (Collection<ResourceLocation>)builder.build();
    }
    
    public IBakedModel bake(final IModelState state, final VertexFormat format, final Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        this.model = ModelBlock.deserialize(this.model_str);
        return new ItemLayerModel(this.model).bake(state, format, (Function)bakedTextureGetter);
    }
    
    public IModelState getDefaultState() {
        return (IModelState)TRSRTransformation.identity();
    }
}
