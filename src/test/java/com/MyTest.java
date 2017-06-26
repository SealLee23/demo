package com;



import org.junit.*;

import java.util.Random;
import static java.lang.System.out;

/**
 * Created by xiaozli on 2017/6/26.
 */
public class MyTest {
    private static Random r;

    @BeforeClass
    public static void init(){
        System.out.println("Init...");
        r = new Random();
        System.out.println("Init Done!");
    }

    @AfterClass
    public static void destroy(){
        System.out.println("Destroy...");
        System.out.print("Destroy Done!");
    }

    @Before
    public void initTestDate(){
        System.out.println("Before test");
    }

    @After
    public void destoryTestData(){
        System.out.println("After test");
    }

    @Test
    public void test01() throws InterruptedException{
        int interval = r.nextInt(10);
        System.out.println("Interval 1: "  +  interval);
        Thread.currentThread().sleep(interval*1000);
        out.println("TEST1");
    }
    @Test
    public void test02() throws InterruptedException{
        int interval = r.nextInt(10);
        System.out.println("Interval 2: "  +  interval);
        Thread.currentThread().sleep(interval*1000);
        out.println("TEST2");
    }
}
