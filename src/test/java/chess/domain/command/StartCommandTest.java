package chess.domain.command;

import chess.domain.game.BlackTurn;
import chess.domain.game.ChessGame;
import chess.domain.game.End;
import chess.domain.game.WhiteTurn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StartCommandTest {
    private StartCommand startCommand;
    private ChessGame game;

    @BeforeEach
    void setUp() {
        game = new ChessGame(null);
        startCommand = new StartCommand(game);
    }

    @DisplayName("게임이 종료된상황에서 start 명령 실행시 예외 발생")
    @Test
    void handle_whenChessGameStatusAreEnd() {
        game.changeState(new End(game));

        assertThatThrownBy(() -> startCommand.handle(null))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("게임 진행중 상황에서 start 명령시 예외 발생")
    @Test
    void handle_whenChessGameStatusAreBlackTurn() {
        game.changeState(new BlackTurn(game));

        assertThatThrownBy(() -> startCommand.handle(null))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("게임 진행중 상황에서 start 명령시 예외 발생")
    @Test
    void handle_whenChessGameStatusAreWhiteTurn() {
        game.changeState(new WhiteTurn(game));

        assertThatThrownBy(() -> startCommand.handle(null))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("start 명령 인식 확인")
    @Test
    void isUsable() {
        boolean actualTrue = startCommand.isAppropriateCommand("start");
        boolean actualFalse = startCommand.isAppropriateCommand("start2");

        assertThat(actualTrue).isTrue();
        assertThat(actualFalse).isFalse();
    }
}