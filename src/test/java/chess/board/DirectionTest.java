package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    Position source = Position.of("d", 4);

    @Test
    @DisplayName("오른쪽 위 대각 방향을 올바르게 판단한다.")
    void determinePositiveFilePositiveRankTest() {
        // given
        Position destination = Position.of("f", 6);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.POSITIVE_FILE_POSITIVE_RANK);
    }

    @Test
    @DisplayName("왼쪽 아래 대각 방향을 올바르게 판단한다.")
    void determineNegativeFileNegativeRankTest() {
        // given
        Position destination = Position.of("b", 2);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.NEGATIVE_FILE_NEGATIVE_RANK);
    }

    @Test
    @DisplayName("왼쪽 위 대각 방향을 올바르게 판단한다.")
    void determineNegativeFilePositiveRankTest() {
        // given
        Position destination = Position.of("b", 6);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.NEGATIVE_FILE_POSITIVE_RANK);
    }

    @Test
    @DisplayName("오른쪽 아래 대각 방향을 올바르게 판단한다.")
    void determinePositiveFileNegativeRankTest() {
        // given
        Position destination = Position.of("f", 2);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.POSITIVE_FILE_NEGATIVE_RANK);
    }

    @Test
    @DisplayName("위쪽 방향을 올바르게 판단한다.")
    void determineSameFilePositiveRankTest() {
        // given
        Position destination = Position.of("d", 6);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.SAME_FILE_POSITIVE_RANK);
    }

    @Test
    @DisplayName("아래쪽 방향을 올바르게 판단한다.")
    void determineSameFileNegativeRankTest() {
        // given
        Position destination = Position.of("d", 2);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.SAME_FILE_NEGATIVE_RANK);
    }

    @Test
    @DisplayName("오른쪽 방향을 올바르게 판단한다.")
    void determinePositiveFileSameRankTest() {
        // given
        Position destination = Position.of("f", 4);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.POSITIVE_FILE_SAME_RANK);
    }

    @Test
    @DisplayName("왼쪽 방향을 올바르게 판단한다.")
    void determineNegativeFileSameRankTest() {
        // given
        Position destination = Position.of("b", 4);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.NEGATIVE_FILE_SAME_RANK);
    }

    @ParameterizedTest
    @CsvSource({"b,3", "f,3", "b,5", "f,5"})
    @DisplayName("나이트 이동 방향을 올바르게 판단한다.")
    void determineKnightDirectionTest(String fileName, int rankNumber) {
        // given
        Position destination = Position.of(fileName, rankNumber);
        // when, then
        assertThat(Direction.calculateBetween(source, destination))
                .isEqualTo(Direction.KNIGHT);
    }
}
