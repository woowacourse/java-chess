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
        String contents = Files.readString(Paths.get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        return words.stream().filter(s -> s.length() > 12).count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream().map(integer -> integer * 2).collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream().reduce(Integer::sum).get();
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        // TODO: 이 부분을 구현한다.
        return numbers.stream().filter(integer -> integer > 3).mapToInt(value -> value * 2).sum();
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths.get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        //System.out.println(words);
        //System.out.println(words.size());
        // TODO 이 부분에 구현한다.
        words.stream().filter(s -> s.length() > 12)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .distinct()
                .limit(100)
                .map(String::toLowerCase)
                .forEach(System.out::println);
    }
}
