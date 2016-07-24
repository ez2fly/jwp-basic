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

public class UpdateFormQuestionController extends AbstractController {

	private static final Logger log = LoggerFactory.getLogger(UpdateFormQuestionController.class);
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		Question question = questionDao.findById(Long.parseLong(request.getParameter("questionId")));
		log.debug("updateForm question : {}", question);
		
		ModelAndView mav = jspView("/qna/form.jsp");
		mav.addObject("updateQuestion", question);
		return mav;
	}

}
