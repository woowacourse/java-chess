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

        // TODO Arrays.stream <-> Arrays.asList().stream ?
        return Arrays.stream(contents.split("[\\P{L}]+"))
                .filter(word -> word.length() > 12)
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
                .filter(number -> number > 3)
                .mapToLong(number -> 2L * number)
                .reduce(0, Long::sum);
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));

        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        List<String> result = words.stream()
                .filter(word -> word.length() > 12)
                .distinct()
                .sorted(Comparator.comparing(String::length).reversed())
                .limit(100)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
