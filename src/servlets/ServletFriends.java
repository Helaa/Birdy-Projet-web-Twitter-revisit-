
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;
import services.User;

@WebServlet("/ServletFriends/*") 

public class ServletFriends extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	public ServletFriends() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException { // throws ServletException, IOException {
		/* Acces aux parameteres */
		
		try{
			PrintWriter writer = response.getWriter();
			String conn = request.getPathInfo();
			String [] conn_param = conn.split("/"); 
			writer.println(services.Friend.getFriendsList(conn_param[1]));
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* Access to parameters */
		String myLogin = request.getParameter("myLogin");
		String friendLogin = request.getParameter("friendLogin");

		try {
			JSONObject json = Friend.addFriend(myLogin, friendLogin);
			print(json, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Access to parameters */
		String myLogin = request.getParameter("myLogin");
		String friendLogin = request.getParameter("friendLogin");

		try {
			JSONObject json = Friend.deleteFriend(myLogin, friendLogin);
			print(json, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void print(JSONObject json, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}
}




