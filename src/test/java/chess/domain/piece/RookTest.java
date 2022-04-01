package chess.domain.piece;

import chess.domain.board.Chessboard;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @ParameterizedTest
    @CsvSource(value = {"2:0"}, delimiter = ':')
    @DisplayName("rook 기물 이동 위치 검증 - true")
    void checkPositionWhenTrue(int a, int b) {
        Rook rook = new Rook(Color.BLACK);

        assertThat(rook.isMovablePosition(new Position(3, 3), new Position(3 + a, 3 + b),
                Chessboard.create().getBoard())).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:3", "1:2", "2:-1", "-1:-3"}, delimiter = ':')
    @DisplayName("rook 기물 이동 위치 검증 - false")
    void checkPositionWhenFalse(int a, int b) {
        Rook rook = new Rook(Color.BLACK);

        assertThat(rook.isMovablePosition(new Position(4, 4), new Position(4 + a, 4 + b),
                Chessboard.create().getBoard())).isFalse();
    }

    @Test
    @DisplayName("같은 타입인지 검사")
    void checkSameType() {
        Rook rook = new Rook(Color.BLACK);

        assertThat(rook.isSameType(Rook.class)).isTrue();
    }
}
