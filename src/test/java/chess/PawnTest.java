package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PawnTest {

    @ParameterizedTest(name = "row {0}, column {1}")
    @CsvSource(value = {"0,1", "2,1", "5,1", "7,1"})
    @DisplayName("폰의 초기 좌표가 1번과 6번 행이 아닐 경우 예외를 발생한다.")
    void initialKingPositionException(int row, int column) {
        final Position position = new Position(row, column);

        assertThatThrownBy(() -> new Pawn(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(position.toString() + "는 폰의 초기 위치가 아닙니다.");
    }
}
