package ru.aivanitskiy.hw05;

public class Main {
    public static void main(String[] args) {
        String className = TestClass.class.getName();

        try {
            Tester tester = new Tester(className);
            tester.test();
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + className + " not found");
        } catch (TestConfigurationException e) {
            System.out.println("Error test configuration: " + e.getMessage());
        }
    }
}
