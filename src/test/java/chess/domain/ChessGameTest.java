package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("체스 게임의 턴을 실행할 수 있다.")
    void play() {
        ChessGame chessGame = new ChessGame();

        assertDoesNotThrow(() -> chessGame.play(
                new Position(File.A, Rank.TWO), new Position(File.A, Rank.THREE)));
    }

    @Test
    @DisplayName("해당 진영의 말이 아닌 다른 진영의 말을 움직일 경우 예외가 발생한다.")
    void isThatTurn() {
        ChessGame chessGame = new ChessGame();

        assertThatThrownBy(() -> chessGame.play(
                new Position(File.A, Rank.SEVEN), new Position(File.A, Rank.SIX)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
