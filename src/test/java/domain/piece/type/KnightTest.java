package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.type.restricted.Knight;

class KnightTest {

    @Test
    @DisplayName("knight가 이동할 수 있는 square를 반환한다.")
    void fetchMovableCoordinate() {
        Knight knight = new Knight(Camp.WHITE, Type.KNIGHT);
        assertThat(knight.fetchMovePath(Square.of(1,3), Square.of(3,4))).contains(
                Square.of(3,4)
        );
    }

    @Test
    @DisplayName("targetSquare가 갈수없는 경로이면 예외를 던진다.")
    void bishopMoveFailTest() {
        Knight knight = new Knight(Camp.WHITE, Type.KNIGHT);
        assertThatThrownBy(() -> knight.fetchMovePath(Square.of(1, 3), Square.of(2, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 경로입니다.");
    }
}
