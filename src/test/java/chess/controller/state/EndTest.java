package chess.controller.state;

import static chess.helper.PositionFixture.A1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.GameCommand;
import chess.model.game.ChessGame;
import chess.model.position.Position;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    private static final List<Position> EMPTY = Collections.emptyList();

    private GameState end;

    @BeforeEach
    void beforeEach() {
        final ChessGame chessGame = new ChessGame();
        final GameState ready = new Ready(chessGame);
        final GameState play = ready.execute(GameCommand.START, EMPTY);

        end = play.execute(GameCommand.END, EMPTY);
    }

    @Test
    @DisplayName("execute()는 호출하면 예외가 발생한다.")
    void execute_whenCall_thenFail() {
        // when, then
        assertThatThrownBy(() -> end.execute(GameCommand.END, EMPTY))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되었습니다.");
    }

    @Test
    @DisplayName("isContinue()는 호출하면 false를 반환한다.")
    void isContinue_whenCall_thenReturnFalse() {
        // when
        final boolean actual = end.isContinue();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isPlay()는 호출하면 false를 반환한다.")
    void isPlay_whenCall_thenReturnFalse() {
        // when
        final boolean actual = end.isPlay();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isPrintable()은 호출하면 false를 반환한다.")
    void isPrintable_wheCall_thenReturnFalse() {
        // when
        final boolean actual = end.isPrintable();

        // then
        assertThat(actual).isFalse();
    }
}
