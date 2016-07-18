package next.controller.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Result;

public class DelAnswerController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		AnswerDao answerDao = new AnswerDao(); 
		
		Result result = answerDao.delete(Long.parseLong(req.getParameter("answerId")));
		
		ObjectMapper mapper = new ObjectMapper(); 
		resp.setContentType("application/json;charset=UTF-8"); 
		PrintWriter out = resp.getWriter(); 
		out.print(mapper.writeValueAsString(result));
		
		return null;
	}
	
}
