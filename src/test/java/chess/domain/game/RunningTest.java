package chess.domain.game;

import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RunningTest {

    @Test
    @DisplayName("게임 시작시 에러가 발생한다.")
    void startTest() {
        GameState gameState = new Running(BoardFixtures.initial(), Color.WHITE);

        assertThatThrownBy(gameState::start)
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
        List<String> arguments = List.of("a2", "a3");
        GameState gameState = new Running(BoardFixtures.initial(), Color.WHITE);

        GameState movedState = gameState.move(arguments);

        assertThat(movedState).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("move 명령시 king이 죽으면 종료 상태로 변한다.")
    void moveToFinishTest() {
        List<String> arguments = List.of("e1", "e8");
        GameState gameState = new Running(BoardFixtures.create(Map.of(
                Point.of("e8"), new King(Color.BLACK),
                Point.of("e1"), new Queen(Color.WHITE)
        )), Color.WHITE);

        GameState movedState = gameState.move(arguments);

        assertThat(movedState).isInstanceOf(Finished.class);
    }
}
