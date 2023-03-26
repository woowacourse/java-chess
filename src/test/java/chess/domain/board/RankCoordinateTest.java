package chess.domain.board;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.board.RankCoordinate.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankCoordinateTest {

    @Test
    void 전달한_행_번호에_맞는_rankCoordinate_객체를_알_수_있다() {
        RankCoordinate rankCoordinate = findBy(1);

        assertThat(rankCoordinate).isEqualTo(ONE);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 9, 100})
    void 전달한_행_번호가_1에서_8_범위의_정수가_아니라면_예외를_발생시킨다(int columnNumber) {
        assertThatThrownBy(() -> findBy(columnNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 행 번호를 입력해주세요.");
    }

    @Test
    void RankCoordinate의_rowNumber_기준으로_정렬된_객체를_알_수_있다() {
        assertThat(getSortedRankCoordinates()).containsExactly(EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, ONE);
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE:TWO:1", "ONE:THREE:1", "TWO:ONE:-1", "ONE:ONE:0"}, delimiter = ':')
    void RankCoordinate_객체간의_크기를_비교할_수_있다(RankCoordinate rankCoordinate1, RankCoordinate rankCoordinate2, int expect) {
        assertThat(rankCoordinate1.compare(rankCoordinate2)).isEqualTo(expect);
    }
}
