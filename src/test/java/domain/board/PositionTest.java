package domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("두 Position의 File 차이 구하기")
    void calculateFileDifference() {
        Position position1 = Position.of(1, 2);
        Position position2 = Position.of(2, 2);
        assertThat(position1.calculateFileDifference(position2)).isEqualTo(-1);
    }

    @Test
    @DisplayName("두 Position의 Rank 차이 구하기")
    void calculateRankDifference() {
        Position position1 = Position.of(2, 1);
        Position position2 = Position.of(2, 2);
        assertThat(position1.calculateRankDifference(position2)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Rank가 2인지 여부 반환")
    void isPawnInitialPosition() {
        Position position = Position.of(1, 2);
        assertThat(position.isWhitePawnInitialPosition()).isTrue();
    }
}
