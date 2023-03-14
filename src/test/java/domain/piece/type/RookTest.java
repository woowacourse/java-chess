package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.piece.Camp;

class RookTest {
    @Test
    @DisplayName("rook이 이동할 수 있는 칸의 좌표를 반환한다.")
    void kingMoveTest() {
        Rook rook = new Rook(Camp.WHITE);
        assertThat(rook.fetchMovableCoordinate(List.of(1, 3))).contains(
                List.of(1, 0),
                List.of(1, 1),
                List.of(1, 2),
                List.of(1, 4),
                List.of(1, 5),
                List.of(1, 6),
                List.of(1, 7),
                List.of(0, 3),
                List.of(2, 3),
                List.of(3, 3),
                List.of(4, 3),
                List.of(5, 3),
                List.of(6, 3),
                List.of(7, 3)
        );
    }
}
