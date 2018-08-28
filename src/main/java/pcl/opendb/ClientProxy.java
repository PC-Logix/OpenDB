package pcl.opendb;


import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import pcl.opendb.CommonProxy;
import pcl.opendb.tileentity.PrinterTE;
import pcl.opendb.tileentityrender.PrinterRenderer;
import pcl.opendb.itemrender.ItemPrinterRenderer;

public class ClientProxy extends CommonProxy {
	
	public void registerRenderers()
	{
		if (OpenDB.render3D) {
			TileEntitySpecialRenderer printerRenderer = new PrinterRenderer();
			ClientRegistry.bindTileEntitySpecialRenderer(pcl.opendb.tileentity.PrinterTE.class, printerRenderer);
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ContentRegistry.printerBlock), new ItemPrinterRenderer(printerRenderer, new PrinterTE()));
		}
	}
	
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te != null && te instanceof PrinterTE)
        {
        	PrinterTE icte = (PrinterTE) te;
            //return new PrinterContainer(player.inventory, icte);
        }
        else
        {
            return null;
        }
		return null;
    }
	
}