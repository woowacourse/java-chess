package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class RowTest {

    @DisplayName("입력된 문자열에 해당되는 행을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"8:EIGHT", "7:SEVEN", "6:SIX", "5:FIVE", "4:FOUR", "3:THREE", "2:TWO",
        "1:ONE"}, delimiter = ':')
    void row(String input, Row expected) {
        assertEquals(Row.getRow(input), expected);
    }

    @DisplayName("입력된 문자열에 해당되는 행이 없을 경우 예외를 던진다.")
    @ParameterizedTest
    @NullAndEmptySource
    @CsvSource(value = {"9", "0"})
    void invalidRow(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Row.getRow(input);
        });
    }

    @DisplayName("입력된 방향만큼 행을 이동시킨다.")
    @ParameterizedTest
    @CsvSource(value = {"UP:SIX", "RIGHT:FIVE", "DOWN:FOUR", "LEFT:FIVE", "UP_RIGHT:SIX",
        "DOWN_RIGHT:FOUR", "DOWN_LEFT:FOUR", "UP_LEFT:SIX", "L_UU:SEVEN", "R_UU:SEVEN",
        "RR_U:SIX",
        "RR_D:FOUR", "R_DD:THREE", "L_DD:THREE", "LL_U:SIX", "LL_D:FOUR"}, delimiter = ':')
    void move(Direction direction, Row expected) {
        assertEquals(Row.FIVE.move(direction), expected);
    }
}