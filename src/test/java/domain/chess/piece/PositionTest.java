package domain.chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @DisplayName("Position이 정상적으로 생성되는 경우")
    @Test
    void position_generate() {
        Position position = new Position(0, 0);
        assertThat(position.getRow()).isEqualTo(0);
        assertThat(position.getColumn()).isEqualTo(0);
    }

    @DisplayName("Position의 인덱스가 잘못된 경우 예외처리된다.")
    @ParameterizedTest
    @CsvSource(value = {"-1:5", "0:8", "-4:9"}, delimiter = ':')
    void invalid_index_position(int x, int y) {
        assertThatThrownBy(() -> Position.Of(x, y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치가 잘못되었습니다.(x : " + x + ", y : " + y + ")");
    }
}