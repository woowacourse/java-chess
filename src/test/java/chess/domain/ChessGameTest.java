package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(Board.create());
    }

    @Test
    @DisplayName("source가 Position의 범위를 초과하면 에러를 발생한다.")
    void moveSourceOutOfPositionBound() {
        assertThatThrownBy(() -> chessGame.move("z1", "c3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 범위를 초과하였습니다.");
    }

    @Test
    @DisplayName("source 에 Piece 가 Blank 이면 에러를 발생한다.")
    void moveSourcePieceIsBlank() {
        assertThatThrownBy(() -> chessGame.move("a3", "a2"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] source에 Piece가 존재하지 않습니다.");
    }
}
