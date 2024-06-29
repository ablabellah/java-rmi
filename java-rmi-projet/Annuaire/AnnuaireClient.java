package classapp;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AnnuaireClient {
//Le code ci-dessus crée un client pour un annuaire téléphonique en utilisant
//le modéle de programmation RMI

    public static void main(String[] args) throws MalformedURLException {

        try {
            //Création d'un objet Context pour établir la connexion avec le serveur.
            Context namingContext = new InitialContext();
            //Utilisation de la méthode lookup pour récupérer l'objet AnnuaireServer depuis le serveur distant.
            AnnuaireServer stub = (AnnuaireServer) namingContext.lookup("rmi://192.168.1.121:5099/Annuaire");

            //Création d'un objet Scanner pour lire les données saisies par l'utilisateur.
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the first name: ");
            String firstName = scanner.nextLine();
            
            System.out.println("Enter the last name: ");
            String lastName = scanner.nextLine();
            
            System.out.println("Enter the phone number: ");
            String phoneNumber = scanner.nextLine();
            //Récupération des informations sur le contact à ajouter dans l'annuaire.
            AnnuaireEntree newEntry = new AnnuaireEntree(firstName, lastName, phoneNumber);
            scanner.close();
            //Appel de la méthode addEntry sur l'objet AnnuaireServer pour ajouter la nouvelle entrée dans l'annuaire.
            stub.addEntry(newEntry);
            //Appel de la méthode getPhoneBook sur l'objet AnnuaireServer pour récupérer la liste des entrées dans l'annuaire.
            ArrayList<AnnuaireEntree> listPhoneBook = stub.getPhoneBook();
            
            //Affichage de la liste des entrées dans l'annuaire.
            System.out.println("List of registered telephone numbers: ");
            for (AnnuaireEntree AnnuaireEntree : listPhoneBook) {
                System.out.println(AnnuaireEntree.toString());
            }
        } catch (RemoteException | NamingException ex) {
            System.err.println(ex);
        }
    }
}
