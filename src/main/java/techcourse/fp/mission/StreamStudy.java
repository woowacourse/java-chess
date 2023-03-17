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
        return words.stream().filter(word -> word.length() > 12).count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream().map(number -> number * 2).collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream().filter(number -> number > 3).mapToInt(number -> number * 2).sum();
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
            .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        List<String> longestWordTop100 = words.stream()
            .sorted((o1, o2) -> o2.length() - o1.length())
            .distinct()
            .limit(100)
            .map(String::toLowerCase)
            .collect(Collectors.toList());
        System.out.println(longestWordTop100);
        System.out.println(longestWordTop100.size());
    }
}
