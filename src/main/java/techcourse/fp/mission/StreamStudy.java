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

    private static String contents;


    static {
        try {
            contents = Files.readString(
                    Paths.get("src/main/resources/techcourse/fp/war-and-peace.txt"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static long countWords()  {
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        return words.stream()
                .filter(m -> m.length() > 12)
                .count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(m -> m * 2)
                .collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {

        return numbers.stream()
                .filter(m -> m > 3)
                .reduce(Integer::sum)
                .get() * 2;
    }

    public static void printLongestWordTop100() throws IOException {
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        final List<String> collectWords = words.stream()
                .filter(m -> m.length() > 12)
                .map(String::toLowerCase)
                .distinct()
                .limit(100)
                .sorted(Comparator.comparing(String::length).reversed())
                .collect(Collectors.toList());

        System.out.println(collectWords);
        System.out.println(collectWords.size());
    }
}
