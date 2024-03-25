package domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class RankTest {
    @Test
    void 존재하지_않은_rank를_찾을_경우_예외가_발생한다() {
        assertThatThrownBy(() -> Rank.fromNumber(0))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않은 rank입니다.");
    }

    @Test
    void 존재하는_rank를_찾을_경우_해당_rank을_반환한다() {
        Rank rank = Rank.fromNumber(1);

        assertThat(rank).isEqualTo(Rank.ONE);
    }

    @Test
    void 다른_rank와_사이에_있는_rank를_찾을_때_자신의_rank가_더_작은_경우_오름차순으로_반환한다() {
        Rank rank = Rank.fromNumber(4);
        Rank other = Rank.fromNumber(8);

        List<Rank> ranks = rank.findRanksBetween(other);

        assertThat(ranks).containsExactly(Rank.FIVE, Rank.SIX, Rank.SEVEN);
    }

    @Test
    void 다른_rank와_사이에_있는_rank를_찾을_때_자신의_rank가_더_큰_경우_내림차순으로_반환한다() {
        Rank rank = Rank.fromNumber(8);
        Rank other = Rank.fromNumber(4);

        List<Rank> ranks = rank.findRanksBetween(other);

        assertThat(ranks).containsExactly(Rank.SEVEN, Rank.SIX, Rank.FIVE);
    }
}
