package chess.model.board;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {
    @ParameterizedTest
    @CsvSource(value = {"0,0", "9,9", "1,9"})
    void x와_y가_범위_내_값이_아니면_예외가_발생한다(int row, int column) {
        // when, then
        assertThatThrownBy(() -> Position.from(x, y))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1", "2,3", "8,8"})
    void x와_y가_범위_내_값이면_예외가_발생하지_않는다(int row, int column) {
        // when, then
        assertThatCode(() -> Position.from(x, y)).doesNotThrowAnyException();
    }
}
