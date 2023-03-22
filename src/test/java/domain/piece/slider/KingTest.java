package domain.piece.slider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;

class KingTest {
    @Test
    @DisplayName("King이 이동할 수 있는 칸의 좌표를 반환한다.")
    void kingMoveTest() {
        King king = new King(Camp.WHITE);
        assertThat(king.fetchMovableSquares(new Square(0, 3), new Square(1,4))).contains(
                new Square(1,4)
        );
    }

    @Test
    @DisplayName("targetSquare가 갈수없는 경로이면 예외를 던진다.")
    void bishopMoveFailTest() {
        King king = new King(Camp.WHITE);
        assertThatThrownBy(() -> king.fetchMovableSquares(new Square(1, 3), new Square(2, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("King은 두 칸 이상 이동할 수 없습니다.");
    }
}
