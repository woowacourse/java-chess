package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("경로에 체스피스가 있는 경우 움직일 수 없다.")
    void moveTest() {
        ChessGame chessGame = ChessGame.create();
        ChessBoardPosition sourcePosition = ChessBoardPosition.of("c1");
        ChessBoardPosition targetPosition = ChessBoardPosition.of("e3");

        assertThatThrownBy(() -> chessGame.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
