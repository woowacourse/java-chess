package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RookTest {

    @ParameterizedTest(name = "row {0}, column {1}")
    @CsvSource(value = {"0,1", "1, 0", "0, 6", "1,7", "6,0", "7,1", "7,6", "6, 7"})
    @DisplayName("룩의 초기 좌표는 (0,0), (0,7), (7,0), (7,7) 아닐 경우 예외를 발생한다.")
    void initialKingPositionException(int row, int column) {
        final Position position = new Position(row, column);

        assertThatThrownBy(() -> new Rook(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(position.toString() + "는 룩의 초기 위치가 아닙니다.");
    }

}
