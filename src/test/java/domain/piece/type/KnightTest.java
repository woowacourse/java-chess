package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;

class KnightTest {

    @Test
    @DisplayName("knight가 이동할 수 있는 square를 반환한다.")
    void fetchMovableCoordinate() {
        Knight knight = new Knight(Camp.WHITE);
        assertThat(knight.fetchMovePath(new Square(1,3), new Square(3,4))).contains(
                new Square(3,4)
        );
    }
}
