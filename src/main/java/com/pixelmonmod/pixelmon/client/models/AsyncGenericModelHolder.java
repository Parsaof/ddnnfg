//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings".
//Decompiled by Procyon!

package com.pixelmonmod.pixelmon.client.models;

import net.minecraft.client.model.ModelBase;
import com.pixelmonmod.pixelmon.client.models.blocks.GenericSmdModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;

import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncGenericModelHolder extends ModelHolder<ModelBase> {

    public static final ModelBase DUMMY;
    private Future<GenericSmdModel> future;
    private final ResourceLocation resource;

    public AsyncGenericModelHolder(final ResourceLocation model) {
        this.resource = model;
    }

    public void render() {
        this.getModel().render((Entity) null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 1.0f);
    }

    public void render(final float scale) {
        this.getModel().render((Entity) null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, scale);
    }

    public ModelBase getModel() {
        this.lastAccess = Instant.now().getEpochSecond();

        if (this.model != null) {
            return this.model;
        }

        if (this.future == null) {
            // démarre le chargement asynchrone
            this.future = ResourceLoader.addTask((Callable<GenericSmdModel>) this::loadModel);
        }

        if (this.future.isDone()) {
            try {
                // récupère le modèle SMD et l'utilise comme ModelBase
                this.model = this.future.get();
                PixelmonModelHolder.loadedHolders.add(this);
            } catch (InterruptedException e) {
                // restaure le flag d'interruption et rend le dummy pour ce tick
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                // log cause réelle
                e.getCause().printStackTrace();
            } finally {
                this.future = null;
            }
            return this.model != null ? this.model : DUMMY;
        }

        // encore en chargement
        return DUMMY;
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

    private static class DummyModel extends ModelBase {
        // ModelBase a une implémentation vide de render() en 1.12, rien à ajouter ici
    }
}