package chess.domain;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.game.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    @Test
    @DisplayName("게임을 시작한 후에 다시 시작할 수 없다.")
    void chessGame_startAndStart_exception() {
        //given
        final var chessGame = new ChessGame();
        chessGame.startGame();

        assertThatThrownBy(() -> chessGame.startGame())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("시작하기 전에 이동 명령을 내릴 수 없다.")
    void playTurn_beforeStart_exception() {
        final var chessGame = new ChessGame();
        Position source = new Position(File.A, Rank.SEVEN);
        Position target = new Position(File.A, Rank.SEVEN);

        assertThatThrownBy(() -> chessGame.playTurn(source, target))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
