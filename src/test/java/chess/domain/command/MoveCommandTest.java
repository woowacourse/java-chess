package chess.domain.command;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.state.finished.End;
import chess.domain.game.state.idle.Ready;
import chess.exception.CommandFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoveCommandTest {

    private MoveCommand moveCommand;
    private ChessGame game;

    @BeforeEach
    void setUp() {
        game = new ChessGame(new Board(Collections.emptyList()));
        moveCommand = new MoveCommand(game);
    }

    @DisplayName("execute 상태일 때 execute 하면 예외")
    @Test
    void execute_whenChessGameStatusAreEnd() {
        game.changeState(new End(game));

        assertThatThrownBy(() -> moveCommand.execute("move a1 b2"))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Ready 상태일 때 execute 하면 예외")
    @Test
    void execute_whenChessGameStatusAreReady() {
        game.changeState(new Ready(game));

        assertThatThrownBy(() -> moveCommand.execute("move a1 b2"))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("잘못된 입력을 확인한다.")
    @ParameterizedTest
    @CsvSource({"move a23 a3", "move a43 a3", "move2 a1 a3"})
    void execute_checkWorngInput(String input) {
        assertThatThrownBy(() -> moveCommand.execute(input))
                .isExactlyInstanceOf(CommandFormatException.class);
    }

    @DisplayName("Status 인지 확인한다.")
    @Test
    void isStatus() {
        assertThat(moveCommand.isStatus()).isFalse();
    }

}