package chess.domain.command;

import chess.domain.game.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatusCommandTest {

    private StatusCommand statusCommand;
    private ChessGame game;

    @BeforeEach
    void setUp() {
        game = new ChessGame(null);
        statusCommand = new StatusCommand(game);
    }

    @DisplayName("StatusCommand의 상태를 확인한다!")
    @Test
    void isStatus() {
        //when
        boolean actual = statusCommand.isStatus();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("StatusCommand가 사용가능한 command인지 확인하는 기능")
    @Test
    void isUsable() {
        boolean actualTrue = statusCommand.isUsable("status");
        boolean actualFalse = statusCommand.isUsable("status2");

        assertThat(actualTrue).isTrue();
        assertThat(actualFalse).isFalse();
    }

}
