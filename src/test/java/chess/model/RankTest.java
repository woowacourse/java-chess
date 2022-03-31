package chess.model;

import static chess.model.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @Test
    @DisplayName("역순 정렬 테스트")
    void test() {
        List<Rank> reverseValues = Rank.reverseValues();
        assertThat(reverseValues).isEqualTo(List.of(EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, ONE));
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE:false", "TWO:true", "FOUR:false", "FIVE:false"}, delimiter = ':')
    @DisplayName("해당 Rank가 두 Rank 사이에 존재하는지 검증한다.")
    void traceGroup(Rank rank, boolean expected) {
        //given
        boolean actual = rank.isBetween(ONE, FOUR);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
