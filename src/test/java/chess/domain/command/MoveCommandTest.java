package chess.domain.command;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.End;
import chess.domain.game.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("올바른 입력인지 확인한다.")
    @Test
    void isUsable() {
        assertThat(moveCommand.isUsable("move a1 a2")).isTrue();
        assertThat(moveCommand.isUsable("move a1a2")).isFalse();
        assertThat(moveCommand.isUsable("move a1 a23")).isFalse();
        assertThat(moveCommand.isUsable("move a123 a2")).isFalse();
        assertThat(moveCommand.isUsable("move2 a1 a2")).isFalse();
        assertThat(moveCommand.isUsable("move a1 a1 a2")).isFalse();
    }

    @DisplayName("Status 인지 확인한다.")
    @Test
    void isStatus() {
        assertThat(moveCommand.isStatus()).isFalse();
    }
}