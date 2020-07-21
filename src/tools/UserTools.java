package tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dataBase.Database;

public class UserTools {
	
	public static boolean existUser(String login) throws SQLException {
		Connection co= Database.getMySQLConnection();

		String query= "SELECT * FROM `user` WHERE login= '"+ login +"';";
		java.sql.Statement pst= co.createStatement();
		//pst.setString(1, login);
		//System.out.println("am in usereeToolsss 1");
		ResultSet res= pst.executeQuery(query);
		int id = -1 ;
		while(res.next()) {
			id = res.getInt("id");
		}
		//System.out.println("am in usereeToolsss" + id);

		res.close();
		co.close();
		return id!=-1;	
	}
	
	public static boolean checkPassword(String login, String pwd) throws SQLException {
		Connection co= Database.getMySQLConnection();

		String query= "select * from user where login="+ login +" and pwd =" + pwd +" ;";
			/*PreparedStatement pst= co.prepareStatement(query);
			pst.setString(1, login);
			pst.setString(2, pwd);
			ResultSet res= pst.executeQuery(query);
			pst.close();
			res.close();
			co.close();
			return true; */
		System.out.println("psssssssssssssssssssssss");
		Statement st = co.createStatement();
		System.out.println("avnt l res=");

		ResultSet res=st.executeQuery(query);
		System.out.println("avant le pwd ok");
		boolean passwordOK = res.next();
		res.close();
		st.close();
		co.close();
		System.out.println(passwordOK);
		return passwordOK;
		
	}
	
	
	public static void insertUser(String login, String nom, String prenom, String pwd, String mail) {
		try {
			Connection co= Database.getMySQLConnection();
			String query = "INSERT INTO `user` (`login`, `nom`, `prenom`, `pwd`, `mail`, `id`) VALUES"
					+ "('"+login+"',"
							+ " '"+nom+"',"
							+ " '"+prenom+"', '"
							+ pwd+"', '"
							+ mail+"',"					
							+ "NULL);";
			java.sql.Statement st = co.createStatement();
			st.executeUpdate(query);
			st.close();
			co.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public static void deleteUser(String login) {
		try {
			Connection co= Database.getMySQLConnection();
			String query= "DELETE FROM `user` WHERE `id`=" + getIdFromLogin(login) +";";
			Statement st= co.createStatement();
			st.executeUpdate(query);
			st.close();
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public static int getIdFromLogin(String login) throws SQLException {
		Connection co= Database.getMySQLConnection();
		String query= "SELECT `id` FROM `user` WHERE `login`= '" + login +"';";
		Statement st= co.createStatement();
		ResultSet res= st.executeQuery(query);
		res.next();
		int id= res.getInt("id");
		st.close();
		res.close();
		co.close();
		return id;
	}
	
	public static int getIdFromKey(String key) throws SQLException {
		Connection co= Database.getMySQLConnection();
		String query="SELECT `id` FROM `session` WHERE `key`='" + key +"';" ;
		Statement st= co.createStatement();
		ResultSet res= st.executeQuery(query);
		res.next();
		int id= res.getInt("id");
		st.close();
		res.close();
		co.close();
		return id;
	}
	
	public static JSONArray getAllUsers() throws SQLException, JSONException{
		Connection co= Database.getMySQLConnection();
		String query= "SELECT `nom`, `prenom` FROM `user` ";
		Statement st= co.createStatement();
		ResultSet res= st.executeQuery(query);
		JSONArray usersList=new JSONArray();
		String nom, prenom;
		while( res.next()) {
			nom= res.getString("nom");
			prenom=res.getString("prenom");
			usersList.put(new JSONObject().put("nom", nom).put("prenom", prenom));
		}
		st.close();
		res.close();
		co.close();
		return usersList;
	}
	
	public static JSONArray getUser(int id) throws SQLException, JSONException {
		Connection co= Database.getMySQLConnection();
		
		String query = "select prenom, nom from user where id =  ?";
		PreparedStatement pst = co.prepareStatement(query);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		rs.next();
		JSONArray userInfo = new JSONArray();
		String nom = rs.getString("nom");
		String prenom = rs.getString("prenom");
		userInfo.put(new JSONObject().put("nom", nom).put("prenom", prenom));

		rs.close();
		pst.close();
		co.close();
		System.out.println(userInfo);
		return userInfo;
	}
	
	
	
}