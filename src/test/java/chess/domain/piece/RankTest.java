package chess.domain.piece;

import chess.domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @DisplayName("int입력받아 Rank을 생성한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, ONE", "2, TWO", "3, THREE", "4, FOUR", "5, FIVE", "6, SIX", "7, SEVEN", "8, EIGHT"})
    void from(final int input, final Rank expected) {
        // given && when
        final Rank rank = Rank.fromSymbol(input);

        // then
        Assertions.assertThat(rank).isEqualTo(expected);
    }

    @DisplayName("유효하지 않는 int을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 9, 10, -1})
    void invalidRank(final int input) {
        Assertions.assertThatThrownBy(() -> Rank.fromSymbol(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효한 랭크 입력이 아닙니다.");
    }
}
