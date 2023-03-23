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
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        /*
        long count = 0;
        for (String w : words) {
            if (w.length() > 12) count++;
        }
        return count;

         */
        /*
        return words.stream()
                .filter(word -> word.length() > 12)
                .count();

         */
        /*
        return words.stream()
                .map(String::length)
                .filter(length -> length > 12)
                .count();

         */

        return words.stream()
                .mapToInt(String::length)
                .filter(length -> length > 12)
                .count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        /*
        List<Integer> result = new ArrayList<>();
        for (Integer number : numbers) {
            result.add(2 * number);
        }

        return result;

         */
        return numbers.stream()
                .map(number -> 2 * number)
                .collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        /*
        int result = 0;

        for (Integer number : numbers) {
            result += number;
        }

        return result;

         */
        //return numbers.stream().mapToInt(number -> number).sum();
        return numbers.stream()
                .mapToLong(number -> number)
                .reduce(0, Long::sum);
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        //return 0L; // TODO: 이 부분을 구현한다.
        return numbers.stream()
                .filter(number -> number > 3)
                .mapToLong(number -> number * 2)
                .reduce(0, Long::sum);
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));

        //List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        // TODO 이 부분에 구현한다.
        /*
        final List<String> words = Arrays.stream(contents.split("[\\P{L}]+"))
                .filter(word -> word.length() > 12)
                .distinct()
                .sorted(
                        Comparator.comparing(
                                String::length,
                                (length1, length2) -> length2 - length1
                        )
                )
                .limit(100)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

         */
        final List<String> words = Arrays.stream(contents.split("[\\P{L}]+"))
                .filter(word -> word.length() > 12)
                .distinct()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(100)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        System.out.println(words);
        System.out.println(words.size());
    }
}
