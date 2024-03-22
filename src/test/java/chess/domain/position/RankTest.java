package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @ParameterizedTest
    @CsvSource({"1,ONE", "2,TWO", "3,THREE", "4,FOUR", "5,FIVE", "6,SIX", "7,SEVEN", "8,EIGHT"})
    @DisplayName("올바른 Rank를 찾을 수 있다")
    void should_find_rank(char rankCharName, String rankName) {

        assertThat(Rank.of(rankCharName).name()).isEqualTo(rankName);
    }

    @ParameterizedTest
    @CsvSource({"1,ONE", "2,TWO", "3,THREE", "4,FOUR", "5,FIVE", "6,SIX", "7,SEVEN", "8,EIGHT"})
    @DisplayName("올바른 Rank를 찾을 수 있다")
    void should_find_rank_from_value(int rankValue, String rankName) {

        assertThat(Rank.of(rankValue).name()).isEqualTo(rankName);
    }

    @Test
    @DisplayName("Rank와 Rank간 거리를 계산할 수 있다.")
    void should_calculate_distance() {
        Rank from = Rank.ONE;
        Rank to = Rank.TWO;

        RankDifference expectedDistance = from.calculateDifference(to);

        assertThat(expectedDistance).isEqualTo(new RankDifference(1));
    }

    @Test
    @DisplayName("Rank간 경로를 만들 수 있다.")
    void should_make_route() {
        Rank from = Rank.ONE;
        Rank to = Rank.FOUR;

        assertThat(from.getRankRoute(to)).containsExactly(Rank.TWO, Rank.THREE);
    }

    @Test
    @DisplayName("같은 Rank간의 경로는 자기 자신만 포함한다.")
    void should_make_route_when_same_Rank() {
        Rank from = Rank.ONE;
        Rank to = Rank.ONE;

        assertThat(from.getRankRoute(to)).containsExactly(Rank.ONE);
    }
}
