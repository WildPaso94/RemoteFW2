package gioco;

import java.io.File;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Fancy World - Release 1
 * @author Luca Bolpagni, Lorenzo Pasini, Davide Faccioli
 *
 * Tramite questa classe viene caricato, da una file in formato xml, il mondo con i rispettivi livelli 
 * Dopo un messaggio di benvenuto il giocatore può incominciare ad interagire con l'applicazione digitando i movimenti che vuole effettuare
 * per raggiungere il goal.
 */
public class Main {
	public static void main(String[] args) {
		//Commento delle 17:24
		final String messaggioBenvenuto = "Benvenuto! Ti trovi in un dungeon, il tuo scopo è raggiungere la casella Goal, che rappresenta la via di fuga. Buona fortuna!";
		final String messaggioViaDiFuga = "La via di fuga si trova al Livello 10!";
		final String messaggioNSEOUD = "Dove vuoi andare? (N/S/E/O/U/D) (0 per uscire e concludere il gioco)";
		final String messaggioNSEOUDDeposito = "Dove vuoi andare? (N/S/E/O/U/D) Oppure puoi depositare una delle tue chiavi con il tasto P! (0 per uscire e concludere il gioco)";
		final String messaggioCarattereValido = "Inserire un carattere valido!";
		final String messaggioIronico = "††††† Pfffff, tanto non avresti mai trovato l'uscita! †††††";
		final String messaggioComplimenti = "Congratulazioni hai vinto!";
		
		Player giocatore = null;
 		Scanner in = new Scanner(System.in).useDelimiter(System.getProperty("line.separator"));
 		String direzione;
 		String risposta;
 		
 		
 		try 
 		{

 			File file = new File("bin/Risorse/FancyWorld.xml");
 			JAXBContext jaxbContext = JAXBContext.newInstance(Mondo.class);

 			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
 			Mondo mondo = (Mondo) jaxbUnmarshaller.unmarshal(file);
 			System.out.println(mondo.toString());
 			giocatore = new Player (0, 0, 1, mondo);
		
 			System.out.println(messaggioBenvenuto);
			System.out.println(messaggioViaDiFuga);
 			do 
 			{
 				Cella cella = mondo.getLivello(giocatore.livello).cellaDaPos(giocatore.x, giocatore.y);
 				
 				
 				
 				
 				System.out.println("Ti trovi in: "+ cella.getName() + '[' + giocatore.x + ',' + giocatore.y + ']' + " Livello: " + giocatore.livello);
 				
 				if (cella.getChiave() != 0)
 				{
 					System.out.println("C'è una chiave!!!-->Tipo " + cella.getChiave());
 					System.out.println("Vuoi raccoglierla? (Y/N)");
 					boolean exit = true;
 					
 					do
 					{
 						risposta = in.next();
	 					if(risposta.equalsIgnoreCase("Y"))
	 					{
	 						//Poi dobbiamo aggiungere la raccolta della chiave.
	 						giocatore.aggiungiChiave(cella.getChiave());
	 						cella.setChiave(0);
	 						exit = false;
	 					}
	 					else if(risposta.equalsIgnoreCase("N"))
	 					{
	 						System.out.println("Chiave NON raccolta");
	 						exit = false;
	 					}
	 					else
	 					{
	 						System.out.println("Inserisci un carattere valido");
	 					}
 					} while (exit);
 				}
 				/*if (!giocatore.chiaviPossedute.isEmpty() && cella.getGoal() == 0 && cella.getStart() == 0 && cella.getChiave() == 0)
 					System.out.println(messaggioNSEOUDDeposito);
 				else*/
 					System.out.println(messaggioNSEOUD);
 				//PROVA PER VEDERE DOVE SONO LE CHIAVI E I PASSAGGI BLOCCATI (cancellare in seguito)\/
 				//System.out.println(cella.getChiave()+"<--Valore chiave");   
 				//System.out.println(cella.getcloseEst()+"<--Valore getCloseEst");
 				//PROVA PER VEDERE DOVE SONO LE CHIAVI E I PASSAGGI BLOCCATI (cancellare in seguito)/\
 				direzione = in.next();
 				
 				if (direzione.equalsIgnoreCase("N") || direzione.equalsIgnoreCase("S") || direzione.equalsIgnoreCase("E") || direzione.equalsIgnoreCase("O") || direzione.equalsIgnoreCase("U") ||direzione.equalsIgnoreCase("D") || direzione.equalsIgnoreCase("0"))
 				{
 					giocatore.movimento(direzione);
 				}
 					else 
 					{
 					System.out.println(messaggioCarattereValido);
 					}
 			}while(!giocatore.controllaVittoria() && !giocatore.returnExit());
 			
 		 	if(giocatore.returnExit())
 		 	{
 		 		System.out.println(messaggioIronico);
 		 	}
 		 	else
 		 	{
 		 		System.out.println(messaggioComplimenti);
 		 	}
 		} catch (JAXBException e) {
		e.printStackTrace();
	  }
	}	
}