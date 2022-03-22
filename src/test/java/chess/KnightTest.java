package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class KnightTest {

    @ParameterizedTest(name = "row {0}, column {1}")
    @CsvSource(value = {"0,0", "0, 2", "1,1", "0,5", "0,7", "1,6 ", "7,5", "7,7", "6,6", "7,2", "6,2"})
    @DisplayName("나이트의 초기 좌표가 (0,1), (0,6), (7,6), (7,1) 아닐 경우 예외를 발생한다.")
    void initialKingPositionException(int row, int column) {
        final Position position = new Position(row, column);

        assertThatThrownBy(() -> new Knight(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(position.toString() + "는 나이트의 초기 위치가 아닙니다.");
    }
}
