package domain;

import chess.domain.Column;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ColumnTest {

    @Test
    void 인덱스_오름차순으로_정렬된_Column_목록을_반환한다() {
        //given, when
        List<Column> expected = List.of(Column.A, Column.B, Column.C, Column.D, Column.E, Column.F, Column.G, Column.H);
        List<Column> actual = Column.getOrderedColumns();

        //then
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void 입력한값이_Column에_존재하지않는경우_예외() {
        //given,when
        String value = "z";

        //then
        assertThatThrownBy(() -> Column.findColumnByValue(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 Column입니다");
    }

    @Test
    void 입력한값이_Column에_존재하면_아무일도_일어나지_않는다() {
        //given, when
        String value = "a";

        //then
        assertDoesNotThrow(() -> Column.findColumnByValue(value));
    }
}
