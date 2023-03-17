package techcourse.fp.study;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
@SuppressWarnings("NonAsciiCharacters")
class PlayGroundTest {

    public static final String COLON_DELIMITER = " : ";

    private final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

    @Test
    public void For문을_활용하여_콜론을_추가하는_문자열_작성() {
        final int size = numbers.size();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            stringBuilder.append(numbers.get(i));

            if (i != size - 1) {
                stringBuilder.append(" : ");
            }
        }

        System.out.println(stringBuilder);
    }

    @Test
    public void ForEach를_활용하여_콜론을_추가하는_문자열_작성() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Integer number : numbers) {
            stringBuilder.append(number).append(" : ");
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
        }

        System.out.println(stringBuilder);
    }

    @Test
    public void Stream을_활용하여_콜론을_추가하는_문자열_작성() {
        final String result = numbers.stream()
                .map(String::valueOf)
                .collect(joining(COLON_DELIMITER));

        System.out.println(result);
    }

    @Test
    public void 이렇게까지_Stream을_써야할까() throws IOException {
        int minGroupSize = 0;
        Stream<String> words = Files.lines(Paths
                .get("src/main/resources/fp/war-and-peace.txt"));

        words.collect(
                        groupingBy(word -> word.chars().sorted()
                                .collect(StringBuilder::new,
                                        (sb, c) -> sb.append((char) c),
                                        StringBuilder::append).toString()))
                .values().stream()
                .filter(group -> group.size() >= minGroupSize)
                .map(group -> group.size() + ": " + group)
                .forEach(System.out::println);
    }

    @Test
    public void ThreadPoolExecutor_실행_테스트() {
        executorService.submit(() -> System.out.println("test"));
    }

    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void printAllOld() {
        PlayGround.printAllOld(numbers);
    }

    @Test
    public void printAllLambda() {
        PlayGround.printAllLambda(numbers);
    }

    @Test
    public void 변경_불가능한_값을_사용하는지() {
        List<Long> aList = new ArrayList<>();
        Stream<Long> parallelStream = aList.parallelStream();

        assertTrue(parallelStream.isParallel());
    }

    @Test
    public void 함수를_인자로_받는_예제() {
        println("Area is ", 2, 3, (message, length, width) -> message + (length * width));
    }

    @Test
    public void 함수를_반환_값으로_활용하는_예제() {
        Function<String, Integer> toInt = value -> Integer.parseInt(value);
        final Integer number = toInt.apply("100");
        //toInt(100);
        assertThat(number).isEqualTo(100);
    }


    /**
     * Functional Interface를 학습하기 위한 예제 코드
     */

    @Test
    public void Function_예제() {
        println("Area is ", 2, 3,
                (message, length, width) -> message + (length * width));
    }

    @FunctionalInterface
    interface TriFunction<T1, T2, T3, R> {
        R apply(T1 t1, T2 t2, T3 t3);
    }

    private <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, TriFunction<T1, T2, T3, String> function) {
        System.out.println(function.apply(t1, t2, t3));
    }

    @Test
    public void Predicate_예제() {
        Predicate<Integer> isPositive = i -> i > 0;
        Predicate<Integer> lessThanThree = i -> i < 3;
        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        List<Integer> filteredPositive = filter(numbers, isPositive);
        assertThat(filteredPositive.size()).isEqualTo(5);

        List<Integer> filteredLessThanThree = filter(numbers, lessThanThree);
        assertThat(filteredLessThanThree.size()).isEqualTo(8);
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> condition) {
        return list.stream()
                .filter(condition)
                .collect(toList());
    }

    @Test
    public void Stream을_사용하지_않은_경우() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int expected = 8;

        Integer notUsingStreamResult = null;
        for (final Integer number : numbers) {
            if (number > 2 && number <= 5) {
                final int newNumber = number * 2;
                if (newNumber > 7) {
                    notUsingStreamResult = newNumber;
                    break;
                }
            }
        }
        assertThat(notUsingStreamResult).isEqualTo(expected);
    }

    @Test
    public void Stream을_사용한_경우() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int expected = 8;

        Integer usingStreamResult = numbers.stream()
                .filter(number -> number > 2)
                .filter(number -> number <= 5)
                .map(number -> number * 2)
                .filter(number -> number > 7)
                .findFirst()
                .get();

        assertThat(usingStreamResult).isEqualTo(expected);
    }

    @Test
    public void 타입추론은_언제_이루어질까() {
        Stream.of(1, 2, 3, 4, 5)
                .filter(number -> number > 3)
                .map(number -> number * 2)
                .map(i -> "#" + i)
                .findFirst()
                .get();
    }

    @Test
    public void Eager_Evaluation_테스트() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<String> result = new ArrayList<>();

        for (Integer number : numbers) {
            log("starting", number);
            log("filtering", number);

            if (number > 3) {
                log("post filtering", number);
                number = number * 2;

                String convertValue = "#" + number;
                log("post converting", number);

                result.add(convertValue);
            }
        }

        log("Invoking terminal method count.\n");
        log("The count is", result.size());
    }

    @Test
    public void Lazy_Evaluation_테스트() {
        Stream<String> stream = Stream.of(1, 2, 3, 4, 5)
                .peek(i -> log("starting", i))
                .filter(i -> {
                    log("filtering", i);
                    return i > 3;
                })
                .peek(i -> log("post filtering", i))
                .map(v -> v * 2)
                .map(i -> "#" + i)
                .peek(i -> log("post converting", i));

        log("Invoking terminal method count.\n");
        log("The count is", stream.count());
    }

    public static void log(Object... objects) {
        StringBuilder now = new StringBuilder(LocalDateTime.now().toString());

        for (Object object : objects) {
            now.append(" - ").append(object.toString());
        }

        System.out.println(now);

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void 재사용_스트림_문제() {
        IntStream stream = IntStream.of(1, 2);
        stream.forEach(System.out::println);

        stream.forEach(System.out::println);
    }

    @Test
    @Disabled
    public void 무한_스트림_문제() {
        IntStream.iterate(0, i -> i + 1)
                .forEach(System.out::println);
    }
}
