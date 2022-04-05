package chess.domain.game;

import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ReadyTest {

    @Test
    @DisplayName("준비 상태를 생성한다.")
    void createStateTest() {
        GameState gameState = new Ready();

        assertThat(gameState).isNotNull();
    }

    @Test
    @DisplayName("게임 시작시 시작 상태로 변한다.")
    void startTest() {
        GameState gameState = new Ready();

        assertThat(gameState.start(BoardFixtures.initial(), Color.WHITE)).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("게임 종료시 종료 상태로 변한다.")
    void finishTest() {
        GameState gameState = new Ready();

        assertThat(gameState.finish()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("준비상태에서는 이동할 수 없다.")
    void throwsExceptionWithTryingToMove() {
        List<Point> arguments = List.of(Point.of("a1"), Point.of("a2"));
        GameState state = new Ready();

        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> state.move(arguments));
    }

    @Test
    @DisplayName("준비상태에서는 점수를 확인할 수 없다.")
    void throwsExceptionWithTryingToStatus() {
        GameState state = new Ready();

        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(state::status);
    }

    @Test
    @DisplayName("준비상태는 실행가능한 상태이다.")
    void readyIsRunnable() {
        GameState state = new Ready();

        boolean isRunnable = state.isRunnable();

        assertThat(isRunnable).isTrue();
    }

    @Test
    @DisplayName("준비상태에서는 응답을 얻을 수 없다.")
    void throwsExceptionWithGettingResponse() {
        GameState state = new Ready();

        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(state::getResponse);
    }
}
