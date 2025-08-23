//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\thoma\OneDrive\Documents\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package com.pixelmonmod.pixelmon.client.models;

import net.minecraft.client.model.*;
import com.pixelmonmod.pixelmon.client.models.blocks.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import java.time.*;
import java.util.concurrent.*;

public class AsyncGenericModelHolder extends ModelHolder<ModelBase>
{
    public static final ModelBase DUMMY;
    private Future<GenericSmdModel> future;
    private ResourceLocation resource;
    
    public AsyncGenericModelHolder(final ResourceLocation model) {
        this.resource = model;
    }
    
    public void render() {
        this.getModel().render((Entity)null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 1.0f);
    }
    
    public void render(final float scale) {
        this.getModel().render((Entity)null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, scale);
    }
    
    public ModelBase getModel() {
        this.lastAccess = Instant.now().getEpochSecond();
        if (this.model != null) {
            return this.model;
        }
        if (this.future == null) {
            this.future = (Future<GenericSmdModel>)ResourceLoader.addTask((Callable)this::loadModel);
        }
        if (this.future.isDone()) {
            try {
                this.model = (ModelBase)this.future.get();
                PixelmonModelHolder.loadedHolders.add(this);
            }
            catch (InterruptedException | ExecutionException ex3) {
                final Exception ex2;
                final Exception ex = ex2;
                ex.printStackTrace();
            }
            this.future = null;
            return this.model;
        }
        return AsyncGenericModelHolder.DUMMY;
    }
    
    public void clear() {
        super.clear();
        this.future = null;
    }
    
    protected GenericSmdModel loadModel() {
        return new GenericSmdModel(this.resource, false);
    }
    
    static {
        DUMMY = new DummyModel();
    }
    
    private static class DummyModel extends ModelBase
    {
    }
}
