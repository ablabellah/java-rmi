package classapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
/*Dans ce code, nous avons une implémentation de serveur 
 pour un annuaire telephonique . le serveur est conçu pour stocker
 les entrées de l'annuaire dans un fichier texte 'data.txt'
 Le serveur est basé sur la technologie Rmi de java qui permets aux applications 
 de communiquer à travers un réseau 
 */
public class AnnuaireServerImpl extends UnicastRemoteObject implements AnnuaireServer {

    private final static String FILE_DIRECTORY = "Annuaire";
	private final static String FILE_NAME = "data.txt";
    private static final long serialVersionUID = 1L;

    public AnnuaireServerImpl() throws RemoteException {
		super();
	}

    public static void main(String[] args) {
        try {// créer uneninstance de 'AnnuaireServerImpl'
        	AnnuaireServerImpl server = new AnnuaireServerImpl();
			
			//créer un registre RMI pour gérer les appels à distance 
			// définir le port d'écoute du registre rmi
            Registry registry = LocateRegistry.createRegistry(5099);

            Context namingContext = new InitialContext();
			//définit le nom de l'objet distant et son adresse IP
			//lié l'annuaire à un nom dans le registre RMI
            namingContext.bind("rmi://192.168.1.121:5099/Annuaire", server);
			
			System.out.println("Server started...");
        } catch (RemoteException | NamingException ex) {
            System.err.println(ex);
        }
    }
	// La méthode getPhoneBook lit les entrées de l'annuaire à partir du fichier texte 
	//et les retourne sous forme de liste d'objets 'AnnuaireEntree'
	public ArrayList<AnnuaireEntree> getPhoneBook() throws RemoteException {
		ArrayList<AnnuaireEntree> listPhones = new ArrayList<AnnuaireEntree>();
		try {

			File file = new File(FILE_DIRECTORY, FILE_NAME);
			Scanner s = new Scanner(file);

			while (s.hasNextLine()) {
				String data = s.nextLine();
				
				AnnuaireEntree phone = generateObject(data);
				listPhones.add(phone);
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return listPhones;
	}
	//cette methode est pour ajouter une nouvelle entrée au repertoire telephonique 
	public void addEntry(AnnuaireEntree entry) throws RemoteException {
		try {
			/*création de l'instance FilWriter en utlisant 
			 * le nom de fichier et le chemin d'acces
			 * l'argument true pour le constructeur de FilWriter 
			 * signifie que le fichier sera ouvert en mode append au lieu d'être remplacé 
			 */
            FileWriter writer = new FileWriter(new File(FILE_DIRECTORY, FILE_NAME), true);
			// BufferedWriter est créé pour effectuer des écritures dans le fichiers			
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
			//l'appel de la methode generateData
            bufferedWriter.write(generateData(entry));
			bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/*la m"thode 'generateData' es utilisée pour convertir une chaine 
	lue de fichier téléphonique en un objet 'AnnuaireEntree' Cela 
	permet de créer une liste d'objets 'AnnuaireEntree' représentant
	le contenu complet du fichier téléphonique  
	 */
	private static String generateData(AnnuaireEntree phone) {
		return phone.getName() + "&" + phone.getLastname() + "&" + phone.getPhone() + "\n";
	}
	/* La méthode generateObject est utilisée pour convertir un objet 
	 * AnnuaireEntree en chaine , qui peut ensuite être écrit 
	 * dans le fichier téléphonique
	*/
	private static AnnuaireEntree generateObject(String stringPhone) {
		String[] fields = stringPhone.split("&");
		AnnuaireEntree phone = new AnnuaireEntree(
			fields[0], fields[1], fields[2]
		);
		return phone;
	}
}
