package chess.model.board.state;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.MOVE;
import static chess.controller.GameCommand.START;
import static chess.controller.GameCommand.STATUS;
import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.controller.GameCommand;
import chess.dao.MoveDao;
import chess.dao.MoveDaoImpl;
import chess.dao.MoveSaveStrategy;
import chess.dao.MoveTruncator;
import chess.model.piece.Empty;
import chess.model.piece.pawn.WhitePawn;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ProgressStateTest extends MoveTruncator {

    @ParameterizedTest(name = "{0}를 받으면 {1}이 반환된다.")
    @MethodSource("startParameters")
    void givenGameCommand_thenReturnGameState(final GameCommand command, final Class state) {
        // when, then
        assertThat(ProgressState.of(command, new MoveDaoImpl()).getClass()).isEqualTo(state);
    }

    private static Stream<Arguments> startParameters() {
        return Stream.of(
                Arguments.of(START, Playing.class),
                Arguments.of(END, End.class),
                Arguments.of(STATUS, Status.class)
        );
    }

    @Test
    @DisplayName("시작하기전에는 move를 호출 할 수 없는지 테스트한다.")
    void cannotCallMove_WhenBeforeStart() {
        // when, then
        assertThatThrownBy(() -> ProgressState.of(MOVE, new MoveDaoImpl()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작하기 전에 move를 호출 할 수 없습니다.");
    }

    @Test
    @DisplayName("기존에 게임이 있으면 기존 체스 게임을 불러온다.")
    void hasGame_thenContinueGame() {
        // given
        final MoveDao moveDao = new MoveDaoImpl();
        moveDao.save(new MoveSaveStrategy(A2, A4));

        // when
        final GameState playing = ProgressState.of(START, moveDao);

        // then
        assertAll(
                () -> assertThat(playing.getBoard().get(A2)).isEqualTo(Empty.getInstance()),
                () -> assertThat(playing.getBoard().get(A4).getClass()).isEqualTo(WhitePawn.class)
        );
    }
}
