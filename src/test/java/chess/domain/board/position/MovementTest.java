package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MovementTest {

    @Test
    @DisplayName("of() : column과 row를 통해 Movement를 생성할 수 있다.")
    void test_of() throws Exception {
        //when & then
        assertDoesNotThrow(() -> Movement.of(1, 1));
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "3,1",
            "1,3",
            "-2,3"
    })
    @DisplayName("of() : Movement에 존재하지 않는 column이나 row를 통해 생성한다면 NoSuchElementException이 발생한다.")
    void test_of_NoSuchElementException(final int column, final int row) throws Exception {
        //when & then
        assertThatThrownBy(() -> Movement.of(column, row))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("이동할 수 없는 방향입니다.");
    }

    @Test
    @DisplayName("nextPosition() : 다음 위치로 움직일 수 있다.")
    void test_nextPosition() throws Exception {
        //given
        final Column toColumn = Column.from(7);
        final Row toRow = Row.from(7);

        //when
        final Position to = Movement.UR.nextPosition(toColumn, toRow);

        //then
        assertAll(
                () -> assertEquals(8, to.column().value()),
                () -> assertEquals(8, to.column().value())
        );
    }
}
