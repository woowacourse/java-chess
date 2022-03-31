package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(BoardFactory.createChessBoard());
    }

    @Test
    @DisplayName("본인 턴이 아닌경우 에러를 발생한다.")
    void moveSourceOutOfPositionBound() {
        assertThatThrownBy(() -> chessGame.move("a7", "a6"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 당신의 차례가 아닙니다.");
    }

    @Test
    @DisplayName("기물이 이동할 수 없으면 에러를 발생한다.")
    void pieceIsNotMovable() {
        assertThatThrownBy(() -> chessGame.move("a1", "a2"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 기물을 이동할 수 없습니다.");
    }
}
