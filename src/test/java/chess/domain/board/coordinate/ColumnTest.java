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
class ColumnTest {
    @ParameterizedTest(name = "column : {0}")
    @ValueSource(chars = {'a', 'h'})
    void column가_a부터_h까지_들어오면_정상_작동(char column) {
        assertThatNoException()
                .isThrownBy(() -> Column.from(column));
    }

    @ParameterizedTest(name = "column : {0}")
    @ValueSource(chars = {'`', 'i', 'z'})
    void column가_a부터_h까지의_범위를_벗어나면_예외_처리(char column) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Column.from(column))
                .withMessage("Column는 a~h까지의 문자만 가능합니다. 다시 입력해주세요.");
    }
    
    @Test
    void 더한_값을_char로_반환() {
        assertThat(Column.A.add(1)).isEqualTo('b');
    }
    
    @Test
    void char_column을_배열_index로_반환() {
        assertThat(Column.B.convertColumnIndex()).isEqualTo(1);
    }
    
    @Test
    void 해당_column이_마지막_column인지_확인() {
        assertAll(
                () -> assertThat(Column.B.isLastColumn()).isFalse(),
                () -> assertThat(Column.H.isLastColumn()).isTrue()
        );
    }
    
    @Test
    void 다음_column이_범위를_초과하지_않는지_확인() {
        assertAll(
                () -> assertThat(Column.B.isNextColumnInRange(Direction.WEST_WEST_SOUTH)).isFalse(),
                () -> assertThat(Column.C.isNextColumnInRange(Direction.WEST_WEST_SOUTH)).isTrue()
        );
    }
}
