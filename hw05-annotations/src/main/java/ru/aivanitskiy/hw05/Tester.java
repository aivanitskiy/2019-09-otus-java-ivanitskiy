package ru.aivanitskiy.hw05;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tester {
    private Class<?> testedClazz;
    private Optional<Method> before = Optional.empty();
    private Optional<Method> after = Optional.empty();
    private List<Method> testedMethods = new ArrayList<>();

    public Tester(String className) throws ClassNotFoundException, TestConfigurationException {
       this.testedClazz = Class.forName(className);

        for (Method method: testedClazz.getDeclaredMethods()) {
            if(method.isAnnotationPresent(Test.class)) {
                testedMethods.add(method);
            }

            if(method.isAnnotationPresent(Before.class)) {
                if(before.isPresent()) {
                    throw new TestConfigurationException("@Before method must be the only one");
                }
                before = Optional.of(method);
            }

            if(method.isAnnotationPresent(After.class)) {
                if(after.isPresent()) {
                    throw new TestConfigurationException("@After method must be the only one");
                }
                after = Optional.of(method);
            }
        }
    }

    public void test() {
        int successCount = 0;
        for (Method method: testedMethods) {
            try {
                var o = testedClazz.getDeclaredConstructor().newInstance();

                if(before.isPresent()) {
                    before.get().invoke(o);
                }

                method.invoke(o);

                if(after.isPresent()) {
                    after.get().invoke(o);
                }
                successCount++;
            } catch (Exception e) {
                System.out.println("Error: method " + method.getName());
            }
        }

        System.out.println(successCount + " / " + testedMethods.size());
    }
}
