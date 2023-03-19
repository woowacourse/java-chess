package chess.domain.board;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class RankTest {

    @Test
    void 인덱스_내림차순으로_정렬된_Rank_목록을_반환한다() {
        List<Rank> expected = List.of(Rank.EIGHT, Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR, Rank.THREE, Rank.TWO, Rank.ONE);

        assertThat(Rank.getOrderedRanks()).isEqualTo(expected);
    }

}