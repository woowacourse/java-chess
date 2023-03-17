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
        
        long count = words.stream().filter(w -> w.length() > 12).count();
        return count;
    }
    
    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        List<Integer> result = numbers.stream().map(number -> 2 * number).collect(Collectors.toList());
        return result;
    }
    
    public static long sumAll(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }
    
    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream().filter(e -> e > 3).map(e -> e * 2).reduce(0, Integer::sum);
    }
    
    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/techcourse/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        
        words.stream()
                .distinct()
                .filter(s -> s.length() > 12)
                .sorted(Comparator.comparing(String::length).reversed())
                .limit(100)
                .map(String::toLowerCase)
                .forEach(System.out::println);
    }
}
