//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\thoma\OneDrive\Documents\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.cepera.pokedollsreforged.proxy;

import net.minecraft.creativetab.*;
import java.util.*;
import net.minecraft.block.*;
import me.cepera.pokedollsreforged.listeners.*;
import net.minecraft.item.*;
import net.minecraftforge.common.*;
import me.cepera.pokedollsreforged.tiles.*;
import net.minecraft.tileentity.*;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy
{
    public final CreativeTabs creativeTab;

    public CommonProxy() {
        this.creativeTab = new CreativeTabs("pokedolls") {
            ItemStack icon;

            public ItemStack createIcon() {
                if (this.icon == null) {
                    final Random r = new Random();
                    this.icon = new ItemStack(Item.getItemFromBlock((Block)RegistryListener.pokedolls.get(r.nextInt(RegistryListener.pokedolls.size()))));
                }
                return this.icon;
            }
        };
    }

    public void preinit(final FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register((Object)new RegistryListener());
        TileEntity.register("pokedollsreforged:pokedoll", (Class)TileEntityPokedoll.class);
    }

    public void init(final FMLInitializationEvent e) {
    }

    public void postinit(final FMLPostInitializationEvent e) {
    }

    public void registerPokedollItemModels() {
    }
}