package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    @Test
    @DisplayName("start를 입력하면 체스게임이 실행된다.")
    void startChessGame() {
        ChessGame chessGame = new ChessGame();
        chessGame.execute(Command.of("start"));
        assertThat(chessGame.isRunning()).isTrue();
    }

    @Test
    @DisplayName("start 상태에서 start를 입력하면 예외가 발생한다..")
    void startDuplicationChessGame() {
        ChessGame chessGame = new ChessGame();
        chessGame.execute(Command.of("start"));
        assertThatThrownBy(() -> chessGame.execute(Command.of("start")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈 말을 움직이면 예외가 발생한다.")
    void emptyPieceMove() {
        ChessGame chessGame = new ChessGame();
        chessGame.execute(Command.of("start"));
        assertThatThrownBy(() -> chessGame.execute(Command.of("move a3 a4")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
