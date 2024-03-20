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

    @Test
    void 현재_rank의_다음_rank를_반환한다() {
        Rank rank = Rank.fromNumber(1);

        assertThat(rank.next()).isEqualTo(Rank.TWO);
    }

    @Test
    void rank_8의_다음_rank를_호출하면_예외가_발생한다() {
        Rank rank = Rank.fromNumber(8);

        assertThatThrownBy(rank::next)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위를 벗어난 rank입니다.");
    }

    @Test
    void 현재_rank의_이전_rank를_반환한다() {
        Rank rank = Rank.fromNumber(8);

        assertThat(rank.prev()).isEqualTo(Rank.SEVEN);
    }

    @Test
    void rank_1의_이전_rank를_호출하면_예외가_발생한다() {
        Rank rank = Rank.fromNumber(1);

        assertThatThrownBy(rank::prev)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위를 벗어난 rank입니다.");
    }
}
