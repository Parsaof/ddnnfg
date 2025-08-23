//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\thoma\OneDrive\Documents\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.cepera.pokedollsreforged;

import org.apache.logging.log4j.*;
import me.cepera.pokedollsreforged.proxy.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = "pokedollsreforged", name = "PokedollsReforged", version = "1.2.1", dependencies = "required-after:pixelmon@[8.3.0,]", acceptedMinecraftVersions = "[1.12.2]")
public class PokedollsReforged
{
    public static final String MODID = "pokedollsreforged";
    public static final String MODNAME = "PokedollsReforged";
    public static final String VERSION = "1.2.1";
    @Mod.Instance
    public static PokedollsReforged INSTANCE;
    private Logger logger;
    @SidedProxy(clientSide = "me.cepera.pokedollsreforged.proxy.ClientProxy", serverSide = "me.cepera.pokedollsreforged.proxy.CommonProxy")
    public static CommonProxy PROXY;
    
    public Logger getLogger() {
        return this.logger;
    }
    
    @Mod.EventHandler
    private void preinit(final FMLPreInitializationEvent e) {
        this.logger = e.getModLog();
        PokedollsReforged.PROXY.preinit(e);
    }
    
    @Mod.EventHandler
    private void init(final FMLInitializationEvent e) {
        PokedollsReforged.PROXY.init(e);
    }
    
    @Mod.EventHandler
    private void postinit(final FMLPostInitializationEvent e) {
        PokedollsReforged.PROXY.postinit(e);
    }
}
