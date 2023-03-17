package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.type.unrestricted.Rook;

class RookTest {
    @Test
    @DisplayName("rook이 이동할 수 있는 칸의 좌표를 반환한다.")
    void kingMoveTest() {
        Rook rook = new Rook(Camp.WHITE);
        assertThat(rook.fetchMovePath(new Square(1, 3), new Square(4,3))).contains(
                new Square(2,3),
                new Square(3,3),
                new Square(4,3)
        );
    }

    @Test
    @DisplayName("rook이 이동할 수 있는 칸의 좌표를 반환한다.")
    void kingMoveTestNegative() {
        Rook rook = new Rook(Camp.WHITE);
        assertThat(rook.fetchMovePath(new Square(5, 5), new Square(0,5))).contains(
                new Square(4,5),
                new Square(3,5),
                new Square(2,5),
                new Square(1,5),
                new Square(0,5)
        );
    }

    @Test
    @DisplayName("targetSquare가 갈수없는 경로이면 예외를 던진다.")
    void bishopMoveFailTest() {
        Rook rook = new Rook(Camp.WHITE);
        assertThatThrownBy(() -> rook.fetchMovePath(new Square(1, 3), new Square(2, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 경로입니다.");
    }
}
