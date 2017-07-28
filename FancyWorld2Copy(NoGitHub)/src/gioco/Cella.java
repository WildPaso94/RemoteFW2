package gioco;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Questa classe rappresenta una cella contenuta all'interno di un livello
 * Una cella può essere un passaggio oppure un luogo
 * Essa è caratterizata da un nome, da una posizione, da quattro lati e dal fatto di poter essere il goal oppure lo start
 * 
 * @author Luca Bolpagni, Lorenzo Pasini, Davide Faccioli
 *
 */
@XmlRootElement (name = "cella")
public class Cella {
	
	String name;
	int x;
	int y;
	int up;
	int down;
	int e;
	int o;
	int n;
	int s;
	int goal;
	int start;
	int chiave;
	int closeEst;
	int closeNord;
	int closeOvest;
	int closeSud;
	
	String type;
	/**
	 * Metodi Get e Set degli attributi della cella
	 * 
	 */
	@XmlElement
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}

	@XmlElement
	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public void setName(String name){
		this.name = name;
	}
	
	@XmlAttribute
	public String getName(){
		return name;
	}
	
	public void setX (int x){
		this.x = x;
	}
	
	@XmlElement
	public int getX (){
		return x;
	}

	public void setY (int y){
		this.y = y;
	}
	
	@XmlElement
	public int getY (){
		return y;
	}	
	
	public void setE (int e){
		this.e = e;
	}
	
	@XmlElement
	public int getE (){
		return e;
	}	
	
	public void setO (int o){
		this.o = o;
	}
	
	@XmlElement
	public int getO (){
		return o;
	}
	
	public void setN (int n){
		this.n = n;
	}
	
	@XmlElement
	public int getN (){
		return n;
	}
	
	public void setS (int s){
		this.s = s;
	}
	
	@XmlElement
	public int getS (){
		return s;
	}
	
	public void setUp (int up){
		this.up = up;
	}
	
	@XmlElement
	public int getUp (){
		return up;
	}
	
	public void setDown (int down){
		this.down = down;
	}
	
	@XmlElement
	public int getDown (){
		return down;
	}
	//Metodi per la gestione delle chiave e dei passaggi chiusi.
	@XmlElement
	public int getChiave() {
		return chiave;
	}
	
	public void setChiave(int chiave) {
		this.chiave = chiave;
	}
	
	@XmlElement
	public int getcloseEst() {
		return closeEst;
	}
	
	public void setcloseEst(int closeEst) {
		this.closeEst = closeEst;
	}
	
	@XmlElement
	public int getcloseOvest() {
		return closeOvest;
	}
	
	public void setcloseOvest(int closeOvest) {
		this.closeOvest = closeOvest;
	}
	
	@XmlElement
	public int getcloseNord() {
		return closeNord;
	}
	
	public void setcloseNord(int closeNord) {
		this.closeNord = closeNord;
	}
	
	@XmlElement
	public int getcloseSud() {
		return closeSud;
	}
	
	public void setcloseSud(int closeSud) {
		this.closeSud = closeSud;
	}
	
	/**
	 * Metodo che permette di visualizzare una cella
	 */
	public String toString() {
		return String.format("[%s: (x= %d, y= %d, up= %d, down= %d, n= %d, s= %d, e= %d, o= %d)]\n\t\t", name, x, y, up, down, n, s, e, o);
	}
}