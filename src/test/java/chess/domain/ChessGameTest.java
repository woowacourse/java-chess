package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.GameCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    @Test
    @DisplayName("start를 입력하면 체스게임이 실행된다.")
    void startChessGame() {
        ChessGame chessGame = new ChessGame();
        chessGame.execute(GameCommand.START);
        assertThat(chessGame.isStarted()).isTrue();
    }

    @Test
    @DisplayName("start 상태에서 start를 입력하면 예외가 발생한다..")
    void startDuplicationChessGame() {
        ChessGame chessGame = new ChessGame();
        chessGame.execute(GameCommand.START);
        assertThatThrownBy(() -> chessGame.execute(GameCommand.START))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
