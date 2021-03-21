package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @DisplayName("Position이 정상적으로 생성되는 경우")
    @Test
    void position_generate() {
        Position position = new Position(0, 0);
        assertThat(position.getRow()).isEqualTo(0);
        assertThat(position.getColumn()).isEqualTo(0);
    }

    @DisplayName("String의 위치를 입력하면 Index를 반환해준다.")
    @ParameterizedTest
    @CsvSource(value = {"a7:1:0", "c6:2:2", "f3:5:5", "h2:6:7"}, delimiter = ':')
    public void string_to_index_position(String input, int x, int y) {
        Position position = Position.of(input);
        assertThat(position.getRow()).isEqualTo(x);
        assertThat(position.getColumn()).isEqualTo(y);
    }
}