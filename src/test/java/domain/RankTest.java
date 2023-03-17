package domain;

import chess.domain.Rank;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RankTest {

    @Test
    void 인덱스_오름차순으로_정렬된_Rank_목록을_반환한다() {
        //given, when
        List<Rank> orderedRanks = List.of(Rank.EIGHT, Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR, Rank.THREE, Rank.TWO, Rank.ONE);

        //then
        assertThat(Rank.getOrderedRanks()).isEqualTo(orderedRanks);
    }

    @Test
    void 입력한값이_Rank에_존재하지않는경우_예외() {
        //given,when
        String value = "9";

        //then
        assertThatThrownBy(() -> Rank.validateValue(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 Rank입니다");
    }

    @Test
    void 입력한값이_Rank에_존재하면_아무일도_일어나지_않는다() {
        //given, when
        String value = "1";

        //then
        assertDoesNotThrow(() -> Rank.validateValue(value));
    }
}
