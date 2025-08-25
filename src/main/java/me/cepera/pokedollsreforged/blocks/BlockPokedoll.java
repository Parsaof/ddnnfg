package me.cepera.pokedollsreforged.blocks;

import com.pixelmonmod.pixelmon.blocks.GenericRotatableModelBlock;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;
import me.cepera.pokedollsreforged.tiles.TileEntityPokedoll;
import me.cepera.pokedollsreforged.PokedollsReforged;

public class BlockPokedoll extends GenericRotatableModelBlock {
    private EnumSpecies pokemon;
    private BlockCustom customBlock;
    private boolean shiny;
    private boolean isCustom;

    // Constructeur pour les Pokémon
    public BlockPokedoll(EnumSpecies pokemon, boolean shiny) {
        super(Material.CLOTH);
        this.pokemon = pokemon;
        this.shiny = shiny;
        this.isCustom = false;
        this.init(pokemon.name.toLowerCase().replace("-", ""), true);
    }

    // Constructeur pour les blocs custom
    public BlockPokedoll(BlockCustom customBlock) {
        super(Material.CLOTH);
        this.customBlock = customBlock;
        this.isCustom = true;
        this.shiny = false;
        this.init(customBlock.getName(), false);
    }

    private void init(String baseName, boolean isPokemon) {
        String name = isPokemon ? "pokedoll_" + baseName : "custom_" + baseName;
        this.setTranslationKey(name);
        this.setHardness(1.0f);
        this.setCreativeTab(PokedollsReforged.PROXY.creativeTab);
        this.setSoundType(SoundType.CLOTH);
    }

    public enum BlockCustom {
        SEWING_MACHINE("sewing_machine", "Sewing Machine"),
        OAK_TABLE("oak_table", "Oak Table"),
        POKECENTER_BENCH("pokecenter_bench", "Pokecenter Bench");

        private final String name;
        private final String displayName;

        BlockCustom(String name, String displayName) {
            this.name = name;
            this.displayName = displayName;
        }

        public String getName() { return name; }
        public String getDisplayName() { return displayName; }
    }

    public EnumSpecies getPokemon() {
        return this.pokemon;
    }

    public BlockCustom getCustomBlock() {
        return this.customBlock;
    }

    public boolean isShiny() {
        return this.shiny;
    }

    public boolean isCustom() {
        return this.isCustom;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        if (this.isCustom) {
            return new TileEntityPokedoll(this.customBlock);
        } else {
            return new TileEntityPokedoll(this.pokemon, this.shiny);
        }
    }
}