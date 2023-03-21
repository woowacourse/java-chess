package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ColumnTest {

    @ParameterizedTest
    @CsvSource({
            "1", "2", "3", "4",
            "5", "6", "7", "8",
    })
    @DisplayName("from() : column 속에 value 가 있으면 column를 생성할 수 있다.")
    void test_from(final int value) throws Exception {
        //when
        final Column column = Column.from(value);

        //then
        assertEquals(column.value(), value);
    }

    @Test
    @DisplayName("from() : column 에 존재하지 않는 value로 column를 생성할 경우 NoSuchElementException 반환한다.")
    void test_from_NoSuchElementException() throws Exception {
        //when & then
        assertThatThrownBy(() -> Column.from(9))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("이동할 수 없는 column 방향입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a -> 1",
            "b -> 2",
            "c -> 3",
            "d -> 4",
            "e -> 5",
            "f -> 6",
            "g -> 7",
            "h -> 8"
    }, delimiterString = " -> ")
    @DisplayName("from() : char형이 주어질 경우, value에 속한 column을 생성할 수 있다.")
    void test_from(final char value, final int result) throws Exception {
        //given
        final Column column = Column.from(result);

        //when
        final Column convertedColumn = Column.from(value);

        //then
        assertEquals(convertedColumn, column);
    }
}
