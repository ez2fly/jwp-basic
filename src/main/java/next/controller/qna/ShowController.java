package next.controller.qna;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class ShowController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(ShowController.class);
	private QuestionDao questionDao = new QuestionDao();
	private AnswerDao answerDao = new AnswerDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
		Long questionId = Long.parseLong(req.getParameter("questionId"));
		
		Question question = questionDao.findById(questionId);
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);
		
		// #13_질문 삭제 가능 여부체크
		boolean questionDeleteAvailable = true;
		for(Answer answer : answers) {
			if (question.getWriter() != answer.getWriter()) {
				questionDeleteAvailable = false;
				break;
			}
		}
		log.debug("questionDeleteAvailable : " + questionDeleteAvailable);
		
		ModelAndView mav = jspView("/qna/show.jsp");
		mav.addObject("question", question);
		mav.addObject("answers", answers);
		mav.addObject("questionDeleteAvailable", questionDeleteAvailable);
		return mav;
	}
}
