package com.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1.0-1.4 中的 java.lang.Thread
 * 5.0 中的 java.util.concurrent
 * 6.0 中的 Phasers 等
 * 7.0 中的 Fork/Join 框架
 * 8.0 中的 Lambda
 * @author joqk
 * @Date ${date}
 * @{description} xxxxx
 **/
public class StreamTest1 {

    public static void main(String[] args) {
     //流的构造函数
        Stream stream = Stream.of("丁彦雨航","阿卜杜沙拉木","方硕");

     //Arrays
        String [] strArray = new String[]{"科比","麦迪","哈登"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);

        List<String> list = Arrays.asList(strArray);
        stream = list.stream();
        stream.forEach(System.out::println);
//数值流的构造
        /*
        IntStream.of(new int[]{1,2,3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1,3).forEach(System.out::println);
    */
        // 流转换为其他数据结构
//        String[] strArray2 = (String[]) stream.toArray(String[]::new);

        //转换单词的大小写
        String[] wordList ={"jamse","tompson","linkedin","alphbet"};
        List<String> output = Arrays.stream(wordList).
                map(String::toUpperCase).
                collect(Collectors.toList());
        //循环展示结果
        output.forEach(System.out::println);
//    平方数
        List<Integer> nums = Arrays.asList(1,3,9);
        List<Integer> squareNums = nums.stream().map(n->n*n).collect(Collectors.toList());
        squareNums.forEach(System.out::println);
//   1对多
        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1),Arrays.asList(2,3),Arrays.asList(4,5,6));
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
        outputStream.forEach(System.out::println);

    }

}
