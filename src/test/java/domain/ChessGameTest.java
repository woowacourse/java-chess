package domain;

import static domain.ChessColumn.*;
import static domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.OutputView;

class ChessGameTest {

    @Test
    @DisplayName("폰은 적이 없을 때 대각선으로 갈 수 없다.")
    void pawnCanNotGoDiagonal() {
        ChessGame chessGame = new ChessGame();
        Square initPawnPosition = Square.of(3, 2);
        Square destination = Square.of(4, 3);
        assertThatThrownBy(() -> chessGame.move(initPawnPosition, destination))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("대각선으로 갈 수 없습니다.");
    }

    @Test
    @DisplayName("폰은 앞에 기물이 있는경우 앞으로 갈 수 없다.")
    void pawnCanNotGoStraight() {
        ChessGame chessGame = new ChessGame();
        chessGame.move(Square.of(B, TWO), Square.of(B, FOUR));
        chessGame.move(Square.of(B, SEVEN), Square.of(B, FIVE));

        assertThatThrownBy(() -> chessGame.move(Square.of(B, FOUR), Square.of(B, FIVE)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("폰은 기물이 있으면 앞으로 갈 수 없습니다.");
    }
}
