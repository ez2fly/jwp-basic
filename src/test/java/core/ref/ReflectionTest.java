package core.ref;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;

public class ReflectionTest {
	private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
	
	@Test
	public void showClass() {
		Class<Question> clazz = Question.class;
		Field[] fields = clazz.getDeclaredFields();
		Constructor[] constructors = clazz.getConstructors();
		Method[] methods = clazz.getMethods();
		
		logger.debug("Class Name : " + clazz.getName());
		logger.debug("Field : ");
		for (Field f : fields) {
			logger.debug(f.toString());
		}
		logger.debug("Constructor : ");
		for (Constructor c : constructors) {
			logger.debug(c.toString());
		}
		logger.debug("Method : ");
		for (Method m : methods) {
			logger.debug(m.toString());
		}
	}
}
