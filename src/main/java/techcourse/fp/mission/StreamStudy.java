package techcourse.fp.mission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths.get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        return words.stream()
                    .filter(it -> it.length() > 12)
                    .count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
                      .map(it -> it * 2)
                      .collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream()
                      .reduce(0, Integer::sum);
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {

        return numbers.stream()
                      .filter(it -> it > 3)
                      .map(it -> it * 2)
                      .reduce(0, Integer::sum);
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths.get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        System.out.println(words);
        System.out.println(words.size());

        words = words.stream()
                     .filter(it -> it.length() > 12)
                     .distinct()
                     .limit(100)
                     .sorted((it1, it2) -> it2.length() - it1.length())
                     .collect(Collectors.toList());

        System.out.println(words);
        System.out.println(words.size());
    }
}
