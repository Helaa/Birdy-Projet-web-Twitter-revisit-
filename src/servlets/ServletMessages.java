package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.Messages;
import tools.ErrorJSON;

@WebServlet("/ServletMessages/*") 

public class ServletMessages extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			JSONObject jsonMessage = new JSONObject();
			String url = request.getPathInfo().replace("/","");
			
			String array[] = url.split("-");
			
			if(request.getPathInfo()==null) {
				jsonMessage.append("res", "null");
			}else {
				jsonMessage = Messages.listing(array[0]);
			}
			jsonMessage.put("url", url);
			print(jsonMessage, response);
		}catch(JSONException e) {
			e.printStackTrace();
		}
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String message = request.getParameter("messages");

		try {
			JSONObject json = Messages.createMessage( login, message);
			print(json, response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		JSONObject json = Messages.deleteMessages(login);
		print(json, response);
	}
	
	
	private void print(JSONObject json, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}
}
