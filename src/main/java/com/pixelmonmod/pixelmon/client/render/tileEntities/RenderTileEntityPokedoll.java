//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\thoma\OneDrive\Documents\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package com.pixelmonmod.pixelmon.client.render.tileEntities;

import me.cepera.pokedollsreforged.tiles.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.tileentity.*;

public class RenderTileEntityPokedoll extends TileEntityRenderer<TileEntityPokedoll>
{
    public RenderTileEntityPokedoll() {
        super.scale = 0.7f;
        super.disableCulling = true;
    }
    
    public void renderTileEntity(final TileEntityPokedoll te, final IBlockState state, final double x, final double y, final double z, final float partialTicks, final int destroyStage) {
        this.bindTexture(te.getTexture());
        te.getModel().getModel().render((Entity)null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 1.0f);
    }
}
