package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoardPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    @DisplayName("End 상태인 경우 start 명령을 실행할 수 없다.")
    void start() {
        GameState gameState = new End();
        assertThatThrownBy(gameState::start)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("End 상태인 경우 end 명령을 실행할 수 없다.")
    void end() {
        GameState gameState = new End();

        assertThatThrownBy(gameState::end)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("End 상태인 경우 move 명령을 실행할 수 없다.")
    void move() {
        GameState gameState = new End();
        ChessBoardPosition sourcePosition = ChessBoardPosition.from("b2");
        ChessBoardPosition targetPosition = ChessBoardPosition.from("b4");
        assertThatThrownBy(() -> gameState.move(sourcePosition, targetPosition))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("End 상태인 경우 status 명령을 실행할 수 없다.")
    void status() {
        GameState gameState = new End();

        assertThatThrownBy(gameState::status)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
