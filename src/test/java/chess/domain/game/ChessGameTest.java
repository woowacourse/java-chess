package chess.domain.game;

import chess.view.PositionConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    @DisplayName("게임이 끝나고 move를 하면 예외를 던진다.")
    void gameTerminateTest() {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        List<String> commands = List.of("b1c3", "h7h6", "c3b5", "h6h5", "b5c7", "h5h4", "c7e8");
        String commandAfterKingDead = "h4h3";
        for (String command : commands) {
            chessGame.progress(convertToSourcePosition(command), PositionConverter.convertToTargetPosition(command));
        }
        assertThatThrownBy(() ->
                chessGame.progress(convertToSourcePosition(commandAfterKingDead), convertToTargetPosition(commandAfterKingDead)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 게임이 끝나서 움직일 수 없습니다.");
    }
}
