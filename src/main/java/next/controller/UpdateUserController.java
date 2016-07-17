package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import core.mvc.Controller;
import next.model.User;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = DataBase.findUserById(req.getParameter("userId"));
		if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
			throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
		}
		
		User updateUser = new User(
				req.getParameter("userId"), 
				req.getParameter("password"), 
				req.getParameter("name"),
				req.getParameter("email"));
		log.debug("Update User : {}", updateUser);
		DataBase.updateUser(updateUser);
		
		user = UserSessionUtils.getUserFromSession(req.getSession());		// 160717_kju : 세션의 user 정보도 변경해준다.	
		user.update(updateUser);
		return "redirect:/";
	}
}
