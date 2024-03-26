package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("1과 8의 차이(gap)는 7이다.")
    @Test
    void calculateGap() {
        int actual = Rank.ONE.gap(Rank.EIGHT);

        assertThat(actual).isEqualTo(7);
    }

    @DisplayName("1과 8의 차(difference)는 -7이다.")
    @Test
    void calculateDifference() {
        int actual = Rank.ONE.difference(Rank.EIGHT);

        assertThat(actual).isEqualTo(-7);
    }

    @DisplayName("source가 target보다 작을 때 source 위치와 target 위치 사이의 Rank 리스트를 찾는다.")
    @Test
    void findBetweenAsc() {
        Rank sourceRank = Rank.ONE;
        Rank targetRank = Rank.FOUR;

        List<Rank> ranks = sourceRank.findBetween(targetRank);

        assertThat(ranks).containsExactly(Rank.TWO, Rank.THREE);
    }

    @DisplayName("source가 target보다 클 때 source 위치와 target 위치 사이의 Rank 리스트를 찾는다.")
    @Test
    void findBetweenDesc() {
        Rank sourceRank = Rank.FOUR;
        Rank targetRank = Rank.ONE;

        List<Rank> ranks = sourceRank.findBetween(targetRank);

        assertThat(ranks).containsExactly(Rank.THREE, Rank.TWO);
    }
}
