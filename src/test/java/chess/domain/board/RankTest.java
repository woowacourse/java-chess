package chess.domain.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class RankTest {

    @Test
    void 인덱스_내림차순으로_정렬된_Rank_목록을_반환한다() {
        List<Rank> expected = List.of(Rank.EIGHT, Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR, Rank.THREE, Rank.TWO, Rank.ONE);

        assertThat(Rank.getOrderedRanks()).isEqualTo(expected);
    }

    @Test
    void 입력한_문자열이_Rank의_value로_존재하지_않는_경우_예외() {
        String notExistValue = "0";

        assertThatThrownBy(() -> Rank.findRankByValue(notExistValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 Rank입니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1","2","3","4","5","6","7","8"})
    void 입력한_문자열이_Rank의_value로_존재하면_아무일도_일어나지_않는다(String existValue) {
        assertDoesNotThrow(() -> Rank.findRankByValue(existValue));
    }

    @Test
    void 입력한_인덱스와_일치하는_인덱스가_Rank에_존재하지_않는_경우_예외() {
        int notExistIndex = 0;

        assertThatThrownBy(() -> Rank.findRankByIndex(notExistIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 Rank입니다");
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8})
    void 입력한_인덱스와_일치하는_인덱스가_존재하면_아무일도_일어나지_않는다(int existIndex) {
        assertDoesNotThrow(() -> Rank.findRankByIndex(existIndex));
    }
}