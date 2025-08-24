package me.cepera.pokedollsreforged.render;

import me.cepera.pokedollsreforged.tiles.TileEntityPokedoll;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

// Pixelmon
import com.pixelmonmod.pixelmon.blocks.tileEntities.TileEntityPokeDoll;
import com.pixelmonmod.pixelmon.client.render.tileEntities.RenderTileEntityPokedoll;

/**
 * TESR passerelle pour utiliser le renderer Pixelmon sur notre propre TileEntity.
 */
public class RenderTileEntityPokedollBridge extends TileEntitySpecialRenderer<TileEntityPokedoll> {

    private final RenderTileEntityPokedoll delegate = new RenderTileEntityPokedoll();

    @Override
    public void render(TileEntityPokedoll te,
                       double x, double y, double z,
                       float partialTicks, int destroyStage, float alpha) {

        // Crée un TE Pixelmon “fake” pointant vers le même monde/pos
        TileEntityPokeDoll pxTe = new TileEntityPokeDoll();
        pxTe.setWorld(te.getWorld());
        pxTe.setPos(te.getPos());

        // Délègue le rendu au TESR de Pixelmon
        delegate.render(pxTe, x, y, z, partialTicks, destroyStage, alpha);
    }
}
