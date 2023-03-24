package chess.controller.state;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.A2;
import static chess.helper.PositionFixture.A3;
import static chess.helper.PositionFixture.A6;
import static chess.helper.PositionFixture.B6;
import static chess.helper.PositionFixture.B7;
import static chess.helper.PositionFixture.C6;
import static chess.helper.PositionFixture.C7;
import static chess.helper.PositionFixture.C8;
import static chess.helper.PositionFixture.E1;
import static chess.helper.PositionFixture.E2;
import static chess.helper.PositionFixture.E3;
import static chess.helper.PositionFixture.F8;
import static chess.helper.PositionFixture.G6;
import static chess.helper.PositionFixture.G7;
import static chess.helper.PositionFixture.H6;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.GameCommand;
import chess.controller.state.End;
import chess.controller.state.GameState;
import chess.controller.state.Play;
import chess.controller.state.Ready;
import chess.controller.state.Result;
import chess.model.game.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayTest {

    private GameState play;

    @BeforeEach
    void beforeEach() {
        final ChessGame chessGame = new ChessGame();
        final GameState ready = new Ready(chessGame);

        play = ready.execute(GameCommand.START, A1, A1);
    }

    @Test
    @DisplayName("execute()는 명령어로 start가 주어지면 예외가 발생한다.")
    void execute_givenStartCommand_thenFail() {
        // when, then
        assertThatThrownBy(() -> play.execute(GameCommand.START, A1, A1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 진행중입니다.");
    }
    
    @Test
    @DisplayName("execute()는 명령어로 move와 올바른 좌표가 주어지면 기물을 옮긴 뒤 Play를 반환한다.")
    void execute_givenMoveCommandAndValidPositions_thenMoveAndReturnPlay() {
        // when
        final GameState actual = play.execute(GameCommand.MOVE, A2, A3);

        // then
        assertThat(actual).isExactlyInstanceOf(Play.class);
    }

    @Test
    @DisplayName("execute()는 명령어로 move와 올바르지 않은 좌표가 주어지면 예외가 발생한다.")
    void execute_givenMoveCommandAndInvalidPositions_thenFail() {
        // when, then
        assertThatThrownBy(() -> play.execute(GameCommand.MOVE, A1, A1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("동일한 위치로 기물을 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("execute()는 move의 결과로 킹이 죽는다면 Result를 반환한다")
    void execute_givenMoveCommandAndValidPositions_thenMoveAndReturnResult() {
        // given
        play = play.execute(GameCommand.MOVE, E2, E3);
        play = play.execute(GameCommand.MOVE, B7, B6);
        play = play.execute(GameCommand.MOVE, E1, E2);
        play = play.execute(GameCommand.MOVE, C8, A6);
        play = play.execute(GameCommand.MOVE, A2, A3);

        // when
        final GameState actual = play.execute(GameCommand.MOVE, A6, E2);

        // then
        assertThat(actual).isExactlyInstanceOf(Result.class);
        assertThat(actual.isPrintable()).isFalse();
    }

    @Test
    @DisplayName("execute()는 명령어로 end가 주어지면 End를 반환한다.")
    void execute_givenEndCommand_thenReturnEnd() {
        // when
        final GameState actual = play.execute(GameCommand.END, A1, A1);

        // then
        assertThat(actual).isExactlyInstanceOf(End.class);
    }

    @Test
    @DisplayName("isContinue()는 호출하면 true를 반환한다.")
    void isContinue_whenCall_thenReturnFalse() {
        // when
        final boolean actual = play.isContinue();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isPlay()는 호출하면 true를 반환한다.")
    void isPlay_whenCall_thenReturnFalse() {
        // when
        final boolean actual = play.isPlay();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isPrintable()은 호출하면 false를 반환한다.")
    void isPrintable_wheCall_thenReturnFalse() {
        // when
        final boolean actual = play.isPrintable();

        // then
        assertThat(actual).isFalse();
    }
}
