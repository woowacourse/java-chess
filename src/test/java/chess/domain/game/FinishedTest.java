package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.BoardFixtures;
import chess.domain.piece.Color;
import chess.dto.EmptyResponse;
import chess.dto.Response;

class FinishedTest {

    @Test
    @DisplayName("게임 시작시 에러가 발생한다.")
    void startTest() {
        GameState gameState = new Finished(BoardFixtures.initial(), Color.WHITE);

        assertThatThrownBy(gameState::start)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("게임 종료시 에러가 발생한다.")
    void finishTest() {
        GameState gameState = new Finished(BoardFixtures.initial(), Color.WHITE);

        assertThatThrownBy(gameState::finish)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("종료상태에서는 이동할 수 없다.")
    void throwsExceptionWithTryingToMove() {
        List<String> ignored = List.of("a1", "a2");
        GameState state = new Finished(BoardFixtures.initial(), Color.WHITE);

        assertThatExceptionOfType(UnsupportedOperationException.class)
            .isThrownBy(() -> state.move(ignored));
    }

    @Test
    @DisplayName("종료상태에서는 점수를 확인할 수 없다.")
    void throwsExceptionWithTryingToStatus() {
        GameState state = new Finished(BoardFixtures.initial(), Color.WHITE);

        assertThatExceptionOfType(UnsupportedOperationException.class)
            .isThrownBy(state::status);
    }

    @Test
    @DisplayName("종료상태는 진행 불가능한 상태이다.")
    void isRunnableIsFalse() {
        GameState state = new Finished(BoardFixtures.initial(), Color.WHITE);

        boolean isRunnable = state.isRunnable();

        assertThat(isRunnable).isFalse();
    }

    @Test
    @DisplayName("종료 상태에서 비어있는 응답을 반환한다.")
    void getEmptyResponse() {
        GameState state = new Finished(BoardFixtures.initial(), Color.WHITE);

        Response response = state.getResponse();

        assertThat(response).isInstanceOf(EmptyResponse.class);
    }
}
