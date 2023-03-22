package techcourse.fp.mission;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class StreamStudyTest {

    private List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    public void 문자_길이가_12보다_큰_경우_Filter() throws Exception {
        long result = StreamStudy.countWords();
        System.out.println("result : " + result);
    }

    @Test
    public void map을_사용하여_숫자_값을_2배() {
        List<Integer> doubleNumbers = StreamStudy.doubleNumbers(numbers);
        doubleNumbers.forEach(System.out::println);
    }

    @Test
    public void 모든_숫자의_합() {
        long sum = StreamStudy.sumAll(numbers);
        assertThat(sum).isEqualTo(21);
    }

    @Test
    public void sumOverThreeAndDouble() {
        numbers = Arrays.asList(3, 1, 6, 2, 4, 8);
        long sum = StreamStudy.sumOverThreeAndDouble(numbers);
        assertThat(sum).isEqualTo(36);
    }

    @Test
    public void printLongestWordTop100() throws Exception {
        StreamStudy.printLongestWordTop100();
    }
}
