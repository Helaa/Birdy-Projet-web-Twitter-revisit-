package test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import services.User;
import tools.SessionTools;
import tools.UserTools;

public class TestUser {
	public static void main(String[] args) throws SQLException, JSONException {
		String login = "helamn ";
		String password = "mdppostCorona";
		String nom = "Menzli";
		String prenom = "Hela";
		String mail="mn.hela14@mail@com";
		
		System.out.println("** La liste de tous les utilsateurs avant l'insertion de " + login+": **");
		User.getUsersList();   
		
		//creation d'un user
		User.createUser(login, nom, prenom, password, mail);
		
		System.out.println("\n** La liste de tous les utilsateurs aprés l'insertion de " + login+": **");
		User.getUsersList();   
		
		System.out.println("\n** La récuperation des données publiques de " + login+": **");
		User.deleteUser(login);
		
		System.out.println("\n** La liste de tous les utilsateurs aprés la supression de " + login+": **");
		User.getUsersList();
		


	}
} 

