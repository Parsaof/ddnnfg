//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\thoma\OneDrive\Documents\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.cepera.pokedollsreforged.proxy;

import net.minecraft.client.resources.*;
import net.minecraft.util.*;
import me.cepera.pokedollsreforged.blocks.*;
import net.minecraft.block.*;
import me.cepera.pokedollsreforged.render.*;
import net.minecraftforge.fml.common.event.*;
import me.cepera.pokedollsreforged.tiles.*;
import com.pixelmonmod.pixelmon.client.render.tileEntities.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraft.client.renderer.tileentity.*;
import me.cepera.pokedollsreforged.listeners.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraftforge.client.model.*;
import me.cepera.pokedollsreforged.render.RenderTileEntityPokedollBridge;
import java.util.*;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preinit(final FMLPreInitializationEvent e) {
        super.preinit(e);
        ModelLoaderRegistry.registerLoader(new ICustomModelLoader() {
            @Override
            public void onResourceManagerReload(final IResourceManager resourceManager) {
            }

            @Override
            public IModel loadModel(final ResourceLocation modelLocation) throws Exception {
                ResourceLocation loc = modelLocation;
                loc = new ResourceLocation(
                        loc.getNamespace(),
                        loc.getPath().substring(
                                (loc.getPath().lastIndexOf("/") > -1) ? (loc.getPath().lastIndexOf("/") + 1) : 0,
                                (loc.getPath().indexOf("#") > -1) ? loc.getPath().indexOf("#") : loc.getPath().length()
                        )
                );

                // FIX: passer un ResourceLocation directement, pas un Object
                final BlockPokedoll pokedoll = (BlockPokedoll) Block.REGISTRY.getObject(loc);

                final PokedollModel model = new PokedollModel(pokedoll);
                return model;
            }

            @Override
            public boolean accepts(final ResourceLocation modelLocation) {
                return modelLocation.getNamespace().equals("pokedollsreforged")
                        && modelLocation.getPath().contains("pokedoll_")
                        && !modelLocation.getPath().contains("models/item/");
            }
        });
    }

    @Override
    public void postinit(final FMLPostInitializationEvent e) {
        super.postinit(e);
        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityPokedoll.class,
                new RenderTileEntityPokedoll()
        );
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
