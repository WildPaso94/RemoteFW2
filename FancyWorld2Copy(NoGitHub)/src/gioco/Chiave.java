package gioco;

public class Chiave {
	
	int keyID;
	
	public Chiave(int keyID)
	{
		this.keyID = keyID;
	}
	
	public void stampaChiave()
	{
		System.out.println("Chiave Tipo--> " + this.keyID);
	}
	
	public int getKeyID()
	{
		return this.keyID;
	}

}