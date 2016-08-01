package core.ref;

import java.lang.reflect.Method;

import org.junit.Test;

public class Junit4TestRunner {
	@Test
	public void run() throws Exception {
		Class<Junit4Test> clazz = Junit4Test.class;
		
		Junit4Test instance = clazz.getConstructor().newInstance();
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			if (m.isAnnotationPresent(MyTest.class)) {
				m.invoke(instance);
			}
		}
	}
}
