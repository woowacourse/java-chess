package domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("현재 Position의 File과 특정 Position의 File의 차를 계산")
    void calculateFileDifference() {
        Position position1 = Position.of(1, 2);
        Position position2 = Position.of(2, 2);
        assertThat(position1.calculateFileDifference(position2)).isEqualTo(-1);
    }

    @Test
    @DisplayName("현재 Position의 Rank과 특정 Position의 Rank의 차를 계산")
    void calculateRankDifference() {
        Position position1 = Position.of(2, 1);
        Position position2 = Position.of(2, 2);
        assertThat(position1.calculateRankDifference(position2)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Position 의 rank가 흰색 Pawn의 초기 rank 인지 확인")
    void isPawnInitialPosition() {
        Position position = Position.of(1, 2);
        assertThat(position.isWhitePawnInitialPosition()).isTrue();
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
