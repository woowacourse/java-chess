package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;

class QueenTest {

    @Test
    @DisplayName("queen이 대각선으로 이동할 수 있는 칸의 좌표를 반환한다.")
    void queenMoveDiagonalTest() {
        Queen queen = new Queen(Camp.WHITE);
        assertThat(queen.fetchMovePath(new Square(1, 3), new Square(5, 7))).contains(
                new Square(2, 4),
                new Square(3, 5),
                new Square(4, 6),
                new Square(5, 7)
        );
    }

    @Test
    @DisplayName("queen이 직선으로 이동할 수 있는 칸의 좌표를 반환한다.")
    void queenMoveForwardTest() {
        Queen queen = new Queen(Camp.WHITE);
        assertThat(queen.fetchMovePath(new Square(1, 3), new Square(4,3))).contains(
                new Square(2,3),
                new Square(3,3),
                new Square(4,3)
        );
    }
}
