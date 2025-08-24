package me.cepera.pokedollsreforged.render;

import me.cepera.pokedollsreforged.tiles.TileEntityPokedoll;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

// Pour détecter un modèle "dummy" (non encore chargé)
import com.pixelmonmod.pixelmon.client.models.AsyncGenericModelHolder;

public class RenderTileEntityPokedoll extends TileEntitySpecialRenderer<TileEntityPokedoll> {

    @Override
    public void render(TileEntityPokedoll te,
                       double x, double y, double z,
                       float partialTicks, int destroyStage, float alpha) {

        if (te == null || te.getWorld() == null) return;

        if (te.getModel() == null || te.getTexture() == null) {
            te.getModelAndTexture();
            if (te.getModel() == null || te.getTexture() == null) return;
        }

        ModelBase model = te.getModel().getModel();
        if (model == null
                || model == com.pixelmonmod.pixelmon.client.models.AsyncGenericModelHolder.DUMMY)
            return;

        ResourceLocation tex = te.getTexture();

        GlStateManager.pushMatrix();

        // état propre = pas de transparence, couleur blanche opaque
        GlStateManager.disableBlend();
        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.enableAlpha();
        GlStateManager.disableCull();      // la plupart des SMD ont besoin de voir les 2 faces

        // centre du bloc (avec un mini offset vertical pour éviter le z-fighting)
        GlStateManager.translate(x + 0.5D, y + 0.01D, z + 0.5D);

        // orientation du bloc si propriété FACING présente
        net.minecraft.block.state.IBlockState state = te.getWorld().getBlockState(te.getPos());
        if (state != null && state.getPropertyKeys().contains(net.minecraft.block.BlockHorizontal.FACING)) {
            net.minecraft.util.EnumFacing f = state.getValue(net.minecraft.block.BlockHorizontal.FACING);
            GlStateManager.rotate(-f.getHorizontalAngle(), 0F, 1F, 0F);
        }

        // CORRECTION SMD : retourner le repère Minecraft -> OpenGL des SMD
        GlStateManager.rotate(180F, 1F, 0F, 0F); // <- corrige "à l'envers"

        // taille "doll" proche du rendu Pixelmon
        GlStateManager.scale(0.7F, 0.7F, 0.7F);

        // dessin
        Minecraft.getMinecraft().getTextureManager().bindTexture(tex);
        model.render(null, 0F, 0F, 0F, 0F, 0F, 1.0F); // scale = 1.0F pour GenericSmdModel

        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }
}
