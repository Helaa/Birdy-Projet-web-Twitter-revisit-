package test;

import java.sql.SQLException;

import org.json.JSONException;

import com.mysql.cj.Session;
import tools.ErrorJSON;
import tools.SessionTools;

public class TestSession {
	public static void main(String[] args)throws SQLException, JSONException {
		String login = "louty";
		String password = "mdppostcorona";
		services.Session.login(login, password);
		String key=services.Session.getKey(login);
		//System.out.println("keyyyyyyyyyyyyyy "+ key );
		//SessionTools.deleteSession(login, key);
		//SessionTools.insertSession(login);
		services.Session.logout(login, password, key);

		

		//String key=ErrorJSON.getKeyOfSession();
		//Authentification.logout(login, password, key);

	}
}
