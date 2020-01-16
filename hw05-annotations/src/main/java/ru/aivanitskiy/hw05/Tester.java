package ru.aivanitskiy.hw05;

import java.lang.reflect.InvocationTargetException;
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
       initTestMethods();
    }

    private void initTestMethods() throws TestConfigurationException {
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

        try {
            for (Method method: testedMethods) {
                Object testedObject = testedClazz.getDeclaredConstructor().newInstance();

                try {
                    runBefore(testedObject);

                    try {
                        method.invoke(testedObject);
                        successCount++;
                    } catch (Throwable e) {
                        System.out.println("Error: method " + method.getName());
                    }
                } finally {
                    runAfter(testedObject);
                }
            }
        } catch (Throwable e) {
            successCount = 0;
            System.out.println("Error: can't test it");
        }

        System.out.println(successCount + " / " + testedMethods.size());
    }

    private void runBefore(Object testedObject) throws InvocationTargetException, IllegalAccessException {
        if(before.isPresent()) {
            before.get().invoke(testedObject);
        }
    }

    private void runAfter(Object testedObject) throws InvocationTargetException, IllegalAccessException {
        if(after.isPresent()) {
            after.get().invoke(testedObject);
        }
    }
}
