package me.cepera.pokedollsreforged.proxy;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import me.cepera.pokedollsreforged.blocks.BlockPokedoll;
import net.minecraft.block.Block;
import me.cepera.pokedollsreforged.render.PokedollModel;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import me.cepera.pokedollsreforged.tiles.TileEntityPokedoll;
import me.cepera.pokedollsreforged.render.RenderTileEntityPokedoll;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import me.cepera.pokedollsreforged.listeners.RegistryListener;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void preinit(final FMLPreInitializationEvent e) {
        super.preinit(e);

        // Loader de modèles item pour les poupées
        ModelLoaderRegistry.registerLoader(new ICustomModelLoader() {
            @Override
            public void onResourceManagerReload(final IResourceManager resourceManager) {}

            @Override
            public IModel loadModel(final ResourceLocation modelLocation) throws Exception {
                ResourceLocation loc = modelLocation;
                final String path = loc.getPath();

                // Vérifie si c'est un item de pokedoll (custom ou Pokémon)
                if (path.contains("pokedoll_") || path.contains("custom_")) {
                    final int start = (path.lastIndexOf('/') > -1) ? path.lastIndexOf('/') + 1 : 0;
                    final int end   = (path.indexOf('#') > -1) ? path.indexOf('#') : path.length();
                    loc = new ResourceLocation(loc.getNamespace(), path.substring(start, end));

                    final BlockPokedoll pokedoll = (BlockPokedoll) Block.REGISTRY.getObject(loc);
                    if (pokedoll != null) {
                        return new PokedollModel(pokedoll);
                    }
                }
                return ModelLoaderRegistry.getMissingModel();
            }

            @Override
            public boolean accepts(final ResourceLocation modelLocation) {
                return modelLocation.getNamespace().equals("pokedollsreforged") &&
                        (modelLocation.getPath().contains("pokedoll_") ||
                                modelLocation.getPath().contains("custom_")) &&
                        !modelLocation.getPath().contains("models/item/");
            }
        });
    }

    @Override
    public void postinit(final net.minecraftforge.fml.common.event.FMLPostInitializationEvent e) {
        super.postinit(e);
        // ✅ on attache NOTRE renderer (plus de réflexion/bridge Pixelmon)
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPokedoll.class, new RenderTileEntityPokedoll());
    }

    @Override
    public void registerPokedollItemModels() {
        for (final BlockPokedoll pokedoll : RegistryListener.pokedolls) {
            ModelLoader.setCustomModelResourceLocation(
                    Item.getItemFromBlock(pokedoll),
                    0,
                    new ModelResourceLocation(pokedoll.getRegistryName(), "inventory")
            );
        }
    }
}