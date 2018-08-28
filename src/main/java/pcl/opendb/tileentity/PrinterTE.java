/**
 * 
 */
package pcl.opendb.tileentity;

import pcl.opendb.OpenDB;
import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Component;
import li.cil.oc.api.network.ComponentConnector;
import li.cil.oc.api.network.Environment;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.network.Message;
import li.cil.oc.api.network.Node;
import li.cil.oc.api.network.Visibility;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Caitlyn
 *
 */
public class PrinterTE extends TileEntity implements Environment {

	public Double PrinterFormatVersion = 2.0;
	protected ComponentConnector node = Network.newNode(this, Visibility.Network).withComponent(getComponentName()).withConnector(32).create();
	protected boolean addedToNetwork = false;

	public PrinterTE() {
		if (this.node() != null) {
			initOCFilesystem();
		}
	}

	private String getComponentName() {
		// TODO Auto-generated method stub
		return "openprinter";
	}

	//private li.cil.oc.api.network.ManagedEnvironment oc_fs;

	private Object oc_fs;
	protected ManagedEnvironment oc_fs(){
		return (ManagedEnvironment) this.oc_fs;
	}

	private void initOCFilesystem() {
		oc_fs = li.cil.oc.api.FileSystem.asManagedEnvironment(li.cil.oc.api.FileSystem.fromClass(OpenDB.class, OpenDB.MODID, "/lua/printer/"), "printer");
		((Component) oc_fs().node()).setVisibility(Visibility.Network);
	}

	@Override
	public Node node() {
		return node;
	}

	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		if (node != null)
			node.remove();
	}

	@Override
	public void invalidate() {
		super.invalidate();
		if (node != null)
			node.remove();
	}

	@Override
	public void onConnect(final Node node) {
		if (node == node()) {
			node.connect(oc_fs().node());
		}
	}

	@Override
	public void onDisconnect(final Node node) {
		if (node.host() instanceof Context) {
			node.disconnect(oc_fs().node());
		} else if (node == this.node) {
			oc_fs().node().remove();
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if(!addedToNetwork) {
			addToNetwork();
		}
	}

	protected void addToNetwork() {
		if(!addedToNetwork) {
			addedToNetwork = true;
			Network.joinOrCreateNetwork(this);
		}
	}

	@Override
	public net.minecraft.network.Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}

	@Callback
	public Object[] greet(Context context, Arguments args) {
		return new Object[] { "Lasciate ogne speranza, voi ch'intrate" };
	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}
}
