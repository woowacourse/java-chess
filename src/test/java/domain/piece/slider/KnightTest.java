package domain.piece.slider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.jumper.Knight;

class KnightTest {

    @Test
    @DisplayName("knight가 이동할 수 있는 square를 반환한다.")
    void fetchMovableCoordinate() {
        Knight knight = new Knight(Camp.WHITE);
        assertThat(knight.fetchMovePath(new Square(1,3), new Square(3,4))).contains(
                new Square(3,4)
        );
    }

    @Test
    @DisplayName("targetSquare가 갈수없는 경로이면 예외를 던진다.")
    void bishopMoveFailTest() {
        Knight knight = new Knight(Camp.WHITE);
        assertThatThrownBy(() -> knight.fetchMovePath(new Square(1, 3), new Square(2, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 경로입니다.");
    }
}
