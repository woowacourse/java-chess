import domain.piece.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @Test
    @DisplayName("다음 랭크를 가져온다.")
    void get_next_rank() {
        final Rank rank = Rank.FOUR;

        final var sut = rank.next();

        assertThat(sut).isEqualTo(Rank.FIVE);
    }

    @Test
    @DisplayName("그전 랭크를 가져온다.")
    void get_prev_rank() {
        final Rank rank = Rank.FOUR;

        final var sut = rank.prev();

        assertThat(sut).isEqualTo(Rank.THREE);
    }

    @Test
    @DisplayName("현재 랭크가 끝인지 확인한다.")
    void check_in_last_bound() {
        Set<Rank> set = EnumSet.of(Rank.ONE, Rank.EIGHT);

        set.forEach(rank -> assertThat(rank.isAtBoundary()).isTrue());
    }
}
