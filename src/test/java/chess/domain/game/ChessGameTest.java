package chess.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.view.PositionConverter.convertToSourcePosition;
import static chess.view.PositionConverter.convertToTargetPosition;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    @Test
    @DisplayName("올바른 턴이 아니면 예외를 던진다.")
    void progress() {
        String command = "c7c5";
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        assertThatThrownBy(() -> chessGame.progress(convertToSourcePosition(command), convertToTargetPosition(command)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 지금은 WHITE차례입니다.");
    }
}
