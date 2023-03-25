package chess.domain.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class ColumnTest {

    @Test
    void 인덱스_오름차순으로_정렬된_Column_목록을_반환한다() {
        List<Column> expected = List.of(Column.A, Column.B, Column.C, Column.D, Column.E, Column.F, Column.G, Column.H);

        assertThat(Column.getOrderedColumns()).isEqualTo(expected);
    }

    @Test
    void 입력한_문자열이_Column의_value로_존재하지_않는_경우_예외() {
        String notExistValue = "z";

        assertThatThrownBy(() -> Column.findColumnByValue(notExistValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 Column입니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a","b","c","d","e","f","g","h"})
    void 입력한_문자열이_Column의_value로_존재하면_아무일도_일어나지_않는다(String existValue) {

        assertDoesNotThrow(() -> Column.findColumnByValue(existValue));
    }

    @Test
    void 입력한_인덱스와_일치하는_인덱스가_존재하지_않는_경우_예외() {
        int notExistIndex = 10;

        assertThatThrownBy(() -> Column.findColumnByIndex(notExistIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 Column입니다");
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8})
    void 입력한_인덱스와_일치하는_인덱스가_존재하면_아무일도_일어나지_않는다(int existIndex) {
        assertDoesNotThrow(() -> Column.findColumnByIndex(existIndex));
    }
}