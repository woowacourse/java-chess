package chess.controller.state;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.A2;
import static chess.helper.PositionFixture.A3;
import static chess.helper.PositionFixture.A6;
import static chess.helper.PositionFixture.B6;
import static chess.helper.PositionFixture.B7;
import static chess.helper.PositionFixture.C8;
import static chess.helper.PositionFixture.E1;
import static chess.helper.PositionFixture.E2;
import static chess.helper.PositionFixture.E3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.GameCommand;
import chess.model.game.ChessGame;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    private GameState result;

    @BeforeEach
    void beforeEach() {
        final ChessGame chessGame = new ChessGame();
        final GameState ready = new Ready(chessGame);
        GameState play = ready.execute(GameCommand.START, Collections.emptyList());

        play = play.execute(GameCommand.MOVE, List.of(E2, E3));
        play = play.execute(GameCommand.MOVE, List.of(B7, B6));
        play = play.execute(GameCommand.MOVE, List.of(E1, E2));
        play = play.execute(GameCommand.MOVE, List.of(C8, A6));
        play = play.execute(GameCommand.MOVE, List.of(A2, A3));

        result = play.execute(GameCommand.MOVE, List.of(A6, E2));
    }

    @Test
    @DisplayName("execute()는 명령어로 Status가 주어지면 출력할 수 있는 Result를 반환한다")
    void execute_givenStatusCommand_thenReturnPrintableResult() {
        // when
        final GameState actual = result.execute(GameCommand.STATUS, Collections.emptyList());

        // then
        assertThat(actual).isExactlyInstanceOf(Result.class);
        assertThat(actual.isPrintable()).isTrue();
    }

    @Test
    @DisplayName("execute()는 명령어로 start가 주어지면 예외가 발생한다")
    void execute_givenStartCommand_thenFail() {
        // when, then
        assertThatThrownBy(() -> result.execute(GameCommand.START, Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임을 시작할 수 없습니다.");
    }

    @Test
    @DisplayName("execute()는 명령어로 move가 주어지면 예외가 발생한다")
    void execute_givenMoveCommand_thenFail() {
        // when, then
        assertThatThrownBy(() -> result.execute(GameCommand.MOVE, Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임을 플레이할 수 없습니다.");
    }

    @Test
    @DisplayName("isContinue()는 호출하면 true를 반환한다.")
    void isContinue_whenCall_thenReturnFalse() {
        // when
        final boolean actual = result.isContinue();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isPlay()는 호출하면 false를 반환한다.")
    void isPlay_whenCall_thenReturnFalse() {
        // when
        final boolean actual = result.isPlay();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isPrintable()은 호출하면 false를 반환한다.")
    void isPrintable_wheCall_thenReturnFalse() {
        // when
        final boolean actual = result.isPrintable();

        // then
        assertThat(actual).isFalse();
    }
}
