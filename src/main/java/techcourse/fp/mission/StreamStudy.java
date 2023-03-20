package techcourse.fp.mission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
            .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        return words.stream().filter(s-> s.length()>12).count();
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        numbers.stream().map(s->result.add(2*s));
        return result;
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream().reduce(0,(a,b)->a+b);
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream()
                .filter(s->s>3)
                .map(s->s*2)
                .reduce(0,(a,b)->a+b);
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
            .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        System.out.println(words.size());
        // TODO 이 부분에 구현한다.

        words.stream().filter(s->s.length()>12)
                .distinct()
                .sorted(Comparator.comparing(String::length))
                .limit(100)
                .map(s->s.toLowerCase())
                .forEach(s-> System.out.println(s));

    }
}
