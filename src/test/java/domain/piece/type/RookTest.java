package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;

class RookTest {
    @Test
    @DisplayName("rook이 이동할 수 있는 칸의 좌표를 반환한다.")
    void kingMoveTest() {
        Rook rook = new Rook(Camp.WHITE);
        assertThat(rook.fetchMovableCoordinate(new Square(1, 3), new Square(4,3))).contains(
                new Square(2,3),
                new Square(3,3),
                new Square(4,3)
        );
    }
}
