package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.domain.game.End;
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

    @DisplayName("게임이 종료된 상황에서 end 명령시 예외발생 확인")
    @Test
    void handle_whenChessGameStatusAreEnd() {
        game.changeState(new End(game));

        assertThatThrownBy(() -> endCommand.handle(null))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("end 입력 제대로 인식하는지 확인")
    @Test
    void isUsable() {
        boolean actualTrue = endCommand.isAppropriateCommand("end");
        boolean actualFalse = endCommand.isAppropriateCommand("end2");

        assertThat(actualTrue).isTrue();
        assertThat(actualFalse).isFalse();
    }
}