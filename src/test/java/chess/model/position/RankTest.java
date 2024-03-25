package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {
    @ParameterizedTest
    @ValueSource(ints = {-50, -2, -1, 0, 9, 10, 11, 244})
    @DisplayName("유효하지 않는 좌표로 Rank를 생성하면 예외가 발생한다.")
    void from(int given) {
        //when //then
        assertThatThrownBy(() -> Rank.from(given))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE,THREE,-2", "EIGHT,FIVE,3", "THREE,SIX,-3"})
    @DisplayName("두 Rank의 차이를 구한다.")
    void minus(Rank given, Rank other, int expected) {
        //when
        int result = given.minus(other);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "WHITE,TWO,true", "WHITE,FIVE,false", "WHITE,ONE,false",
            "BLACK,EIGHT,false", "BLACK,SEVEN,true", "BLACK,SIX,false"
    })
    @DisplayName("폰이 초기 위치인지 판단한다.")
    void isPawnInitialRank(Side side, Rank rank, boolean expected) {
        //when
        boolean result = rank.isPawnInitialRank(side);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"TWO,2,FOUR", "ONE,3,FOUR", "EIGHT,-5,THREE"})
    @DisplayName("이동할 칸 수 만큼 증가한 랭크를 반환한다.")
    void findNextRank(Rank given, int offset, Rank expected) {
        //when
        Rank result = given.findNextRank(offset);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"TWO,-6", "ONE,20", "EIGHT,1"})
    @DisplayName("이동할 수 없다면 예외가 발생한다.")
    void findNextRankExceedRange(Rank given, int offset) {
        //when //then
        assertThatThrownBy(() -> given.findNextRank(offset))
                .isInstanceOf(IllegalStateException.class);
    }
}
