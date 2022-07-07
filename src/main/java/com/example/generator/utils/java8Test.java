package com.example.generator.utils;

import com.example.generator.model.SysUser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.regex.Pattern.*;

/**
 * Java8  常用方法测试
 */
public class java8Test {


    public static void test() throws FileNotFoundException {
        /**
         * 流的创建------Start---------------
         */
        //1.使用Collection下的 stream() 和 parallelStream() 方法
        List<String> list = new ArrayList<>();
        //获取一个顺序流
        Stream<String> stream = list.stream();
        //获取一个并行流
        Stream<String> parallelStream = list.parallelStream();


        //2.使用Arrays 中的 stream() 方法，将数组转成流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream2 = Arrays.stream(nums);

        //3.使用Stream中的静态方法：of()、iterate()、generate()
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2).limit(6);
        stream2.forEach(System.out::println);
        // 0 2 4 6 8 10

        Stream<Double> stream5 = Stream.generate(Math::random).limit(2);
        stream3.forEach(System.out::println);


        //4.使用 BufferedReader.lines() 方法，将每行内容转成流
        BufferedReader reader = new BufferedReader(new FileReader("F:\\test_stream.txt"));
        Stream<String> lineStream = reader.lines();
        lineStream.forEach(System.out::println);

        //5.使用 Pattern.splitAsStream() 方法，将字符串分隔成流
        Pattern pattern = compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);
        /**
         * 流的创建------end---------------
         */


        /**
         * 流的中间操作------start--------------
         * filter()：返回结果生成新的流中只包含满足筛选条件的数据。
         * distinct()：数据去重
         * skip(n)：将前几个元素跳过（取出）再返回一个流，如果流中的元素小于或者等于n，就会返回一个空的流
         * limit(n)：返回指定数量的元素的流。返回的是stream里前面的n个元素
         */

        Stream<Integer> m1 = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
        System.out.println("----------------------------------");
        m1.filter(s -> s > 8||s<5) //6 6 7 9 8 10 12 14 14
             //   .distinct() //6 7 9 8 10 12 14
              //  .skip(2) //9 8 10 12 14
              //  .limit(2) //9 8
                .forEach(System.out::println);

        //map()：接收一个函数作为参数，将流中的每一个元素通过此函数映射为新的元素，并作为新流中对应的元素
        Stream<Integer> m2 = Stream.of(1, 2, 3);
        m2.map(a -> a * a).forEach(System.out::print); // 1  4  9

        //flatMap()：将流中的每个元素都放到一个流中，最后将所有的流合并成一个新流，所有流对象中的元素都合并到这个新生成的流中返回
        List<SysUser> s1 = new ArrayList<>();
        List<SysUser> s2 = new ArrayList<>();
        List<SysUser> s3 = new ArrayList<>();

        List<SysUser> list1 = Stream.of(s1, s2, s3).flatMap(Collection::stream).collect(Collectors.toList());


        //sorted()：自然排序，流中元素需实现Comparable接口
        //sorted(Comparator com)：定制排序，自定义Comparator排序器
        SysUser u1 = new SysUser();
        u1.setName("小王");
        u1.setId(1);
        SysUser u2 = new SysUser();
        u2.setName("小赵");
        u2.setId(2);
        SysUser u3 = new SysUser();
        u3.setName("小黄");
        u3.setId(3);
        List<SysUser> users = Arrays.asList(u1, u2, u3);
        users.stream().sorted(
                (o1, o2) -> {
                    if (o1.getName().equals(o2.getName())) {
                        return o1.getId() - o2.getId();
                    } else {
                        return o1.getName().compareTo(o2.getName());
                    }
                }
        ).forEach(System.out::print);


        //peek()：对流中每个元素执行操作，并返回一个新的流，返回的流还是包含原来流中的元素
        //与map类似，但是map有返回值，peek无返回值。
        Stream<Integer> p1 = Stream.of(1, 2, 3);
        p1.peek(System.out::print).count();

        /**
         * 流的中间操作------end--------------
         */

        /**
         * 流的终止操作------start--------------
         */

        //forEach()：内部迭代
        //forEachOrdered()
        //串行流中 都是按顺序输出，并行流中forEachOrdered才会严格按照顺序输出
        List<String> strs = Arrays.asList("a", "b", "c");
        strs.stream().forEachOrdered(System.out::print);//abc
        System.out.println();
        strs.stream().forEach(System.out::print);//abc
        System.out.println();
        strs.parallelStream().forEachOrdered(System.out::print);//abc
        System.out.println();
        strs.parallelStream().forEach(System.out::print);//bca


        //toArray()  将流转换为字符串数组
        // 将字符串流转换为字符串数组
        List<String> listS = Arrays.asList("A", "B", "C", "D");

        // 将整数流转换为整数数组
        List<Integer> listI = Arrays.asList(1, 3, 2, 4);
        Integer[] sArray = listI.stream().toArray(Integer[]::new);


        //reduce()：可以将流中元素反复结合起来，得到一个值。
        List<Integer> listR = Arrays.asList(1,2,3,4);
        // reduce()方法中第一个参数是起始值,第二个参数Lambda表达式中第一个参数x就是起始值,lambda表达式第二个参数y就是集合中的每个值
        // 遍历集合中每个参数作为y,然后进行计算(x+y) 得到结果作为x,最后将所有结果相加,得到sum
        Integer sum = listR.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum); // 10


         sum = listR.stream().reduce((x, y) -> x + y).get();
        System.out.println(sum); // 10

        //allMatch()：检查是否匹配所有元素
        //noneMatch()：检查是否没有匹配所有元素
        //anyMatch()：检查是否至少匹配一个元素
        //findFirst()：返回第一个元素
        //findAny()：返回当前流中的任意元素
        //count()：返回流中总数
        //max()：返回流中最大值
        //min()：返回流中最小值
        List<Integer> listM = Arrays.asList(1, 2, 3, 4, 5);

        boolean allMatch = listM.stream().allMatch(e -> e > 10); //false
        boolean noneMatch = listM.stream().noneMatch(e -> e > 10); //true
        boolean anyMatch = listM.stream().anyMatch(e -> e > 4);  //true
        Integer findFirst = listM.stream().findFirst().get(); //1
        Integer findAny = listM.stream().findAny().get(); //1

        long count = listM.stream().count(); //5
        Integer max = listM.stream().max(Integer::compareTo).get(); //5
        Integer min = listM.stream().min(Integer::compareTo).get(); //1


        //collect操作
        //collect()：将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
        // 第一个数组没有重复元素，第二个数组有重复元素
        List<String> listC = Arrays.asList("A", "B", "C", "D");
        List<String> dlistC = Arrays.asList("A", "A", "C", "D");

        //1.Collectors.toList()
        List<String> listResult = listC.stream().collect(Collectors.toList());
        System.out.println(listResult); // [A, B, C, D]

        //2. Collectors.toSet()  去重了
        Set<String> setResult = listC.stream().collect(Collectors.toSet());
        System.out.println(setResult);  // [A, B, C, D]

        Set<String> dsetResult = dlistC.stream().collect(Collectors.toSet());
        System.out.println(dsetResult); // [A, C, D]

        //3. Collectors.toCollection()
        //上面的toMap,toSet转换出来的都是特定的类型，如果我们需要自定义，则可以使用toCollection()
        List<String> custListResult = list.stream().collect(Collectors.toCollection(LinkedList::new));
         // class java.util.LinkedList
        System.out.println(custListResult.getClass());

        //4.Collectors.toMap()
        //toMap接收两个参数，第一个参数是keyMapper，第二个参数是valueMapper：
        //如果stream中有重复的值，则转换会报IllegalStateException异常，在toMap中添加第三个参数mergeFunction，来解决冲突的问题。
        Map<String, Integer> mapResult = listC.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(mapResult);		// {A=1, B=1, C=1, D=1}


        Map<String, Integer> dMapResult = dlistC.stream()
                .collect(Collectors.toMap(Function.identity(), String::length, (item, identicalItem) -> item));
        System.out.println(dMapResult);		// {A=1, C=1, D=1}

        //5. Collectors.collectingAndThen()
        //collectingAndThen允许我们对生成的集合再做一次操作。

        List<String> collectAndThenResult = listC.stream()
                        .collect(Collectors.collectingAndThen(Collectors.toList(), l -> {return new ArrayList<>(l);}));
        System.out.println(collectAndThenResult);	// [A, B, C, D]

        //6.Collectors.joining()：joining用来连接stream中的元素：
        String joinResult = listC.stream().collect(Collectors.joining());
        System.out.println(joinResult);	// ABCD
        String joinResult1 = listC.stream().collect(Collectors.joining(" "));
        System.out.println(joinResult1);// A B C D
        String joinResult2 = listC.stream().collect(Collectors.joining(" ", "E","F"));
        System.out.println(joinResult2);// EA B C DF


        //7. Collectors.counting()
        //counting主要用来统计stream中元素的个数

        //8. Collectors.summarizingDouble/Long/Int()
        //summarizingDouble/Long/Int为stream中的元素生成了统计信息，返回的结果是一个统计类：

        IntSummaryStatistics intResult = listC.stream()
                .collect(Collectors.summarizingInt(String::length));
        System.out.println(intResult);	// IntSummaryStatistics{count=4, sum=4, min=1, average=1.000000, max=1}

        //9. Collectors.averagingDouble/Long/Int()
        //averagingDouble/Long/Int()对stream中的元素做平均

        //10. Collectors.summingDouble/Long/Int()
        //summingDouble/Long/Int()对stream中的元素做sum操作

       // 11. Collectors.maxBy()/minBy()
        //maxBy()/minBy()根据提供的Comparator，返回stream中的最大或者最小值

        Optional<String> maxByResult = listC.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
        System.out.println(maxByResult);	// Optional[D]


        //12. Collectors.groupingBy()
        //GroupingBy根据某些属性进行分组，并返回一个Map
        Map<Integer, Set<String>> groupByResult = listC.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        System.out.println(groupByResult);	// {1=[A, B, C, D]}


        //13. Collectors.partitioningBy()
        //PartitioningBy是一个特别的groupingBy，PartitioningBy返回一个Map，这个Map是以boolean值为key，从而将stream分成两部分，一部分是匹配PartitioningBy条件的，一部分是不满足条件的

        Map<Boolean, List<String>> partitionResult = listC.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 3));
        System.out.println(partitionResult); // {false=[A, B, C, D], true=[]}


    }




}
