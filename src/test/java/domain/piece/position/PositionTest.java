package domain.piece.position;

import domain.exception.InvalidPositionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @DisplayName("Position이 정상적으로 생성되는 경우")
    @Test
    void position_generate() {
        Position position = Position.valueOf(0, 0);
        assertThat(position).isEqualTo(Position.valueOf(0, 0));
    }

    @DisplayName("Position의 인덱스가 잘못 입력 된 경우")
    @ParameterizedTest
    @ValueSource(strings = {"x2", "y3", "b9"})
    void invalid_position_index(String value) {
        assertThatThrownBy(() -> Position.of(value))
                .isInstanceOf(InvalidPositionException.class);
    }

    @DisplayName("입력받은 포지션이 2자를 넘어가는 경우 ")
    @ParameterizedTest
    @ValueSource(strings = {"x22", "y13", "bb9"})
    void invalid_position_input_length(String value) {
        assertThatThrownBy(() -> Position.of(value))
                .isInstanceOf(InvalidPositionException.class);
    }

    @DisplayName("String의 위치를 입력하면 Index를 반환해준다.")
    @ParameterizedTest
    @CsvSource(value = {"a7:1:0", "c6:2:2", "f3:5:5", "h2:6:7"}, delimiter = ':')
    public void string_to_index_position(String input, int x, int y) {
        Position position = Position.of(input);
        assertThat(position).isEqualTo(Position.valueOf(x, y));
    }
}