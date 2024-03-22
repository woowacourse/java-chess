package domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}
