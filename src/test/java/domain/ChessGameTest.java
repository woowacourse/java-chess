package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("폰은 적이 없을 때 대각선으로 갈 수 없다.")
    void pawn_validate() {
        ChessGame chessGame = new ChessGame();
        Square initPawnPosition = Square.of(3, 2);
        Square destination = Square.of(4, 3);

        assertThatThrownBy(() -> chessGame.move(initPawnPosition, destination))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("대각선으로 갈 수 없습니다.");
    }
}
