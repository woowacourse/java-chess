package techcourse.fp.mission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        return words.stream()
                .filter(w -> w.length() > 12)
                .mapToInt(String::length)
                .reduce(0, (count, n) -> n + 1);
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(it -> it * 2)
                .collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, (result, number) -> result + number);
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number > 3)
                .map(number -> number * 2)
                .reduce(0, (total, number) -> total + number);
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        words = words.stream()
                .filter(w -> w.length() > 12)
                .distinct()
                .sorted((x, y) -> y.length() - x.length())
                .limit(100)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        System.out.println(words);
        System.out.println(words.size());
        // TODO 이 부분에 구현한다.
    }
}
