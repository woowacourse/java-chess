package techcourse.fp.mission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStudy {

    public static final String FILE_DIR = "src/main/resources/techcourse/fp/war-and-peace.txt";

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
            .get(FILE_DIR));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        return words.stream()
            .filter((a) -> a.length() > 12)
            .count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
            .map(n -> n * 2)
            .collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream()
            .filter(n -> n > 3)
            .mapToInt(n -> n * 2)
            .sum();
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
            .get(FILE_DIR));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"))
            .stream()
            .filter(w -> w.length() > 12)
            .distinct()
            .sorted(Comparator.comparingInt(String::length).reversed())
            .limit(100)
            .map(String::toLowerCase)
            .collect(Collectors.toList());

        for (String word : words) {
            System.out.println(word);
        }
        System.out.println(words.size());
    }
}
