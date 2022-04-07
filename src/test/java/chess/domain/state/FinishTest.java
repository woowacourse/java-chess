package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishTest {

    @Test
    @DisplayName("Finish 상태인 경우 start를 실행하면 WhiteTurn 상태가 된다.")
    void start() {
        GameState gameState = new Finish(ChessBoard.create());
        assertThat(gameState.start()).isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("Finish 상태인 경우 end를 실행하면 End 상태가 된다.")
    void end() {
        GameState gameState = new Finish(ChessBoard.create());

        assertThat(gameState.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("Finish 상태인 경우 move 명령을 실행할 수 없다.")
    void move() {
        GameState gameState = new Finish(ChessBoard.create());
        ChessBoardPosition sourcePosition = ChessBoardPosition.from("b2");
        ChessBoardPosition targetPosition = ChessBoardPosition.from("b4");

        assertThatThrownBy(() -> gameState.move(sourcePosition, targetPosition))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Finish 상태인 경우 status를 수행해도 상태가 변하지 않는다.")
    void status() {
        GameState gameState = new Finish(ChessBoard.create());

        assertThat(gameState.status()).isInstanceOf(gameState.getClass());
    }
}
