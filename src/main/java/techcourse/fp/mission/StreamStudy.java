package techcourse.fp.mission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
//        return words.stream()
//                .filter(w -> w.length() > 12)
//                .count();
        return words.stream()
                .mapToInt(String::length)// map으로 메서드 참조 해줌
                .filter(it -> it > 12)
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
                .filter(number -> number > 3)
                .mapToLong(number -> number * 2)
                .reduce(0, Long::sum);
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        words = words.stream()
                .filter(word -> word.length() > 12)
                .distinct()
                .sorted(Comparator.comparing(String::length).reversed())
                .limit(100)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        System.out.println(words);
        System.out.println(words.size());
    }
}
