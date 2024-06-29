package classapp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
// Définition de l'interface AnnuaireServer pour décrire les méthodes RMI
public interface AnnuaireServer extends Remote {
    // Méthode pour récupérer la liste des entrées dans l'annuaire
    public ArrayList<AnnuaireEntree> getPhoneBook() throws RemoteException;

        // Méthode pour ajouter une nouvelle entrée dans l'annuaire

    public void addEntry(AnnuaireEntree entry) throws RemoteException;
    
}

	

