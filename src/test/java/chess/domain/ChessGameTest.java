package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.game.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("체스 게임의 턴을 실행할 수 있다.")
    void play() {
        ChessGame chessGame = new ChessGame();

        assertDoesNotThrow(() -> chessGame.play(
                new Position(Column.A, Rank.TWO), new Position(Column.A, Rank.THREE)));
    }

    @Test
    @DisplayName("해당 진영의 말이 아닌 다른 진영의 말을 움직일 경우 예외가 발생한다.")
    void isThatTurn() {
        ChessGame chessGame = new ChessGame();

        assertThatThrownBy(() -> chessGame.play(
                new Position(Column.A, Rank.SEVEN), new Position(Column.A, Rank.SIX)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
