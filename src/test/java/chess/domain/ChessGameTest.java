package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    @Test
    @DisplayName("source가 Position의 범위를 초과하면 에러를 발생한다.")
    void moveSourceOutOfPositionBound() {
        ChessGame chessGame = new ChessGame(Board.create());
        assertThatThrownBy(() -> chessGame.move("z1", "c3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 범위를 초과하였습니다.");
    }
}
