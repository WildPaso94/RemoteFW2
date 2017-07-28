package gioco;

import java.util.ArrayList;

/**
 *
 * Questa classe rappresenta il giocatore di Fancy World
 * 
 * @author Luca Bolpagni, Lorenzo Pasini, Davide Faccioli
 *
 */
public class Player {
	
	final String messaggioChiuso = "Il passaggio è chiuso a chiave!!!";
	final String messaggioBloccato = "Il passaggio e' bloccato da un muro, scegli un'altra direzione";
	final String messaggioServeChiave = "Serve la chiave di tipo -->";
	
	int x;
	int y;
	int livello;
	Mondo mondo;
	boolean exit = false;
	
	ArrayList<Chiave> chiaviPossedute = new ArrayList<Chiave>();
	
	/**
	 * Un giocatore ï¿½ caratterizzato dai seguenti parametri
	 * 
	 * @param x; coordinata x della posizione del giocatore
	 * @param y; coordinata y della posizione del giocatore
	 * @param livello; il livello al quale il giocatore si trova
	 * @param mondo; il mondo che il giocatore si trova ad esplorare
	 */
	public Player (int x, int y, int livello, Mondo mondo){
		this.x = x;
		this.y = y;
		this.livello = livello;
		this.mondo = mondo;
	}
	
	public void aggiungiChiave(int keyID)
	{
		Chiave chiaveDaAggiungere = new Chiave(keyID);
		this.chiaviPossedute.add(chiaveDaAggiungere);
		System.out.println("Chiave raccolta!");
		System.out.println("Chiavi in tuo possesso:");
		for(Chiave c : chiaviPossedute)
			c.stampaChiave();
	}
	
	/**
	 * Metodo che controlla se un giocatore ha vinto
	 * @return una variabile booleana che esprime il fatto che il giocatore ha raggiunto il goal
	 */
	public boolean controllaVittoria (){
		boolean vinto = false;
		Cella cella = mondo.getLivello(livello).cellaDaPos(x, y);
		if (cella.goal == 1){
			vinto = true;
		}
		return vinto;
	}
	
	/**
	 * Metodo che permette il movimento del giocatore all'interno del mondo e quindi all'interno di ogni livello
	 * @param direzione verso la quale il giocatore vuole spostarsi
	 */
	public void movimento (String direzione){
		
		Cella cella = mondo.getLivello(livello).cellaDaPos(x, y);
		
		if (movimentoValido(x, y, livello, direzione)){
			if(direzione.equalsIgnoreCase("n")){
				y = y - 1;
			}
			else if(direzione.equalsIgnoreCase("s")){
				y = y + 1;
			}
			else if(direzione.equalsIgnoreCase("e")){
				x = x + 1;
			}
			else if(direzione.equalsIgnoreCase("o")){
				x = x - 1;
			}
			else if(direzione.equalsIgnoreCase("u")){
				livello = livello + 1;
			}
			else if(direzione.equalsIgnoreCase("d")){
				livello = livello - 1;
			}
		}
		else if (!movimentoValido(x, y, livello, direzione) && cella.getcloseEst()!=0 && direzione.equalsIgnoreCase("e") && controlloChiave(cella.getcloseEst()))
		{
			//Qui dovremo modificare closeEst da n a 0
			System.out.println("CONGRATULAZIONI! Hai aperto il passaggio usando la chiave --> " + cella.getcloseEst());
			cella.setcloseEst(0);
			x = x + 1;
		}
		else if (!movimentoValido(x, y, livello, direzione) && cella.getcloseOvest()!=0 && direzione.equalsIgnoreCase("o") && controlloChiave(cella.getcloseOvest()))
		{
			System.out.println("CONGRATULAZIONI! Hai aperto il passaggio usando la chiave --> " + cella.getcloseOvest());
			cella.setcloseOvest(0);
			x = x - 1;
		}
		else if (!movimentoValido(x, y, livello, direzione) && cella.getcloseNord()!=0 && direzione.equalsIgnoreCase("n") && controlloChiave(cella.getcloseNord()))
		{
			System.out.println("CONGRATULAZIONI! Hai aperto il passaggio usando la chiave --> " + cella.getcloseNord());
			cella.setcloseNord(0);
			y = y - 1;
		}
		else if (!movimentoValido(x, y, livello, direzione) && cella.getcloseSud()!=0 && direzione.equalsIgnoreCase("s") && controlloChiave(cella.getcloseSud()))
		{
			System.out.println("CONGRATULAZIONI! Hai aperto il passaggio usando la chiave --> " + cella.getcloseSud());
			cella.setcloseSud(0);
			y = y + 1;
		}
		else if (!movimentoValido(x, y, livello, direzione) && cella.getcloseEst()!=0 && direzione.equalsIgnoreCase("e"))
			System.out.println(messaggioChiuso + "\n" + messaggioServeChiave + cella.getcloseEst());
		else if (!movimentoValido(x, y, livello, direzione) && cella.getcloseOvest()!=0 && direzione.equalsIgnoreCase("o"))
			System.out.println(messaggioChiuso + "\n" + messaggioServeChiave + cella.getcloseOvest());
		else if (!movimentoValido(x, y, livello, direzione) && cella.getcloseSud()!=0 && direzione.equalsIgnoreCase("s"))
			System.out.println(messaggioChiuso + "\n" + messaggioServeChiave + cella.getcloseSud());
		else if (!movimentoValido(x, y, livello, direzione) && cella.getcloseNord()!=0 && direzione.equalsIgnoreCase("n"))
			System.out.println(messaggioChiuso + "\n" + messaggioServeChiave + cella.getcloseNord());
		else {
			System.out.println(messaggioBloccato);
		}
	}
	//Usato per confrontare le chiavi del giocatore con la chiave che blocca il passaggio (True, il giocatore possiede la chiave)!!!
	public boolean controlloChiave(int chiavePassaggio)
	{
		for(Chiave c : chiaviPossedute)
		{
			if(c.getKeyID()==chiavePassaggio)
				return true;
		}
		return false;
	}
	
	/**
	 * Metodo che controlla se la prossima direzione che il giocatore vuole percorrere ï¿½ consentita
	 * @param x; coordinata x della posizione del giocatore
	 * @param y; coordinata y della posizione del giocatore
	 * @param livello; il livello al quale il giocatore si trova
	 * @param direzione verso la quale il giocatore ï¿½ intenzionato a spostarsi
	 * @return
	 */
	private boolean movimentoValido (int x, int y, int livello, String direzione){
		boolean valido = false;
		Cella cella = mondo.getLivello(livello).cellaDaPos(x, y);
		if(direzione.equalsIgnoreCase("n") && cella.getcloseNord()==0){
			if (cella.n == 1)
				valido = true;
		}
		else if(direzione.equalsIgnoreCase("s") && cella.getcloseSud()==0){
			if (cella.s == 1)
				valido = true;
		}
		else if(direzione.equalsIgnoreCase("e") && cella.getcloseEst()==0){
			if (cella.e == 1)
				valido = true;
		}
		else if(direzione.equalsIgnoreCase("o") && cella.getcloseOvest()==0){
			if (cella.o == 1)
				valido = true;
		}
		else if(direzione.equalsIgnoreCase("u")){
			if (cella.up == 1)
				valido = true;
		}
		else if(direzione.equalsIgnoreCase("d")){
			if (cella.down == 1)
				valido = true;
		}
		else if(direzione.equalsIgnoreCase("0")){
			this.exit = true;
			valido = true;
		}
		return valido;
	}	
	
	/**
	 * Metodo che ritorna un tipo booleano
	 */
	public boolean returnExit() {
		return this.exit;
	}
	
}