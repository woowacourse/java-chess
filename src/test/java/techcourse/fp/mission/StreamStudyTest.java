package techcourse.fp.mission;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class StreamStudyTest {

    private static final String COLON_DELIMITER = " : ";
    private List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

    // 1 : 2 : 3 : 4 : 5
    @Test
    public void For문을_활용하여_콜론을_추가하는_문자열_작성() {
        final int size = numbers.size();
        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            final String convertedValue = String.valueOf(numbers.get(i));
            stringBuilder.append(convertedValue);

            if (i != size - 1) {
                stringBuilder.append(" : ");
            }
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
    public void 병렬_처리하는지() {
        final List<Long> aList = new ArrayList<>();
        final Stream<Long> listParallel = aList.parallelStream();

        final Stream streamParallel = Stream.of(1, 2, 3, 4, 5).parallel();

        assertTrue(listParallel.isParallel());
        assertTrue(streamParallel.isParallel());
    }

    @Test
    public void 문자_길이가_12보다_큰_경우_Filter() throws Exception {
        final long result = StreamStudy.countWords();
        System.out.println("result : " + result);
    }

    @Test
    public void map을_사용하여_숫자_값을_2배() {
        final List<Integer> doubleNumbers = StreamStudy.doubleNumbers(numbers);
        doubleNumbers.forEach(System.out::println);
    }

    @Test
    public void 모든_숫자의_합() {
        final long sum = StreamStudy.sumAll(numbers);
        assertThat(sum).isEqualTo(21);
    }

    @Test
    public void sumOverThreeAndDouble() {
        numbers = Arrays.asList(3, 1, 6, 2, 4, 8);
        final long sum = StreamStudy.sumOverThreeAndDouble(numbers);
        assertThat(sum).isEqualTo(36);
    }

    @Test
    public void printLongestWordTop100() throws Exception {
        StreamStudy.printLongestWordTop100();
    }
}
