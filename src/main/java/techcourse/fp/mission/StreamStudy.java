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

        return words.stream().filter(word -> word.length() > 12).count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
            .mapToInt(number -> number * 2)
            .boxed()
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
            .mapToInt(number -> number * 2)
            .sum();
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
            .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        // TODO 이 부분에 구현한다.
        words.stream()
            .filter(word -> word.length() > 12)
            .sorted(Comparator.comparingInt(String::length))
            .distinct()
            .limit(100)
            .forEach(word -> System.out.println(word.toLowerCase()));
    }
}
