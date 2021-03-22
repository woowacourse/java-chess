package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.domain.game.state.finished.End;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("End 상태일 때 execute 하면 예외")
    @Test
    void execute_whenChessGameStatusAreEnd() {
        game.changeState(new End(game));

        assertThatThrownBy(() -> endCommand.execute(null))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Status 인지 확인한다.")
    @Test
    void isStatus() {
        assertThat(endCommand.isStatus()).isFalse();
    }

}