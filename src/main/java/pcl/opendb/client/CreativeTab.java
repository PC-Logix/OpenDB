package pcl.opendb.client;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import pcl.opendb.ContentRegistry;
import pcl.opendb.OpenDB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs {
	public CreativeTab(String unlocalizedName) {
		super(unlocalizedName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Item.getItemFromBlock(ContentRegistry.printerBlock);
	}
}