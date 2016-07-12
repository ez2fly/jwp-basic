package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;


/**
 * Servlet implementation class UpdateUserFormServlet
 */
@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(CreateUserServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userid");
		if (userId != null) {
			request.setAttribute("user", DataBase.findUserById(userId));
		} else {
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("user");
			if (obj != null) {
				request.setAttribute("user", (User)obj);
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/user/update.jsp");
        rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User(
                req.getParameter("userId"), 
                req.getParameter("password"), 
                req.getParameter("name"),
                req.getParameter("email"));
        log.debug("modify user : {}", user);
        DataBase.modifyUser(user);
        
        resp.sendRedirect("/user/list");
	}
	
	
}
