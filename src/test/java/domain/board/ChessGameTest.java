package domain.board;

import domain.piece.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ChessGameTest {

    @Test
    @DisplayName("기물의 이동규칙 밖인 경우, 이동할 수 없다")
    void moveImpossible() {
        ChessGame chessGame = new ChessGame();

        Coordinate start = new Coordinate(1, 0);
        Coordinate end = new Coordinate(0, 0);

        assertThatThrownBy(() -> chessGame.move(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("목적지에 기물이 이미 존재하는 경우, 이동할 수 없다")
    void moveImpossibleWhenExistsAtEnd() {
        ChessGame chessGame = new ChessGame();

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(1, 0);

        assertThatThrownBy(() -> chessGame.move(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("경로상에 기물이 존재하는 경우, reap이 불가능한 기물은 이동할 수 없다")
    void moveImpossibleWhenBlockedAndCantReap() {
        ChessGame chessGame = new ChessGame();

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 0);

        assertThatThrownBy(() -> chessGame.move(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("경로상에 기물이 존재하는 경우, reap이 가능한 기물은 이동할 수 있다")
    void moveImpossibleWhenBlockedAndCanReap() {
        ChessGame chessGame = new ChessGame();

        Coordinate start = new Coordinate(0, 1);
        Coordinate end = new Coordinate(2, 0);

        assertThatCode(() -> chessGame.move(start, end))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("일반적인 경우, 기물은 이동할 수 있다")
    void movePossible() {
        ChessGame chessGame = new ChessGame();

        Coordinate start = new Coordinate(1, 0);
        Coordinate end = new Coordinate(3, 0);

        assertThatCode(() -> chessGame.move(start, end))
                .doesNotThrowAnyException();
    }
}
