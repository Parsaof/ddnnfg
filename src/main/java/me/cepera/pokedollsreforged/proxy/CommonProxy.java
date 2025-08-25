package me.cepera.pokedollsreforged.proxy;

import net.minecraft.creativetab.CreativeTabs;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import me.cepera.pokedollsreforged.listeners.RegistryListener;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import me.cepera.pokedollsreforged.tiles.TileEntityPokedoll;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class CommonProxy {
    public final CreativeTabs creativeTab;

    public CommonProxy() {
        this.creativeTab = new CreativeTabs("pokedolls") {
            ItemStack icon;

            public ItemStack createIcon() {
                if (this.icon == null) {
                    final Random r = new Random();
                    // Vérifier qu'il y a des blocs dans la liste avant de choisir un index aléatoire
                    if (RegistryListener.pokedolls != null && !RegistryListener.pokedolls.isEmpty()) {
                        this.icon = new ItemStack(Item.getItemFromBlock((Block)RegistryListener.pokedolls.get(r.nextInt(RegistryListener.pokedolls.size()))));
                    } else {
                        // Fallback si la liste est vide (ne devrait pas arriver normalement)
                        this.icon = ItemStack.EMPTY;
                    }
                }
                return this.icon;
            }
        };
    }

    public void preinit(final FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new RegistryListener());
        TileEntity.register("pokedollsreforged:pokedoll", TileEntityPokedoll.class);
    }

    public void init(final FMLInitializationEvent e) {
        // Initialisation commune
    }

    public void postinit(final FMLPostInitializationEvent e) {
        // Post-initialisation commune
    }

    public void registerPokedollItemModels() {
        // Implémentation vide, sera override dans ClientProxy
    }
}