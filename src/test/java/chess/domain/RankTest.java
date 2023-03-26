package chess.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static chess.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankTest {

    @ParameterizedTest
    @CsvSource({"ONE, 3", "TWO, 2", "FOUR, 0", "EIGHT, 4"})
    void 거리를_계산한다(final Rank otherRank, final int expectedDistance) {
        //given
        final Rank rank = FOUR;

        //when
        final int actualDistance = rank.calculateDistance(otherRank);

        //then
        assertThat(actualDistance).isEqualTo(expectedDistance);
    }

    @Nested
    class 두_랭크_사이의_랭크들을_구한다 {

        @Test
        void 오름차순_랭크들을_반환한다() {
            //given
            final Rank startRank = TWO;
            final Rank endRank = SEVEN;

            //when
            final List<Rank> betweenRanks = startRank.getRanksTo(endRank);

            //then
            assertThat(betweenRanks).containsExactly(THREE, FOUR, FIVE, SIX);
        }

        @Test
        void 내림차순_랭크들을_반환한다() {
            //given
            final Rank startRank = SEVEN;
            final Rank endRank = TWO;

            //when
            final List<Rank> betweenRanks = startRank.getRanksTo(endRank);

            //then
            assertThat(betweenRanks).containsExactly(SIX, FIVE, FOUR, THREE);
        }
    }

    @ParameterizedTest
    @CsvSource({"FOUR, true", "SIX, false"})
    void 높은_랭크인지_확인한다(final Rank otherRank, final boolean expected) {
        //given
        final Rank rank = FIVE;

        //when
        final boolean actual = rank.isUpperThan(otherRank);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"FOUR, false", "SIX, true"})
    void 낮은_랭크인지_확인한다(final Rank otherRank, final boolean expected) {
        //given
        final Rank rank = FIVE;

        //when
        final boolean actual = rank.isLowerThan(otherRank);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
