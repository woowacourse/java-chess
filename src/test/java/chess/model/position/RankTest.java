package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @Nested
    @DisplayName("findRank() 테스트")
    class FindRankMethodTest {

        @ParameterizedTest(name = "findRank()는 {0}을 전달하면 {1}을 반환한다")
        @DisplayName("findRank() 성공 테스트")
        @MethodSource("chess.helper.arguments.RankArguments#provideValidFindRankArguments")
        void findRank_givenValue_thenReturnSameValueRank(final int value, final Rank expected) {
            // when
            final Rank actual = Rank.findRank(value);

            // then
            assertThat(actual).isSameAs(expected);
        }

        @ParameterizedTest(name = "1 ~ 8의 범위를 벗어나는 값 {0}을 전달하면 예외가 발생한다")
        @DisplayName("findRank() 실패 테스트")
        @ValueSource(ints = {0, 9})
        void findRank_givenInvalidValue_thenFail(final int value) {
            assertThatThrownBy(() -> Rank.findRank(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("존재하지 않는 행입니다.");
        }
    }

    @Test
    @DisplayName("differ()는 다른 Rank를 전달하면 차이를 반환한다")
    void differ_givenOtherRank_thenReturnDiffer() {
        // given
        final Rank first = Rank.FIRST;

        // when
        final int actual = first.differ(Rank.FIRST);

        assertThat(actual).isZero();
    }

    @Nested
    @DisplayName("findNextRank() 테스트")
    class FindNextRankMethodTest {

        @ParameterizedTest(name = "주어지는 offer가 {0}일 때 {1}을 반환한다")
        @DisplayName("findNextRank() 성공 테스트")
        @MethodSource("chess.helper.arguments.RankArguments#provideValidFindNextRankArguments")
        void findNextRank_givenOffset_thenReturnNextRank(final int offer, final Rank expected) {
            // given
            final Rank fifth = Rank.FIFTH;

            // when
            final Rank actual = fifth.findNextRank(offer);

            // then
            assertThat(actual).isSameAs(expected);
        }

        @ParameterizedTest(name = "Rank가 {0}일 때 계산 결과가 1 ~ 8의 범위를 넘어서는 offer {1}이 주어지면 예외가 발생한다.")
        @DisplayName("findNextRank() 실패 테스트")
        @MethodSource("chess.helper.arguments.RankArguments#provideInvalidFindNextRankArguments")
        void findNextRank_whenBoundaryRank_givenBoundaryOffset_thenFail(final Rank rank, final int offer) {
            assertThatThrownBy(() -> rank.findNextRank(offer))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("존재하지 않는 행입니다.");
        }
    }
}
