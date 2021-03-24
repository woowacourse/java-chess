package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.Start;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.Running;
import chess.exception.InvalidCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChessGameTest {

    private ChessGame runningChessGame;

    @BeforeEach
    void setUp() {
        runningChessGame = new ChessGame(new Running(Board.createGamingBoard(), Side.WHITE));
    }

    @Test
    @DisplayName("Ready 상태에서 Running 상태로 전환")
    void start() {
        ChessGame chessGame = new ChessGame(new Ready(Board.createGamingBoard()));
        chessGame.start();

        assertThat(chessGame.state()).isInstanceOf(Running.class);
    }

    @ParameterizedTest(name = "Running 상태에서 move, end 명령 가능")
    @ValueSource(strings = {"move a2 a3", "end"})
    void execute(String command) {
        runningChessGame.execute(Command.from(command));
    }

    @ParameterizedTest(name = "Running 상태에서 start 명령 실행시 예외발생")
    @ValueSource(strings = {"start", "status"})
    void executeStartFail() {
        assertThatThrownBy(() -> runningChessGame.execute(new Start()))
                .isInstanceOf(InvalidCommandException.class);
    }
}