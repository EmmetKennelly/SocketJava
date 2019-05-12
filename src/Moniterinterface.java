
public interface Moniterinterface
{

	
	public String[] getNames ();
	public boolean hasFile (String name);
	public byte[] download (String name);
	public String share(String name, byte [] bytes);
	public void attach(Observer o);
	public void deattach(Observer o);
}
