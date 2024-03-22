package domain.position;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void ONE와_EIGHT_사이에_존재하는_rank_목록을_반환한다() {
        Rank source = Rank.ONE;
        Rank target = Rank.EIGHT;

        List<Rank> betweenRanks = source.between(target);

        assertThat(betweenRanks).containsExactlyElementsOf(List.of(
                Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN
        ));
    }

    @Test
    void EIGHT와_ONE_사이에_존재하는_rank_목록을_반환한다() {
        Rank source = Rank.EIGHT;
        Rank target = Rank.ONE;

        List<Rank> betweenRanks = source.between(target);

        assertThat(betweenRanks).containsExactlyElementsOf(List.of(
                Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR, Rank.THREE, Rank.TWO
        ));
    }
}
