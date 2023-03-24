package techcourse.fp.mission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
                .get("C:\\local_work\\IntelliJ\\WoowaCourse\\LV1\\java-chess\\src\\main\\resources\\techcourse\\fp\\war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        long count = 0;
//        for (String w : words) {
//            if (w.length() > 12) count++;
//        }
        count = words.stream()
                .mapToInt(String::length)
                .filter(it -> it > 12)
                .count();
        return count;
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
//        for (Integer number : numbers) {
//            result.add(2 * number);
//        }
        result = numbers.stream()
                .map(number -> number * 2)
                .collect(Collectors.toList());

        return result;
    }

    public static long sumAll(List<Integer> numbers) {
        int result = 0;

//        for (Integer number : numbers) {
//            result += number;
//        }

        result = numbers.stream()
                .reduce(0, (total, number) -> total + number);

        return result;
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number > 3)
                .mapToLong(number -> number*2)
                .sum();
//        return 0L; // TODO: 이 부분을 구현한다.
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get("C:\\local_work\\IntelliJ\\WoowaCourse\\LV1\\java-chess\\src\\main\\resources\\techcourse\\fp\\war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"))
                .stream()
                .filter(word -> word.length() > 12)
                .distinct()
                .sorted(Comparator.comparing(String::length).reversed()) // 이거만 다시 봐보장~
                .limit(100)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        System.out.println(words);
        System.out.println(words.size());
    }
}
