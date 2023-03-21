package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.PiecesPosition;
import chess.domain.game.ChessCommandType;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameCommand;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndChessGameTest {

    private ChessGame endGame;

    @BeforeEach
    void setRunningGame() {
        PiecesPosition piecesPosition = new PiecesPosition();
        endGame = new EndChessGame(piecesPosition);
    }

    @Test
    @DisplayName("게임이 끝나면 실행이 불가능 하다.")
    void isRunnableGameTest() {
        assertThat(endGame.isRunnableGame())
                .isFalse();
    }

    @Test
    @DisplayName("게임이 끝나 어떤 명령도 실행 불가 하다.")
    void playByCommandExceptionTest() {
        ChessGameCommand command = new ChessGameCommand(
                ChessCommandType.MOVE,
                Position.of(File.A, Rank.ONE),
                Position.of(File.A, Rank.TWO)
        );

        assertThatThrownBy(() -> endGame.playByCommand(command))
                .isInstanceOf(IllegalStateException.class);
    }
}
