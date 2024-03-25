package chess.view.mapper;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.board.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RowMapperTest {

    @Test
    @DisplayName("이름으로 Row를 찾는다.")
    void findByInputValueSuccessTest() {
        assertAll(
                () -> assertEquals(Row.ONE, RowMapper.findByInputValue("1")),
                () -> assertEquals(Row.TWO, RowMapper.findByInputValue("2")),
                () -> assertEquals(Row.THREE, RowMapper.findByInputValue("3")),
                () -> assertEquals(Row.FOUR, RowMapper.findByInputValue("4")),
                () -> assertEquals(Row.FIVE, RowMapper.findByInputValue("5")),
                () -> assertEquals(Row.SIX, RowMapper.findByInputValue("6")),
                () -> assertEquals(Row.SEVEN, RowMapper.findByInputValue("7")),
                () -> assertEquals(Row.EIGHT, RowMapper.findByInputValue("8"))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"   ", "zxc", "123"})
    @DisplayName("이름으로 Row를 찾는데 실패한다.")
    void findByInputValueFailTest(String value) {
        assertThatThrownBy(() -> RowMapper.findByInputValue(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
