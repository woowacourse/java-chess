package chess.domain.board;

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
    @DisplayName("두 Position이 같은 대각선상에 있음")
    void isOnSameDiagonalAs_True() {
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(6, 2);
        assertThat(position1.isOnSameDiagonalAs(position2)).isTrue();
    }

    @Test
    @DisplayName("두 Position이 같은 대각선상에 있지 않음")
    void isOnSameDiagonalAs_False() {
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(6, 3);
        assertThat(position1.isOnSameDiagonalAs(position2)).isFalse();
    }

    @Test
    @DisplayName("두 Position이 같은 File 상에 있음")
    void isOnSameFileAs_True() {
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 8);
        assertThat(position1.isOnSameFileAs(position2)).isTrue();
    }

    @Test
    @DisplayName("두 Position이 같은 File 상에 있지 않음")
    void isOnSameFileAs_False() {
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(2, 1);
        assertThat(position1.isOnSameFileAs(position2)).isFalse();
    }

    @Test
    @DisplayName("두 Position이 같은 Rank 상에 있음")
    void isOnSameRankAs_True() {
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(8, 1);
        assertThat(position1.isOnSameRankAs(position2)).isTrue();
    }

    @Test
    @DisplayName("두 Position이 같은 Rank 상에 있지 않음")
    void isOnSameRankAs_False() {
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 2);
        assertThat(position1.isOnSameRankAs(position2)).isFalse();
    }
}
