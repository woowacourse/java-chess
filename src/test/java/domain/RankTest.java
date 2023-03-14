package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RankTest {

    @Test
    void 인덱스_오름차순으로_정렬된_Rank_목록을_반환한다() {
        //given, when
        List<Rank> orderedRanks = List.of(Rank.EIGHT, Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR, Rank.THREE, Rank.TWO, Rank.ONE);

        //then
        assertThat(Rank.getOrderedRanks()).isEqualTo(orderedRanks);
    }
}
