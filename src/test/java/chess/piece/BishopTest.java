package chess.piece;

import chess.piece.Bishop;
import chess.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BishopTest {

    @ParameterizedTest(name = "row {0}, column {1}")
    @CsvSource(value = {"0,1", "0,3", "1,2", "1,5", "0,4", "0,6", "7,1", "7,3", "6,2", "7,4", "7,6", "6,5"})
    @DisplayName("비숍의 초기 좌표가 (0,4), (7,3) 아닐 경우 예외를 발생한다.")
    void initialKingPositionException(int row, int column) {
        final Position position = new Position(row, column);

        assertThatThrownBy(() -> new Bishop(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(position.toString() + "는 비숍의 초기 위치가 아닙니다.");
    }
}
