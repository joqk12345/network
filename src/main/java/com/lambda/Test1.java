package com.lambda;

/**
 * @author joqk
 * @Date ${date}
 * @{description} xxxxx
 **/
public class Test1 {
    public static void main(String[] args) {

        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();
        //java8 之后
        new Thread(() ->{
            System.out.println("你好，tes");
        }).start();
    }
}
