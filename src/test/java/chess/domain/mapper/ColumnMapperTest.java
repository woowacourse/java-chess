package chess.domain.mapper;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.Column;
import chess.view.ColumnMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ColumnMapperTest {

    @Test
    @DisplayName("이름으로 Column를 찾는다.")
    void findByInputValueSuccessTest() {
        assertAll(
                () -> assertEquals(Column.A, ColumnMapper.findByInputValue("a")),
                () -> assertEquals(Column.B, ColumnMapper.findByInputValue("b")),
                () -> assertEquals(Column.C, ColumnMapper.findByInputValue("c")),
                () -> assertEquals(Column.D, ColumnMapper.findByInputValue("d")),
                () -> assertEquals(Column.E, ColumnMapper.findByInputValue("e")),
                () -> assertEquals(Column.F, ColumnMapper.findByInputValue("f")),
                () -> assertEquals(Column.G, ColumnMapper.findByInputValue("g")),
                () -> assertEquals(Column.H, ColumnMapper.findByInputValue("h"))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"   ", "zxc", "123"})
    @DisplayName("이름으로 Column를 찾는데 실패한다.")
    void findByInputValueFailTest(String value) {
        assertThatThrownBy(() -> ColumnMapper.findByInputValue(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
