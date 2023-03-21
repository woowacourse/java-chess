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
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        return words.stream()
                .map(String::length)
                .filter(length -> length > 12)
                .count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> 2 * number)
                .collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream()
                .filter(word -> word > 3)
                .reduce(0, (a, b) -> a + b * 2);
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        System.out.println(words.size());

        words.stream()
                .filter(word -> word.length() > 12)
                .sorted((w1, w2) -> {
                    if (w1.length() > w2.length()) {
                        return 1;
                    } else if (w1.length() == w2.length()) {
                        return 0;
                    }
                    return -1;
                })
                .distinct()
                .limit(100)
                .map(String::toLowerCase)
                .forEach(System.out::println);
    }
}
