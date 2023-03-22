package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RowTest {

    @ParameterizedTest
    @CsvSource({
            "1", "2", "3", "4",
            "5", "6", "7", "8",
    })
    @DisplayName("from() : Row 속에 value 가 있으면 Row를 생성할 수 있다.")
    void test_from(final int value) throws Exception {
        //when
        final Row row = Row.from(value);

        //then
        assertEquals(row.value(), value);
    }

    @Test
    @DisplayName("from() : row 에 존재하지 않는 value로 row를 생성할 경우 NoSuchElementException 반환한다.")
    void test_from_NoSuchElementException() throws Exception {
        //when & then
        assertThatThrownBy(() -> Row.from(9))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("이동할 수 없는 row 방향입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1 -> 1",
            "2 -> 2",
            "3 -> 3",
            "4 -> 4",
            "5 -> 5",
            "6 -> 6",
            "7 -> 7",
            "8 -> 8"
    }, delimiterString = " -> ")
    @DisplayName("from() : char형이 주어질 경우, value에 속한 row을 생성할 수 있다.")
    void test_from(final char value, final int result) throws Exception {
        //given
        final Row row = Row.from(result);

        //when
        final Row convertedRow = Row.from(value);

        //then
        assertEquals(convertedRow, row);
    }

    @Test
    @DisplayName("findPossibleRowCandidates() : Row 가 될 수 있는 value 를 소문자로 구할 수 있다.")
    void test_findPossibleRowCandidates() throws Exception {
        //when
        final List<Integer> possibleRowCandidates = Row.findPossibleRowCandidates();

        //then
        assertThat(possibleRowCandidates).contains(1,2,3,4,5,6,7,8);
    }
}
