package chess.domain.game;

import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Queen;
import chess.request.BoardAndTurnInfo;
import chess.request.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class RunningTest {

    @Test
    @DisplayName("게임 시작시 에러가 발생한다.")
    void startTest() {
        GameState gameState = new Running(BoardFixtures.initial(), Color.WHITE);

        assertThatThrownBy(() ->gameState.start(BoardFixtures.initial(), Color.WHITE))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("게임 종료시 종료 상태로 변한다.")
    void finishTest() {
        GameState gameState = new Running(BoardFixtures.initial(), Color.WHITE);

        assertThat(gameState.finish()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("move 명령시 running 상태로 변한다.")
    void moveToRunningTest() {
        List<Point> arguments = List.of(Point.of("a2"), Point.of("a3"));
        GameState gameState = new Running(BoardFixtures.initial(), Color.WHITE);

        GameState movedState = gameState.move(arguments);

        assertThat(movedState).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("move 명령시 king이 죽으면 종료 상태로 변한다.")
    void moveToFinishTest() {
        List<Point> arguments = List.of(Point.of("e1"), Point.of("e8"));
        GameState gameState = new Running(BoardFixtures.create(Map.of(
                Point.of("e8"), new King(Color.BLACK),
                Point.of("e1"), new Queen(Color.WHITE)
        )), Color.WHITE);

        GameState movedState = gameState.move(arguments);

        assertThat(movedState).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("move 명령시 king이 죽으면 종료 상태로 변한다.")
    void throwsExceptionWithArgumentsSize() {
        List<Point> arguments = List.of(Point.of("e1"), Point.of("e8"), Point.of("e5"));
        GameState gameState = new Running(BoardFixtures.empty(), Color.WHITE);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> gameState.move(arguments));
    }

    @Test
    @DisplayName("status 상태로 변한다.")
    void turnIntoStatusState() {
        GameState state = new Running(BoardFixtures.empty(), Color.WHITE);

        GameState changed = state.status();

        assertThat(changed).isInstanceOf(Status.class);
    }

    @Test
    @DisplayName("진행상태는 실행가능한 상태이다.")
    void runningIsRunnable() {
        GameState state = new Running(BoardFixtures.empty(), Color.WHITE);

        boolean isRunnable = state.isRunnable();

        assertThat(isRunnable).isTrue();
    }

    @Test
    @DisplayName("진행상태에서는 응답을 얻을 수 있다.")
    void gettingResponse() {
        GameState state = new Running(BoardFixtures.empty(), Color.WHITE);

        Response response = state.getResponse();

        assertThat(response).isInstanceOf(BoardAndTurnInfo.class);
    }
}
