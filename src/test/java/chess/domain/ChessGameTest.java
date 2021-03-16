package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameTest {

    @DisplayName("게임 진행 여부 확인")
    @Test
    void 게임_진행_확인_테스트() {
        ChessGame chessGame = new ChessGame();

        assertThat(chessGame.isRunning("start")).isTrue();
    }
}
