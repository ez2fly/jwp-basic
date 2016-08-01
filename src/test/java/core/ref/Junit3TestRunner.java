package core.ref;

import java.lang.reflect.Method;

import org.junit.Test;

public class Junit3TestRunner {
	@Test
	public void run() throws Exception {
		Class<Junit3Test> clazz = Junit3Test.class;
		
		Junit3Test instance = clazz.getConstructor().newInstance();
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			if (m.getName().startsWith("test")) {
				m.invoke(instance);
			}
		}
	}
}
