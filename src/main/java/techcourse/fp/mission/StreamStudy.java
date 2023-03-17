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

        return words.stream()
                .filter(w -> w.length() > 12)
                .count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> number * 2)
                .collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number > 3)
                .map(number -> number * 2)
                .reduce(0, Integer::sum);
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        // TODO 이 부분에 구현한다.
//
//        단어의 길이가 12자를 초과하는 단어를 추출한다.
//        12자가 넘는 단어 중 길이가 긴 순서로 100개의 단어를 추출한다.
//        단어 중복을 허용하지 않는다. 즉, 서로 다른 단어 100개를 추출해야 한다.
//        추출한 100개의 단어를 출력한다. 모든 단어는 소문자로 출력해야 한다.

        words.stream()
                .filter(word -> word.length() > 12)
                .distinct()
                .sorted(Comparator.comparing((String word) -> word.length()).reversed())
                .map(word -> word.toLowerCase())
                .limit(100)
                .collect(Collectors.toList());
    }
}
