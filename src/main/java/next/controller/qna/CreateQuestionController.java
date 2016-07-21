package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.Result;

public class CreateQuestionController extends AbstractController {

	private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		Question question = new Question(request.getParameter("writer")
				, request.getParameter("title")
				, request.getParameter("contents"));
		log.debug("new question : {}", question);
				
		questionDao.insert(question);
		return  jspView("redirect:/");
	}

}
