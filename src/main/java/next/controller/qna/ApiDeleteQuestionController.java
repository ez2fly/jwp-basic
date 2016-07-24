package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;

public class ApiDeleteQuestionController extends AbstractController {

	private static final Logger log = LoggerFactory.getLogger(ApiDeleteQuestionController.class);
	private QuestionDao questionDao = new QuestionDao();
	private AnswerDao answerDao = new AnswerDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		long questionId = Long.parseLong(request.getParameter("questionId"));
		
		ModelAndView mav = jsonView();
		try {
			questionDao.delete(questionId);
			answerDao.deleteByQuestionId(questionId);
			
			mav.addObject("result", Result.ok());
		} catch(DataAccessException e) {
			mav.addObject("result", Result.fail(e.getMessage()));
		}
		return mav;
	}

}
