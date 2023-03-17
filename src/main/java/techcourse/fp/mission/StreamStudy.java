package techcourse.fp.mission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStudy {

    private static final String WAR_AND_PEACE_PATH = "src/main/resources/techcourse/fp/war-and-peace.txt";

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
            .get(WAR_AND_PEACE_PATH));
        return Arrays.stream(contents.split("[\\P{L}]+"))
                .filter(w -> w.length() > 12)
                .count();

    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(num -> num * 2)
                .collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
//        return numbers.stream()
//                .mapToInt(num -> num)
//                .sum();
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) throws IOException {
        return numbers.stream()
                .filter(num -> num > 3)
                .map(num -> (long)num * 2)
                .reduce(0L, Long::sum);
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
            .get(WAR_AND_PEACE_PATH));
        Arrays.stream(contents.split("[\\P{L}]+"))
                .distinct()
                .filter(word -> word.length() > 12)
                .sorted(Comparator.comparing(String::length).reversed())
                .map(String::toLowerCase)
                .limit(100)
                .forEach(System.out::println);


    }
}
