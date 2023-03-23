package chess.model.game.state;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.A2;
import static chess.helper.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.dto.PlayDto;
import chess.model.game.ChessGame;
import chess.model.game.GameCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayTest {

    private GameState play;

    @BeforeEach
    void beforeEach() {
        final ChessGame chessGame = new ChessGame();
        final GameState ready = new Ready(chessGame);
        final PlayDto request = new PlayDto(GameCommand.START, A1, A1);

        play = ready.execute(request);
    }

    @Test
    @DisplayName("execute()는 명령어로 start가 주어지면 예외가 발생한다.")
    void execute_givenStartCommand_thenFail() {
        // given
        final PlayDto request = new PlayDto(GameCommand.START, A1, A1);

        // when, then
        assertThatThrownBy(() -> play.execute(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 진행중입니다.");
    }
    
    @Test
    @DisplayName("execute()는 명령어로 move와 올바른 좌표가 주어지면 기물을 옮긴 뒤 Play를 반환한다.")
    void execute_givenMoveCommandAndValidPositions_thenMoveAndReturnPlay() {
        // given
        final PlayDto request = new PlayDto(GameCommand.MOVE, A2, A3);

        // when
        final GameState actual = play.execute(request);

        // then
        assertThat(actual).isExactlyInstanceOf(Play.class);
    }

    @Test
    @DisplayName("execute()는 명령어로 move와 올바르지 않은 좌표가 주어지면 예외가 발생한다.")
    void execute_givenMoveCommandAndInvalidPositions_thenFail() {
        // given
        final PlayDto request = new PlayDto(GameCommand.MOVE, A1, A1);

        // when, then
        assertThatThrownBy(() -> play.execute(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("동일한 위치로 기물을 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("execute()는 명령어로 end가 주어지면 End를 반환한다.")
    void execute_givenEndCommand_thenReturnEnd() {
        // given
        final PlayDto request = new PlayDto(GameCommand.END, A1, A1);

        // when
        final GameState actual = play.execute(request);

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
