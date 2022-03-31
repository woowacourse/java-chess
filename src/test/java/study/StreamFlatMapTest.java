package study;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class StreamFlatMapTest {

    @Test
    void flatMap은_매개변수로_들어온_스트림을_본인이_속한_스트림의_구성요소로_만든다() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);

        List<int[]> pairs = numbers1.stream()
                .flatMap(this::streamAndMap)
                .collect(toList());
//        pairs.forEach(pair -> System.out.printf("(%d, %d)", pair[0], pair[1]));
    }

    private Stream<int[]> streamAndMap(Integer i) {
        List<Integer> numbers2 = Arrays.asList(3, 4);
        return numbers2.stream()
                .map(j -> new int[]{i, j});
    }

    @Test
    void flatMap_합이_3의_배수인_쌍들만_출력() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);

        List<int[]> pairs = numbers1.stream()
                .flatMap(this::streamFilterAndMap)
                .collect(toList());
//        pairs.forEach(pair -> System.out.printf("(%d, %d)", pair[0], pair[1]));
    }

    private Stream<int[]> streamFilterAndMap(Integer i) {
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);
        return numbers2.stream()
                .filter(j -> (i + j) % 3 == 0)
                .map(j -> new int[]{i, j});
    }
}
