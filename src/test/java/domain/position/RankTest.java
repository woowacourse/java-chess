package domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("두 랭크 간 방향이 위쪽이면 참을 반환한다.")
    void isUp_True() {
        Rank source = Rank.ONE;
        Rank target = Rank.FIVE;

        boolean isUp = source.isUp(target);

        assertThat(isUp).isTrue();
    }

    @Test
    @DisplayName("두 랭크 간 방향이 아래쪽이면 참을 반환한다.")
    void isLDown_True() {
        Rank source = Rank.FIVE;
        Rank target = Rank.ONE;

        boolean isDown = source.isDown(target);

        assertThat(isDown).isTrue();
    }

    @Test
    @DisplayName("두 랭크가 같으면 거짓을 반환한다.")
    void isUp_False_isDown_False() {
        Rank source = Rank.ONE;
        Rank target = Rank.ONE;

        boolean isUp = source.isUp(target);
        boolean isDown = source.isDown(target);

        assertAll(() -> {
            assertThat(isUp).isFalse();
            assertThat(isDown).isFalse();
        });
    }

    @Test
    @DisplayName("두 랭크 사이의 모든 랭크를 출발부터 도착까지 순서대로 반환한다.")
    void betweenRanks() {
        Rank source = Rank.ONE;
        Rank target = Rank.FIVE;

        List<Rank> ranks = source.betweenRanks(target);

        assertThat(ranks).containsExactly(Rank.TWO, Rank.THREE, Rank.FOUR);
    }

    @Test
    @DisplayName("두 랭크 사이의 모든 랭크를 출발부터 도착까지 순서대로 반환한다. - 역순")
    void betweenRanks_reversed() {
        Rank source = Rank.FIVE;
        Rank target = Rank.ONE;

        List<Rank> ranks = source.betweenRanks(target);

        assertThat(ranks).containsExactly(Rank.FOUR, Rank.THREE, Rank.TWO);
    }
}
