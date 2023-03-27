package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.type.unrestricted.Queen;

class QueenTest {

    @Test
    @DisplayName("queen이 대각선으로 이동할 수 있는 칸의 좌표를 반환한다.")
    void queenMoveDiagonalTest() {
        Queen queen = new Queen(Camp.WHITE);
        assertThat(queen.fetchMovePath(Square.of(1, 3), Square.of(5, 7))).contains(
                Square.of(2, 4),
                Square.of(3, 5),
                Square.of(4, 6),
                Square.of(5, 7)
        );
    }

    @Test
    @DisplayName("queen이 직선으로 이동할 수 있는 칸의 좌표를 반환한다.")
    void queenMoveForwardTest() {
        Queen queen = new Queen(Camp.WHITE);
        assertThat(queen.fetchMovePath(Square.of(1, 3), Square.of(4, 3))).contains(
                Square.of(2, 3),
                Square.of(3, 3),
                Square.of(4, 3)
        );
    }

    @Test
    @DisplayName("targetSquare가 갈수없는 경로이면 예외를 던진다.")
    void bishopMoveFailTest() {
        Queen queen = new Queen(Camp.WHITE);
        assertThatThrownBy(() -> queen.fetchMovePath(Square.of(1, 3), Square.of(2, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 경로입니다.");
    }
}
