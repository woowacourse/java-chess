package techcourse.fp.mission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
            .get("C:\\Users\\user\\Desktop\\우테코\\레벨 1\\체스\\java-chess\\src\\main\\resources\\techcourse\\fp\\war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        return words.stream()
                .filter(w -> w.length() > 12)
                .count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> number * 2)
                .collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(number -> number)
                .sum();
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream()
                .filter(x -> x > 3)
                .map(x -> x * 2)
                .reduce(0, (x, y) -> x + y);
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
            .get("C:\\Users\\user\\Desktop\\우테코\\레벨 1\\체스\\java-chess\\src\\main\\resources\\techcourse\\fp\\war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

//        System.out.println(words);
//        System.out.println(words.size());
        // TODO 이 부분에 구현한다.
        words.stream()
                .filter(w -> w.length() > 12)
                .map(x -> x.toLowerCase())
                .distinct()
                .sorted(Comparator.comparing(String::length).reversed())
                .limit(100)
                .forEach(System.out::println);
    }
}
