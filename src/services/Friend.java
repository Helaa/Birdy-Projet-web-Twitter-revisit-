package services;

import java.sql.SQLException;


import org.json.JSONException;
import org.json.JSONObject;

import dataBase.DBStatic;
import tools.ErrorJSON;
import tools.UserTools;
import tools.SessionTools;
import tools.FriendsTools ;

public class Friend {
	
	public static JSONObject addFriend(String myLogin, String friendLogin) throws SQLException {
		
		if (!SessionTools.verifySession(myLogin)) {
			return ErrorJSON.serviceRefused("Vous n'etes pas connectés!", DBStatic.notInDbError);
		}
		if (myLogin==null || friendLogin==null) {
			return ErrorJSON.serviceRefused("Champs manquant!", DBStatic.emptyCaseError);
		}
		if (!UserTools.existUser(myLogin) ||!UserTools.existUser(friendLogin) ) {
			return ErrorJSON.serviceRefused("User Inexistant!", DBStatic.notInDbError);
		}
		
		int myId= UserTools.getIdFromLogin(myLogin);
		int friendId= UserTools.getIdFromLogin(friendLogin);
		
		FriendsTools.addFriend(myId, friendId);
		FriendsTools.addFriend(friendId, myId);
		System.out.println("Now you're friends with " + friendLogin);
		return ErrorJSON.serviceAccepted("Now you're friends with " + friendLogin);
	}
	
	public static JSONObject deleteFriend(String myLogin, String friendLogin) throws SQLException {
		if (!SessionTools.verifySession(myLogin)) {
			return ErrorJSON.serviceRefused("Vous n'etes pas connectés!", DBStatic.notInDbError);
		}
		if (myLogin==null || friendLogin==null) {
			return ErrorJSON.serviceRefused("Champs manquants!", DBStatic.emptyCaseError);
		}
		if (! UserTools.existUser(friendLogin) || ! UserTools.existUser(myLogin) ) {
			return ErrorJSON.serviceRefused("User Inexistant!", DBStatic.notInDbError);
		}
		
		int myId= UserTools.getIdFromLogin(myLogin);
		int friendId= UserTools.getIdFromLogin(friendLogin);		
		
		FriendsTools.deleteFriend(myId, friendId);
		FriendsTools.deleteFriend(friendId, myId);
		System.out.println(friendLogin + " deleted succussefully.");
		return ErrorJSON.serviceAccepted(friendLogin + " deleted succussefully.");
	}
	
	
	public static JSONObject getFriendsList(String login) throws SQLException, JSONException {
		
		if (!SessionTools.verifySession(login)) {
			return ErrorJSON.serviceRefused("Vous n'etes pas connectés!", DBStatic.notInDbError);
		}
		JSONObject obj = new JSONObject();
		if (login==null) {
			return ErrorJSON.serviceRefused("Champs manquants!", DBStatic.emptyCaseError);
		}
		if (! UserTools.existUser(login) ) {
			return ErrorJSON.serviceRefused("User Inexistant!", DBStatic.notInDbError);
		}
		obj.put(login+" friends list : ", FriendsTools.getFriendsList(login));
		//System.out.println("Friends of " + login +": " + obj);
		return obj;
	}
	
	
	public static JSONObject getTheBestSuguestion(String login) throws SQLException, JSONException {
		if (login==null) {
			return ErrorJSON.serviceRefused("Champs manquants!" , DBStatic.emptyCaseError);
		}
		if (! UserTools.existUser(login)) {
			return ErrorJSON.serviceRefused("User Inexistant!", DBStatic.notInDbError);
		}
		FriendsTools.getTheBestSuggestion(login);
		return ErrorJSON.serviceAccepted();
	}
}