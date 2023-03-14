package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.piece.Camp;

class KingTest {
    @Test
    @DisplayName("King이 이동할 수 있는 칸의 좌표를 반환한다.")
    void kingMoveTest() {
        King king = new King(Camp.WHITE);
        assertThat(king.fetchMovableCoordinate(List.of(0, 3))).contains(
                List.of(0, 2),
                List.of(0, 4),
                List.of(1, 2),
                List.of(1, 3),
                List.of(1, 4)
        );
    }
}
