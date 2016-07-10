package next;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DataBase;
import model.User;

/**
 * Servlet implementation class UserCreateServlet
 */
@WebServlet("/user/create")
public class UserCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory.getLogger(UserCreateServlet.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		User user = new User(id, pw, name, email);		// 160710__한글깨짐. 원인은??
		logger.debug(user.toString());
		DataBase.addUser(user);
		DataBase.addUser(new User("kkang18x", "1234", "남상칠", "kkang18x@nate.com"));
		
//		response.sendRedirect("/index.html");
		request.setAttribute("users", DataBase.findAll());							// 헤더에?? 추가되는 내용??
		RequestDispatcher rd = request.getRequestDispatcher("/user/list.jsp");
		rd.forward(request, response);
	}

}
