package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.domain.game.End;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EndCommandTest {

    private EndCommand endCommand;
    private ChessGame game;

    @BeforeEach
    void setUp() {
        game = new ChessGame(null);
        endCommand = new EndCommand(game);
    }

    @Test
    void handle_whenChessGameStatusAreEnd() {
        game.changeState(new End(game));

        assertThatThrownBy(() -> endCommand.handle(null))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isUsable() {
        endCommand.isAppropriateCommand(null);
        boolean actualTrue = endCommand.isAppropriateCommand("end");
        boolean actualFalse = endCommand.isAppropriateCommand("end2");

        assertThat(actualTrue).isTrue();
        assertThat(actualFalse).isFalse();
    }
}