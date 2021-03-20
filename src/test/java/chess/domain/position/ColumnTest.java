package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.piece.strategy.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class ColumnTest {

    @DisplayName("입력된 문자열에 해당되는 열을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"a:A", "b:B", "c:C", "d:D", "e:E", "f:F", "g:G",
            "h:H"}, delimiter = ':')
    void StringColumn(String input, Column expected) {
        assertEquals(expected, Column.getColumn(input));
    }

    @DisplayName("입력된 정수값에 해당되는 열을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1:A", "2:B", "3:C", "4:D", "5:E", "6:F", "7:G",
            "8:H"}, delimiter = ':')
    void IntegerColumn(int input, Column expected) {
        assertEquals(expected, Column.getColumn(input));
    }

    @DisplayName("입력된 문자열에 해당되는 이 없을 경우 예외를 던진다.")
    @ParameterizedTest
    @NullAndEmptySource
    @CsvSource(value = {"z", "u"})
    void invalidColumn(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Column.getColumn(input));
    }

    @DisplayName("입력된 정수값에 해당되는 이 없을 경우 예외를 던진다.")
    @ParameterizedTest
    @CsvSource(value = {"9", "0"})
    void invalidColumn(int input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Column.getColumn(input));
    }

    @DisplayName("입력된 방향만큼 열을 이동시킨다.")
    @ParameterizedTest
    @CsvSource(value = {"UP:E", "RIGHT:F", "DOWN:E", "LEFT:D", "UP_RIGHT:F",
            "DOWN_RIGHT:F", "DOWN_LEFT:D", "UP_LEFT:D", "L_UU:D", "R_UU:F", "RR_U:G",
            "RR_D:G", "R_DD:F", "L_DD:D", "LL_U:C", "LL_D:C"}, delimiter = ':')
    void move(Direction direction, Column expected) {
        assertEquals(expected, Column.E.move(direction));
    }

}