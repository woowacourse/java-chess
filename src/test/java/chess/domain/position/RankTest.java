package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {
    @DisplayName("0부터 시작하는 행 번호로 Rank를 찾을 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"0, EIGHT", "1, SEVEN", "2, SIX", "3, FIVE", "4, FOUR", "5, THREE", "6, TWO", "7, ONE"})
    void should_FindRank_When_RowNumberGiven(int rowNumber, Rank rank) {
        assertThat(Rank.from(rowNumber)).isEqualTo(rank);
    }

    @DisplayName("행 번호에 해당하는 Rank가 없을 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 9, 10})
    void should_ThrowNoSuchElementException_When_WrongRowNumberIsGiven(int invalidRowNumber) {
        assertThatThrownBy(() -> Rank.from(invalidRowNumber))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("Rank가 주어졌을 때 x축 반전시킨 Rank를 반환할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"EIGHT, ONE", "SEVEN, TWO", "SIX, THREE", "FIVE, FOUR"})
    void should_ReturnReverseRank(Rank rank, Rank reversedRank) {
        assertAll(
                () -> assertThat(rank.reverse()).isEqualTo(reversedRank),
                () -> assertThat(reversedRank.reverse()).isEqualTo(rank)
        );
    }

    @DisplayName("두 랭크간 거리를 계산할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"EIGHT, ONE, 7", "TWO, ONE, 1", "FIVE, ONE, 4"})
    void should_CalculateDistance_When_OtherRankGiven(Rank rank1, Rank rank2, int distance) {
        assertAll(
                () -> assertThat(rank1.calculateDistanceWith(rank2)).isEqualTo(distance),
                () -> assertThat(rank2.calculateDistanceWith(rank1)).isEqualTo(distance)
        );
    }

    @DisplayName("랭크가 특정 랭크보다 보드 위치에서 더 위인지 알 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"EIGHT, ONE", "SEVEN, TWO", "SIX, THREE", "FIVE, FOUR"})
    void should_CalculateRankIsHigherThanOther(Rank higher, Rank lower) {
        assertThat(higher.isAbove(lower)).isTrue();
    }

    @DisplayName("랭크가 특정 랭크보다 보드 위치에서 더 위인지 알 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"EIGHT, ONE", "SEVEN, TWO", "SIX, THREE", "FIVE, FOUR"})
    void should_CalculateRankIsLowerThanOther(Rank higher, Rank lower) {
        assertThat(lower.isBelow(higher)).isTrue();
    }

    @DisplayName("가중치만큼 움직인 랭크를 계산할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"EIGHT, 1, SEVEN", "EIGHT, 2, SIX", "EIGHT, 3, FIVE", "EIGHT, 4, FOUR", "EIGHT, 5, THREE"})
    void should_CalculateMovedFile_When_MoveWeightIsGiven(Rank start, int weight, Rank moved) {
        assertThat(start.move(weight)).isEqualTo(moved);
    }
}
