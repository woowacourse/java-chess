package techcourse.fp.mission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        String[] words = contents.split("[\\P{L}]+");

        return Arrays.stream(words)
                .filter(word -> word.length() > 12)
                .count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> number * 2)
                .collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream()
                .map(Integer::longValue)
                .reduce(Long::sum)
                .orElseThrow(() -> new IllegalArgumentException("값이 없음"));
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number > 3)
                .map(number -> number * 2)
                .map(Integer::longValue)
                .reduce(Long::sum)
                .orElseThrow(() -> new IllegalArgumentException("값이 없음"));
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        words.stream()
                .filter(word -> word.length() > 12)
                .sorted((word1, word2) -> Integer.compare(word2.length(), word1.length()))
                .distinct()
                .limit(100)
                .map(String::toLowerCase)
                .forEach(System.out::println);
    }
}
