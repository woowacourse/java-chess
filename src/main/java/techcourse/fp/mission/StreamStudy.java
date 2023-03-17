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

    private static String FILE_PATH = "src/main/resources/techcourse/fp/war-and-peace.txt";

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
                .get(FILE_PATH));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        return words.stream()
                .filter(world -> world.length() > 12)
                .count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();

        return result.stream()
                .map(number -> number * 2)
                .collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream()
                .reduce(Integer::sum)
                .get();
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number > 3)
                .map(number -> number * 2)
                .reduce(Integer::sum)
                .get();
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get(FILE_PATH));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"))
                .stream()
                .filter(s -> s.length() > 12)
                .distinct()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(100)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        System.out.println(words);
        System.out.println(words.size());
    }
}
