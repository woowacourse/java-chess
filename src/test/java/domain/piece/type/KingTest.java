package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.type.restricted.King;

class KingTest {
    @Test
    @DisplayName("King이 이동할 수 있는 칸의 좌표를 반환한다.")
    void kingMoveTest() {
        King king = new King(Camp.WHITE);
        assertThat(king.fetchMovePath(Square.of(0, 3), Square.of(1, 4))).contains(
                Square.of(1, 4)
        );
    }

    @Test
    @DisplayName("targetSquare가 갈수없는 경로이면 예외를 던진다.")
    void bishopMoveFailTest() {
        King king = new King(Camp.WHITE);
        assertThatThrownBy(() -> king.fetchMovePath(Square.of(1, 3), Square.of(2, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 경로입니다.");
    }
}
