package chess.domain.command;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.End;
import chess.domain.game.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

class MoveCommandTest {

    private MoveCommand moveCommand;
    private ChessGame game;

    @BeforeEach
    void setUp() {
        game = new ChessGame(new Board(Collections.emptyList()));
        moveCommand = new MoveCommand(game);
    }

    @Test
    void handle_whenChessGameStatusAreEnd() {
        game.changeState(new End(game));

        assertThatThrownBy(() -> moveCommand.handle("move a1 b2"))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void handle_whenChessGameStatusAreReady() {
        game.changeState(new Ready(game));

        assertThatThrownBy(() -> moveCommand.handle("move a1 b2"))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isUsable() {
        assertThat(moveCommand.isAppropriateCommand("move a1 a2")).isTrue();
        assertThat(moveCommand.isAppropriateCommand("move a1a2")).isFalse();
        assertThat(moveCommand.isAppropriateCommand("move a1 a23")).isFalse();
        assertThat(moveCommand.isAppropriateCommand("move a123 a2")).isFalse();
        assertThat(moveCommand.isAppropriateCommand("move2 a1 a2")).isFalse();
        assertThat(moveCommand.isAppropriateCommand("move a1 a1 a2")).isFalse();
    }
}