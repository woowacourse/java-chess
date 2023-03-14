package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.piece.Camp;

class BishopTest {

    @Test
    @DisplayName("bishop이 이동할 수 있는 칸의 좌표를 반환한다.")
    void kingMoveTest() {
        Bishop bishop = new Bishop(Camp.WHITE);
        assertThat(bishop.fetchMovableCoordinate(List.of(1, 3))).contains(
                List.of(0, 2),
                List.of(0, 4),
                List.of(2, 4),
                List.of(2, 2),
                List.of(3, 5),
                List.of(3, 1),
                List.of(4, 6),
                List.of(5, 7)
        );
    }
}
