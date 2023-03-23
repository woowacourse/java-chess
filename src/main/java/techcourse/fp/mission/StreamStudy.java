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
        final String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        return words.stream()
                .filter(word -> word.length() > 12)
                .count();
    }

    public static List<Integer> doubleNumbers(final List<Integer> numbers) {
        return numbers.stream()
                .map(number -> number * 2)
                .collect(Collectors.toList());
    }

    public static long sumAll(final List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }

    public static long sumOverThreeAndDouble(final List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number > 3)
                .map(number -> number * 2)
                .reduce(0, Integer::sum);
    }

    public static void printLongestWordTop100() throws IOException {
        final String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        System.out.println(words);
        System.out.println(words.size());

        //words.stream()
        //        .filter(word -> word.length() > 12)
        //        .sorted(Comparator.comparing(String::length).reversed())
        //        .limit(100)
        //        .distinct()
        //        .map(String::toLowerCase)
        //        .forEach(System.out::println);

        words.stream()
                .distinct()
                .filter(word -> word.length() > 12)
                .sorted(Comparator.comparing(String::length).reversed())
                .map(String::toLowerCase)
                .limit(100)
                .forEach(System.out::println);
    }
}
