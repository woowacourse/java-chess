package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;

class BishopTest {

    @Test
    @DisplayName("bishop이 이동할 수 있는 칸의 좌표를 반환한다.")
    void kingMoveTest() {
        Bishop bishop = new Bishop(Camp.WHITE);
        assertThat(bishop.fetchMovableCoordinate(new Square(1,3), new Square(5,7))).contains(
                new Square(2,4),
                new Square(3,5),
                new Square(4,6),
                new Square(5,7)
        );
    }
}
