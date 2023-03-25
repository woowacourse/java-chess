package chess.controller.state;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.A2;
import static chess.helper.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.GameCommand;
import chess.dao.ChessMovementDao;
import chess.helper.FakeChessMovementDao;
import chess.model.game.ChessGame;
import chess.model.position.Position;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    private static final List<Position> EMPTY = Collections.emptyList();

    private ChessGame chessGame;
    private GameState ready;

    @BeforeEach
    void beforeEach() {
        chessGame = new ChessGame();
        final ChessMovementDao dao = new FakeChessMovementDao();
        ready = new Ready(chessGame, dao);
    }

    @Test
    @DisplayName("execute()는 명령어로 start가 주어지면 ChessGame의 필드를 초기화하고 Play를 반환한다.")
    void execute_givenStartCommand_thenInitialChessGameAndReturnPlay() {
        // when
        final GameState actual = ready.execute(GameCommand.START, EMPTY);

        // then
        assertThat(actual).isExactlyInstanceOf(Play.class);

        assertThat(chessGame.getChessBoard()).isNotNull();
    }

    @Test
    @DisplayName("execute()는 명령어로 load가 주어지면 dao에서 이전 게임 명령어를 불러와 초기화하고 Play를 반환한다.")
    void execute_givenLoadCommand_thenInitialChessGameAndReturnPlay() {
        // when
        final GameState actual = ready.execute(GameCommand.LOAD, EMPTY);

        // then
        assertThat(actual).isExactlyInstanceOf(Play.class);

        assertThat(chessGame.getChessBoard()).isNotNull();
    }

    @Test
    @DisplayName("execute()는 명령어로 move가 주어지면 예외가 발생한다.")
    void execute_givenMoveCommand_thenFail() {
        // when, then
        assertThatThrownBy(() -> ready.execute(GameCommand.MOVE, List.of(A2, A3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("execute()는 명령어로 status가 주어지면 예외가 발생한다.")
    void execute_givenStatusCommand_thenFail() {
        // when, then
        assertThatThrownBy(() -> ready.execute(GameCommand.STATUS, EMPTY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("execute()는 명령어로 end가 주어지면 ChessGame을 초기화하지 않고 End를 반환한다.")
    void execute_givenEndCommand_thenNotInitialChessGameAndReturnEnd() {
        // when
        final GameState actual = ready.execute(GameCommand.END, EMPTY);

        // then
        assertThat(actual).isExactlyInstanceOf(End.class);

        assertThatThrownBy(() -> chessGame.getChessBoard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임을 시작하지 않았습니다.");
    }

    @Test
    @DisplayName("isContinue()는 호출하면 true를 반환한다.")
    void isContinue_whenCall_thenReturnTrue() {
        // when
        final boolean actual = ready.isContinue();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isPlay()는 호출하면 false를 반환한다.")
    void isPlay_whenCall_thenReturnFalse() {
        // when
        final boolean actual = ready.isPlay();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isPrintable()은 호출하면 false를 반환한다.")
    void isPrintable_wheCall_thenReturnFalse() {
        // when
        final boolean actual = ready.isPrintable();

        // then
        assertThat(actual).isFalse();
    }
}
