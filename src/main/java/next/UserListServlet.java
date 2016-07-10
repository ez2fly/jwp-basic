package next;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DataBase;
import model.User;
import util.IOUtils;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/user/list")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(UserListServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		boolean logined = false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				logger.debug("[Cookie] Name : " + cookie.getName() + ", Value : " + cookie.getValue());
				if ("logined".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
					logined = true;
					break;
				}
			}
		} else {
			logger.debug("It's not contained cookie");
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		boolean doParse = false;
		List<String> lines = IOUtils.readFileLines("./webapp/user/list.html");
		for (String line : lines) {
			if (doParse == false) {
				out.println(line);
			}
			
			String tag = line.trim();
			if ("<tbody>".equals(tag)) {
				doParse = true;
			}
			else if ("</tbody>".equals(tag)) {
				if (logined) {
					printUserTable(out);
				}
				out.println("</tbody>");
				doParse = false;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	private void printUserTable(PrintWriter out) {
		int i = 0;
		Collection<User> users = DataBase.findAll();
		for (User user : users) {			
			out.println("<tr>");
			StringBuilder sb = new StringBuilder();
			sb.append("<th scope=\"row\">" + ++i + "</th>");
			sb.append(" <td>" + user.getUserId() + "</td>");
			sb.append(" <td>" + user.getName() + "</td>");
			sb.append(" <td>" + user.getEmail() + "</td>");
			sb.append(" <td><a href=\"#\" class=\"btn btn-success\" role=\"button\">수정</a></td>");
			out.println(sb.toString());
			out.println("</tr>");
		}
	}

}
