package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class ApiAddAnswerController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(ApiAddAnswerController.class);

	private AnswerDao answerDao = new AnswerDao();
	private QuestionDao questionDao = new QuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
		Answer answer = new Answer(req.getParameter("writer"), 
				req.getParameter("contents"), 
				Long.parseLong(req.getParameter("questionId")));
		log.debug("answer : {}", answer);
		
		long questionId = answer.getQuestionId();
		Question question = questionDao.findById(questionId);
		if (question != null) {
			int count = question.getCountOfComment() + 1;
			questionDao.updateCountOfAnswer(questionId, count);
			log.debug("update answer count : " + count);
		}
		
		Answer savedAnswer = answerDao.insert(answer);
		return jsonView().addObject("answer", savedAnswer);
	}
}