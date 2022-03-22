package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QueenTest {

    @ParameterizedTest(name = "row {0}, column {1}")
    @CsvSource(value = {"0, 2", "0, 4", "1, 3", "6, 4", "7, 3", "7, 5"})
    @DisplayName("퀸의 초기 좌표가 (0,3), (7,4) 아닐 경우 예외를 발생한다.")
    void initialKingPositionException(int row, int column) {
        final Position position = new Position(row, column);

        assertThatThrownBy(() -> new Queen(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(position.toString() + "는 퀸의 초기 위치가 아닙니다.");
    }
}
