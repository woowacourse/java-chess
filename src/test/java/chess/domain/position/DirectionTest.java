package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    private Position source = Position.of(File.D, Rank.FOUR);

    @Test
    @DisplayName("오른쪽 위 대각 방향을 올바르게 판단한다.")
    void determinePositiveFilePositiveRankTest() {
        // given
        Position destination = Position.of(File.F, Rank.SIX);
        // when
        Direction direction = Direction.calculateBetween(source, destination);
        // then
        assertThat(direction).isEqualTo(Direction.POSITIVE_FILE_POSITIVE_RANK);
    }

    @Test
    @DisplayName("왼쪽 아래 대각 방향을 올바르게 판단한다.")
    void determineNegativeFileNegativeRankTest() {
        // given
        Position destination = Position.of(File.B, Rank.TWO);
        // when
        Direction direction = Direction.calculateBetween(source, destination);
        // then
        assertThat(direction).isEqualTo(Direction.NEGATIVE_FILE_NEGATIVE_RANK);
    }

    @Test
    @DisplayName("왼쪽 위 대각 방향을 올바르게 판단한다.")
    void determineNegativeFilePositiveRankTest() {
        // given
        Position destination = Position.of(File.B, Rank.SIX);
        // when
        Direction direction = Direction.calculateBetween(source, destination);
        // then
        assertThat(direction).isEqualTo(Direction.NEGATIVE_FILE_POSITIVE_RANK);
    }

    @Test
    @DisplayName("오른쪽 아래 대각 방향을 올바르게 판단한다.")
    void determinePositiveFileNegativeRankTest() {
        // given
        Position destination = Position.of(File.F, Rank.TWO);
        // when
        Direction direction = Direction.calculateBetween(source, destination);
        // then
        assertThat(direction).isEqualTo(Direction.POSITIVE_FILE_NEGATIVE_RANK);
    }

    @Test
    @DisplayName("위쪽 방향을 올바르게 판단한다.")
    void determineSameFilePositiveRankTest() {
        // given
        Position destination = Position.of(File.D, Rank.SIX);
        // when
        Direction direction = Direction.calculateBetween(source, destination);
        // then
        assertThat(direction).isEqualTo(Direction.SAME_FILE_POSITIVE_RANK);
    }

    @Test
    @DisplayName("아래쪽 방향을 올바르게 판단한다.")
    void determineSameFileNegativeRankTest() {
        // given
        Position destination = Position.of(File.D, Rank.TWO);
        // when
        Direction direction = Direction.calculateBetween(source, destination);
        // then
        assertThat(direction).isEqualTo(Direction.SAME_FILE_NEGATIVE_RANK);
    }

    @Test
    @DisplayName("오른쪽 방향을 올바르게 판단한다.")
    void determinePositiveFileSameRankTest() {
        // given
        Position destination = Position.of(File.F, Rank.FOUR);
        // when
        Direction direction = Direction.calculateBetween(source, destination);
        // then
        assertThat(direction).isEqualTo(Direction.POSITIVE_FILE_SAME_RANK);
    }

    @Test
    @DisplayName("왼쪽 방향을 올바르게 판단한다.")
    void determineNegativeFileSameRankTest() {
        // given
        Position destination = Position.of(File.B, Rank.FOUR);
        // when
        Direction direction = Direction.calculateBetween(source, destination);
        // then
        assertThat(direction).isEqualTo(Direction.NEGATIVE_FILE_SAME_RANK);
    }

    @Test
    @DisplayName("왼쪽 위 방향의 다음 Position을 반환한다.")
    void nextPositionOfNegativeFilePositiveRankTest() {
        // given
        Position destination = Position.of(File.C, Rank.FIVE);
        // when
        Position nextPosition = Direction.NEGATIVE_FILE_POSITIVE_RANK.nextPosition(source);
        // then
        assertThat(nextPosition).isEqualTo(destination);
    }

    @Test
    @DisplayName("오른쪽 아래 방향의 다음 Position을 반환한다.")
    void nextPositionOfPositiveFileNegativeRankTest() {
        // given
        Position destination = Position.of(File.E, Rank.THREE);
        // when
        Position nextPosition = Direction.POSITIVE_FILE_NEGATIVE_RANK.nextPosition(source);
        // then
        assertThat(nextPosition).isEqualTo(destination);
    }

    @Test
    @DisplayName("오른쪽 위 방향의 다음 Position을 반환한다.")
    void nextPositionOfPositiveFilePositiveRankTest() {
        // given
        Position destination = Position.of(File.E, Rank.FIVE);
        // when
        Position nextPosition = Direction.POSITIVE_FILE_POSITIVE_RANK.nextPosition(source);
        // then
        assertThat(nextPosition).isEqualTo(destination);
    }

    @Test
    @DisplayName("왼쪽 아래 방향의 다음 Position을 반환한다.")
    void nextPositionOfNegativeFileNegativeRankTest() {
        // given
        Position destination = Position.of(File.C, Rank.THREE);
        // when
        Position nextPosition = Direction.NEGATIVE_FILE_NEGATIVE_RANK.nextPosition(source);
        // then
        assertThat(nextPosition).isEqualTo(destination);
    }

    @Test
    @DisplayName("위쪽 방향의 다음 Position을 반환한다.")
    void nextPositionOfSameFilePositiveRankTest() {
        // given
        Position destination = Position.of(File.D, Rank.FIVE);
        // when
        Position nextPosition = Direction.SAME_FILE_POSITIVE_RANK.nextPosition(source);
        // then
        assertThat(nextPosition).isEqualTo(destination);
    }

    @Test
    @DisplayName("아래쪽 방향의 다음 Position을 반환한다.")
    void nextPositionOfSameFileNegativeRankTest() {
        // given
        Position destination = Position.of(File.D, Rank.THREE);
        // when
        Position nextPosition = Direction.SAME_FILE_NEGATIVE_RANK.nextPosition(source);
        // then
        assertThat(nextPosition).isEqualTo(destination);
    }

    @Test
    @DisplayName("오른쪽 방향의 다음 Position을 반환한다.")
    void nextPositionOfPositiveFileSameRankTest() {
        // given
        Position destination = Position.of(File.E, Rank.FOUR);
        // when
        Position nextPosition = Direction.POSITIVE_FILE_SAME_RANK.nextPosition(source);
        // then
        assertThat(nextPosition).isEqualTo(destination);
    }

    @Test
    @DisplayName("왼쪽 방향의 다음 Position을 반환한다.")
    void nextPositionOfNegativeFileSameRankTest() {
        // given
        Position destination = Position.of(File.C, Rank.FOUR);
        // when
        Position nextPosition = Direction.NEGATIVE_FILE_SAME_RANK.nextPosition(source);
        // then
        assertThat(nextPosition).isEqualTo(destination);
    }
}
