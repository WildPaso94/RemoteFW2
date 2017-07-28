package gioco;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Questa classe rappresenta un livello 
 * Un livello è caratterizzato da un numero e da un insieme di celle in cui il giocatore si può muovere
 * 
 * @author Luca Bolpagni, Lorenzo Pasini, Davide Faccioli
 *
 */
@XmlRootElement (name = "livello")
public class Livello {

	int numero;
	ArrayList<Cella> celle = new ArrayList<Cella>();

	/**
	 * Metodo che permette di associare ad un livello il numero che lo identifica
	 * @param numero
	 */
	public void setNumero (int numero){
		this.numero = numero;
	}
	
	/**
	 * Metodo che permette di ottenere il numero relativo ad un livello
	 * @return numero del livello
	 */
	@XmlAttribute
	public int getNumero () {
		return numero;
	}
	
	/**
	 * Metodo che ritorna l'insieme delle caselle che caratterizzano un livello
	 * @return
	 */
	@XmlElement (name= "cella")
	public ArrayList<Cella> getList (){
		return celle;
	}
	
	/**
	 * Metodo che permette di inserire l'insieme delle celle che caratterizzano un livello
	 * @param celle
	 */
	public void setList (ArrayList<Cella> celle){
		this.celle = celle;
	}
	
	/**
	 * Metodo che permette di aggiungere una cella al livello
	 * @param cella
	 */
	public void aggiungiCella (Cella cella) {
		celle.add(cella);
	}
	
	/**
	 * Metodo che ritorna una cella in base alla sua posizione nel livello
	 * @param x; coordinata x della cella
	 * @param y; coordinata y della cella
	 * @return la cella corrispondente
	 */
	public Cella cellaDaPos (int x, int y){
			for (Cella c: celle){
				if(c.x == x && c.y == y){
					return c;
				}
			}
		return null;
	}
	
	/**
	 * Metodo che visualizza il livello ovvero il suo numero e le celle da cui è composto
	 */
	public String toString() {	
		return String.format("Livello %d:\n\t\t%s\n\t", numero, celleToString());
	}
	
	/**
	 * Metodo che visualizza una cella
	 */
	private String celleToString(){
		StringBuilder builder = new StringBuilder();
		for (Cella c : celle) {
			builder.append(c.toString());
		}
		return builder.toString();
	}
}