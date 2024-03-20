package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @ParameterizedTest
    @CsvSource(value = {"WHITE,TWO,true", "WHITE,FIVE,false", "WHITE,ONE,false", "BLACK,EIGHT,false",
            "BLACK,SEVEN,true", "BLACK,SIX,false"})
    @DisplayName("폰이 초기 위치인지 판단한다.")
    void isPawnInitialRank(Side side, Rank rank, boolean expected) {
        //when
        boolean result = rank.isPawnInitialRank(side);

        //then
        assertThat(result).isEqualTo(expected);
    }
}
