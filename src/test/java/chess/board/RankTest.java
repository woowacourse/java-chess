package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @ParameterizedTest
    @MethodSource("getValueDiffTestCases")
    @DisplayName("대상 Rank과의 값 차이를 반환한다.")
    void getValueDiff(Rank rank, Rank targetRank, int valueDiff) {
        // when, then
        assertThat(rank.getValueDiff(targetRank)).isEqualTo(valueDiff);
    }

    static Stream<Arguments> getValueDiffTestCases() {
        return Stream.of(
                Arguments.arguments(Rank.ONE, Rank.ONE, 0),
                Arguments.arguments(Rank.ONE, Rank.TWO, 1),
                Arguments.arguments(Rank.ONE, Rank.THREE, 2),
                Arguments.arguments(Rank.ONE, Rank.FOUR, 3),
                Arguments.arguments(Rank.ONE, Rank.FIVE, 4),
                Arguments.arguments(Rank.ONE, Rank.SIX, 5),
                Arguments.arguments(Rank.ONE, Rank.SEVEN, 6),
                Arguments.arguments(Rank.ONE, Rank.EIGHT, 7)
        );
    }

    @ParameterizedTest
    @MethodSource("getValuePointTestCases")
    @DisplayName("대상 Rank과의 값 차이에 따른 수직 좌표 값을 반환한다.")
    void getValuePoint(Rank rank, Rank targetRank, int valuePoint) {
        // when, then
        assertThat(rank.getValuePoint(targetRank)).isEqualTo(valuePoint);
    }

    static Stream<Arguments> getValuePointTestCases() {
        return Stream.of(
                Arguments.arguments(Rank.FOUR, Rank.FOUR, 0),
                Arguments.arguments(Rank.FOUR, Rank.FIVE, 1),
                Arguments.arguments(Rank.FOUR, Rank.SIX, 1),
                Arguments.arguments(Rank.FOUR, Rank.SEVEN, 1),
                Arguments.arguments(Rank.FOUR, Rank.EIGHT, 1),
                Arguments.arguments(Rank.FOUR, Rank.THREE, -1),
                Arguments.arguments(Rank.FOUR, Rank.TWO, -1),
                Arguments.arguments(Rank.FOUR, Rank.ONE, -1)
        );
    }
}
