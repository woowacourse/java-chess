package chess.model.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.PlayRequest;
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
        final PlayRequest playRequest = new PlayRequest("start", "A2", "A3");

        play = ready.execute(playRequest);
    }

    @Test
    @DisplayName("execute()는 명령어로 start가 주어지면 예외가 발생한다.")
    void execute_givenStartCommand_thenFail() {
        // given
        final PlayRequest playRequest = new PlayRequest("start", "Z0", "Z0");

        // when, then
        assertThatThrownBy(() -> play.execute(playRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 진행중입니다.");
    }
    
    @Test
    @DisplayName("execute()는 명령어로 move와 올바른 좌표가 주어지면 기물을 옮긴 뒤 Play를 반환한다.")
    void execute_givenMoveCommandAndValidPositions_thenMoveAndReturnPlay() {
        // given
        final PlayRequest playRequest = new PlayRequest("move", "A2", "A3");

        // when
        final GameState actual = play.execute(playRequest);

        // then
        assertThat(actual).isExactlyInstanceOf(Play.class);
    }

    @Test
    @DisplayName("execute()는 명령어로 move와 올바르지 않은 좌표가 주어지면 예외가 발생한다.")
    void execute_givenMoveCommandAndInvalidPositions_thenFail() {
        // given
        final PlayRequest playRequest = new PlayRequest("move", "A1", "A1");

        // when, then
        assertThatThrownBy(() -> play.execute(playRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("동일한 위치로 기물을 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("execute()는 명령어로 end가 주어지면 End를 반환한다.")
    void execute_givenEndCommand_thenReturnEnd() {
        // given
        final PlayRequest playRequest = new PlayRequest("end", "Z0", "Z0");

        // when
        final GameState actual = play.execute(playRequest);

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
}
