package chess.piece;

import chess.piece.King;
import chess.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class KingTest {

    @ParameterizedTest(name = "row {0}, column {1}")
    @CsvSource(value = {"0,3", "0, 5", "1, 4", "7, 4", "7, 2"})
    @DisplayName("킹의 초기 좌표가 (0,4), (7,3) 아닐 경우 예외를 발생한다.")
    void initialKingPositionException(int row, int column) {
        final Position position = new Position(row, column);

        assertThatThrownBy(() -> new King(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(position.toString() + "는 킹의 초기 위치가 아닙니다.");
    }
}
