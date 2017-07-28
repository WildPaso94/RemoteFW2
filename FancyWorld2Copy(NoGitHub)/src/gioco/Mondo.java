package gioco;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Questa classe rappresenta il mondo in cui è ambientata l'applicazione Fancy World 
 * Il mondo è caratterizzato da un nome e da una serie di livelli
 * 
 * @author Luca Bolpagni, Lorenzo Pasini, Davide Faccioli
 *
 */
@XmlRootElement (name = "mondo")
public class Mondo {
	String name;
	ArrayList<Livello> livelli = new ArrayList<Livello>();
	
	
	/**
	 * Metodo Set che imposta il nome del mondo
	 * @param name
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * Metodo Get che ritorna il nome del mondo
	 * @return il nome del mondo
	 */
	@XmlAttribute
	public String getName (){
		return name;
	}

	/**
	 * Metodo che ritorna la lista dei livelli presenti nel mondo
	 * @return lista di livelli
	 */
	@XmlElement(name= "livello")
	public ArrayList<Livello> getList (){
		return livelli;
	}
	
	/**
	 * Metodo che permette di inserire i livelli all'interno del mondo 
	 * @param lista di livelli da inserire
	 */
	public void setList (ArrayList<Livello> livelli){
		this.livelli = livelli;
	}
	
	/**
	 * Metodo che ritorna un livello in base al suo numero passato come parametro
	 * @param numero del livello 
	 * @return livello specifico
	 */
	public Livello getLivello (int numero){
		for (Livello l: livelli){
			if(l.numero == numero){
				return l;
			}
		}
	return null;
	}
	
	/**
	 * Metodo che visualizza il mondo ovvero il suo nome e i livelli da cui è composto
	 */
	public String toString() {
		return String.format("%s:\n\t%s", name, livelloToString());
	}
	
	/**
	 * Metodo che visualizza un livello
	 */
	private String livelloToString(){
		StringBuilder builder = new StringBuilder();
		for (Livello l : livelli) {
			builder.append(l.toString());
		}
		return builder.toString();
	}
}