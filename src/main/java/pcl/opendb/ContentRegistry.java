package pcl.opendb;

import pcl.opendb.blocks.BlockPrinter;
import pcl.opendb.tileentity.PrinterTE;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ContentRegistry {

	public static CreativeTabs creativeTab;
	public static Block printerBlock;

	private ContentRegistry() {
	}

	// Called on mod preInit()
	public static void preInit() {
		registerBlocks();
	}

	//Called on mod init()
	public static void init() {
		registerRecipes();
	}

	private static void registerBlocks() {
		//Register Blocks
		printerBlock = new BlockPrinter();
		GameRegistry.registerBlock(printerBlock, "openprinter.printer");

		GameRegistry.registerTileEntity(PrinterTE.class, "PrinterTE");
	}

	private static void registerRecipes() {
		ItemStack redstone		= new ItemStack(Items.redstone);
		ItemStack microchip		= li.cil.oc.api.Items.get("chip1").createItemStack(1);
		ItemStack pcb			= li.cil.oc.api.Items.get("printedCircuitBoard").createItemStack(1);

		GameRegistry.addRecipe(new ShapedOreRecipe( new ItemStack(printerBlock, 1), 
				"IRI",
				"MPM",
				"IRI",
				'I', "nuggetIron", 'R', redstone, 'M', microchip, 'P', pcb));
	}
}
