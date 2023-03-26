package chess.domain.board.coordinate;

import chess.domain.direction.Direction;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RowTest {
    @ParameterizedTest(name = "row : {0}")
    @ValueSource(ints = {1, 8})
    void row가_1부터_8까지_들어오면_정상_작동(int row) {
        assertThatNoException()
                .isThrownBy(() -> Row.from(row));
    }

    @ParameterizedTest(name = "row : {0}")
    @ValueSource(ints = {0, 9})
    void row가_1부터_8까지의_범위를_벗어나면_예외_처리(int row) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Row.from(row))
                .withMessage("Row는 1~8까지의 숫자만 가능합니다. 다시 입력해주세요.");
    }
    
    @Test
    void 더한_값_반환() {
        assertThat(Row.FIVE.add(1)).isEqualTo(6);
    }
    
    @Test
    void 현재_값이_해당_값보다_이하인지_확인() {
        assertAll(
                () -> assertThat(Row.FIVE.isLessOrEqualTo(5)).isTrue(),
                () -> assertThat(Row.FIVE.isLessOrEqualTo(6)).isTrue()
        );
    }
    
    @Test
    void 현재_값이_해당_값보다_이상인지_확인() {
        assertAll(
                () -> assertThat(Row.FIVE.isOverOrEqualTo(5)).isTrue(),
                () -> assertThat(Row.FIVE.isOverOrEqualTo(4)).isTrue()
        );
    }
    
    @Test
    void 다음_row가_범위를_초과하지_않는지_확인() {
        assertAll(
                () -> assertThat(Row.SEVEN.isNextRowInRange(Direction.NORTH_NORTH)).isFalse(),
                () -> assertThat(Row.SIX.isNextRowInRange(Direction.NORTH_NORTH)).isTrue()
        );
    }
    
    @Test
    void 처음_시작할_때_기물과_연관된_row인지_확인() {
        assertAll(
                () -> assertThat(Row.SEVEN.isPieceLine()).isTrue(),
                () -> assertThat(Row.SIX.isPieceLine()).isFalse()
        );
    }
    
    @Test
    void 현재_row와_direction의_row를_합친_row_사이의_row값_반환() {
        Row row = Row.FOUR.betweenRow(2);
        assertThat(row).isEqualTo(Row.FIVE);
    }
}
