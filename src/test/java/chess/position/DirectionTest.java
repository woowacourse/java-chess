package chess.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    Position source = Position.of(File.D, Rank.FOUR);

    @Test
    @DisplayName("오른쪽 위 대각 방향을 올바르게 판단한다.")
    void determinePositiveFilePositiveRankTest() {
        // given
        Position destination = Position.of(File.F, Rank.SIX);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.POSITIVE_FILE_POSITIVE_RANK);
    }

    @Test
    @DisplayName("왼쪽 아래 대각 방향을 올바르게 판단한다.")
    void determineNegativeFileNegativeRankTest() {
        // given
        Position destination = Position.of(File.B, Rank.TWO);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.NEGATIVE_FILE_NEGATIVE_RANK);
    }

    @Test
    @DisplayName("왼쪽 위 대각 방향을 올바르게 판단한다.")
    void determineNegativeFilePositiveRankTest() {
        // given
        Position destination = Position.of(File.B, Rank.SIX);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.NEGATIVE_FILE_POSITIVE_RANK);
    }

    @Test
    @DisplayName("오른쪽 아래 대각 방향을 올바르게 판단한다.")
    void determinePositiveFileNegativeRankTest() {
        // given
        Position destination = Position.of(File.F, Rank.TWO);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.POSITIVE_FILE_NEGATIVE_RANK);
    }

    @Test
    @DisplayName("위쪽 방향을 올바르게 판단한다.")
    void determineSameFilePositiveRankTest() {
        // given
        Position destination = Position.of(File.D, Rank.SIX);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.SAME_FILE_POSITIVE_RANK);
    }

    @Test
    @DisplayName("아래쪽 방향을 올바르게 판단한다.")
    void determineSameFileNegativeRankTest() {
        // given
        Position destination = Position.of(File.D, Rank.TWO);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.SAME_FILE_NEGATIVE_RANK);
    }

    @Test
    @DisplayName("오른쪽 방향을 올바르게 판단한다.")
    void determinePositiveFileSameRankTest() {
        // given
        Position destination = Position.of(File.F, Rank.FOUR);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.POSITIVE_FILE_SAME_RANK);
    }

    @Test
    @DisplayName("왼쪽 방향을 올바르게 판단한다.")
    void determineNegativeFileSameRankTest() {
        // given
        Position destination = Position.of(File.B, Rank.FOUR);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.NEGATIVE_FILE_SAME_RANK);
    }
}
