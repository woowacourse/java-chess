package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("Ready 상태에서 start 명령어를 입력하면 WhiteTurn 상태가 된다.")
    void executeStartCommand() {
        GameState gameState = new Ready(ChessBoard.create());

        assertThat(gameState.start()).isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("Ready 상태에서 end 명령어를 입력하면 End 상태가 된다.")
    void executeEndCommand() {
        GameState gameState = new Ready(ChessBoard.create());

        assertThat(gameState.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("Ready 상태에서 move 명령어를 입력하면 오류가 발생한다.")
    void executeMoveCommand() {
        GameState gameState = new Ready(ChessBoard.create());
        ChessBoardPosition sourcePosition = ChessBoardPosition.from("b2");
        ChessBoardPosition targetPosition = ChessBoardPosition.from("b4");

        assertThatThrownBy(() -> gameState.move(sourcePosition, targetPosition))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Ready 상태에서 status 명령어를 입력하면 오류가 발생한다.")
    void executeStatusCommand() {
        GameState gameState = new Ready(ChessBoard.create());

        assertThatThrownBy(gameState::status)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
