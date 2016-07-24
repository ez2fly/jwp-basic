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

public class DeleteQuestionController extends AbstractController {

	private static final Logger log = LoggerFactory.getLogger(DeleteQuestionController.class);
	private QuestionDao questionDao = new QuestionDao();
	private AnswerDao answerDao = new AnswerDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		long questionId = Long.parseLong(request.getParameter("questionId"));
		questionDao.delete(questionId);
		answerDao.deleteByQuestionId(questionId);
		
		return jspView("redirect:/");
	}

}
