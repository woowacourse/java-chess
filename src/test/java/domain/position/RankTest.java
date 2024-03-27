package domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("두 랭크 사이의 거리를 구한다.")
    void distance_Ranks() {
        Rank source = Rank.FIVE;
        Rank target = Rank.ONE;

        int distance = source.distance(target);

        assertThat(distance).isEqualTo(4);
    }

    @Test
    @DisplayName("두 랭크 사이의 전방 거리를 구한다.")
    void forwardDistance_Ranks() {
        Rank source = Rank.ONE;
        Rank target = Rank.FIVE;

        int distance = source.forwardDistance(target);

        assertThat(distance).isEqualTo(4);
    }

    @Test
    @DisplayName("시작 랭크가 도착 랭크보다 낮다면 낮은 랭크부터 반환한다.")
    void betweenRanks_up() {
        Rank source = Rank.ONE;
        Rank target = Rank.FIVE;

        List<Rank> ranks = source.betweenRanks(target);

        assertThat(ranks).containsExactly(Rank.TWO, Rank.THREE, Rank.FOUR);
    }

    @Test
    @DisplayName("시작 랭크가 도착 랭크보다 높다면 높은 랭크부터 반환한다.")
    void betweenRanks_down() {
        Rank source = Rank.FIVE;
        Rank target = Rank.ONE;

        List<Rank> ranks = source.betweenRanks(target);

        assertThat(ranks).containsExactly(Rank.FOUR, Rank.THREE, Rank.TWO);
    }

    @Test
    @DisplayName("두 랭크가 같다면 참을 반환한다.")
    void isSame_Ranks_True() {
        Rank source = Rank.ONE;
        Rank target = Rank.ONE;

        assertThat(source.isSame(target)).isTrue();
    }

    @Test
    @DisplayName("두 랭크가 같지 않다면 거짓을 반환한다.")
    void isSame_Ranks_False() {
        Rank source = Rank.ONE;
        Rank target = Rank.TWO;

        assertThat(source.isSame(target)).isFalse();
    }

    @Test
    @DisplayName("랭크의 order를 기준으로 랭크들을 반환한다.")
    void orderValues() {
        List<Rank> ranks = Rank.valuesByOrder();

        assertThat(ranks).containsExactly(Rank.EIGHT, Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR, Rank.THREE, Rank.TWO, Rank.ONE);
    }
}
