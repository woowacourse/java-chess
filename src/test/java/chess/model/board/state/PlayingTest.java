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
import chess.dao.MoveFindAllStrategy;
import chess.dao.MoveTruncator;
import chess.model.board.NoWhiteKingChessGame;
import chess.model.piece.Empty;
import chess.model.piece.pawn.WhitePawn;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayingTest extends MoveTruncator {

    private GameState playing;
    private MoveDao moveDao;

    @BeforeEach
    void init() {
        moveDao = new MoveDaoImpl();
        playing = ProgressState.of(START, moveDao);
    }

    @Test
    @DisplayName("GameCommand가 Start이면 End가 반환된다.")
    void givenEnd_thenReturnEnd() {
        // when
        final GameState end = playing.changeState(END);

        // then
        assertThat(end.getClass()).isEqualTo(End.class);
    }

    @Test
    @DisplayName("GameCommand가 Start이면 예외가 발생한다.")
    void givenStart_thenFail() {
        // when, then
        assertThatThrownBy(() -> playing.changeState(START))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("GameCommand가 Status이면 예외가 발생한다.")
    void givenStatus_thenFail() {
        // when
        final GameState status = playing.changeState(STATUS);

        // then
        assertThat(status.getClass()).isEqualTo(Status.class);
    }

    @ParameterizedTest(name = "{0}를 받으면 {1}이 반환된다.")
    @MethodSource("modifyStateParameters")
    void givenGameCommand_thenReturnGameState(final GameCommand command, final Class<GameState> state) {
        // when, then
        assertThat(playing.changeState(command).getClass()).isEqualTo(state);
    }

    private static Stream<Arguments> modifyStateParameters() {
        return Stream.of(
                Arguments.of(MOVE, Playing.class),
                Arguments.of(END, End.class),
                Arguments.of(STATUS, Status.class)
        );
    }

    @Test
    @DisplayName("excuteAndSave() 호출하면 move()가 수행된다.")
    void executeAndSave_whenCall_thenSuccess() {
        // when
        playing.executeAndSave(A2, A4);

        // then
        assertAll(
                () -> assertThat(playing.getBoard().get(A4).getClass()).isEqualTo(WhitePawn.class),
                () -> assertThat(playing.getBoard().get(A2).getClass()).isEqualTo(Empty.class),
                () -> assertThat(moveDao.findAll(new MoveFindAllStrategy())).hasSize(1)
        );
    }

    @Test
    @DisplayName("게임이 끝났으면 End클래스를 반환한다.")
    void isGameEnd_whenGameEnd_thenReturnEnd() {
        // given
        final NoWhiteKingChessGame noWhiteKingChessGame = NoWhiteKingChessGame.create();
        final GameState playing = new Playing(noWhiteKingChessGame.getChessGame(), new MoveDaoImpl());

        // when
        final GameState result = playing.isGameEnd();

        // then
        assertThat(result.getClass()).isEqualTo(End.class);
    }

    @Test
    @DisplayName("게임이 끝나지 않았으면 자기자신을 반환한다.")
    void isGameEnd_whenNotEndGame_thenReturnEnd() {
        // when
        final GameState result = playing.isGameEnd();

        // then
        assertThat(result).isSameAs(playing);
    }

    @Test
    @DisplayName("calculateScores()를 요청하면 예외를 발생시킨다")
    void calculateScores_whenCall_thenFail() {
        // when, then
        assertThatThrownBy(() -> playing.calculateScores())
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
