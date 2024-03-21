package chess.domain.mapper;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.Row;
import chess.view.RowMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RowMapperTest {

    @Test
    @DisplayName("이름으로 Row를 찾는다.")
    void findByInputValueSuccessTest() {
        assertAll(
                () -> assertEquals(Row.RANK1, RowMapper.findByInputValue("1")),
                () -> assertEquals(Row.RANK2, RowMapper.findByInputValue("2")),
                () -> assertEquals(Row.RANK3, RowMapper.findByInputValue("3")),
                () -> assertEquals(Row.RANK4, RowMapper.findByInputValue("4")),
                () -> assertEquals(Row.RANK5, RowMapper.findByInputValue("5")),
                () -> assertEquals(Row.RANK6, RowMapper.findByInputValue("6")),
                () -> assertEquals(Row.RANK7, RowMapper.findByInputValue("7")),
                () -> assertEquals(Row.RANK8, RowMapper.findByInputValue("8"))
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
