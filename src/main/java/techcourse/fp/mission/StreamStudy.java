package techcourse.fp.mission;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
            .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
         return Arrays.stream(contents.split("[\\P{L}]+"))
                .filter(StreamStudy::isOverThan12)
                .count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> number * 2)
                .collect(toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
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
        Arrays.stream(contents.split("[\\P{L}]+"))
                .filter(StreamStudy::isOverThan12)
                .distinct()
                .map(String::toLowerCase)
                .sorted(comparing(String::length).reversed())
                .limit(100)
                .forEach(System.out::println);
    }

    private static boolean isOverThan12(String word) {
        return word.length() > 12;
    }
}
