package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.Rank.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @ParameterizedTest
    @CsvSource({"ONE, 3", "TWO, 2", "FOUR, 0", "EIGHT, 4"})
    @DisplayName("거리 계산 테스트")
    void calculateDistanceTest(final Rank otherRank, final int expectedDistance) {
        final Rank rank = FOUR;

        final int actualDistance = rank.calculateDistance(otherRank);

        assertThat(actualDistance).isEqualTo(expectedDistance);
    }

}
