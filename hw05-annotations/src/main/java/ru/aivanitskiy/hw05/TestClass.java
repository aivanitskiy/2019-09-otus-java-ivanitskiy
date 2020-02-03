package ru.aivanitskiy.hw05;

public class TestClass {

    @Test
    public void test1()
    {
        System.out.println("Testing 1");
    }

    @Test
    public void test2()
    {
        System.out.println("Testing 2");
    }

    @Test
    public void test3()
    {
        System.out.println("Testing 3");
        throw new RuntimeException();
    }

    @Test
    public void test4()
    {
        System.out.println("Testing 4");
    }


    @Before
    public void before()
    {
        System.out.println("Before");
    }

    @After
    public void after()
    {
        System.out.println("After");
    }
}
