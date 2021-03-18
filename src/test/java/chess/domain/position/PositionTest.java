package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {
    @Test
    @DisplayName("Poistion을 생성하면, Position 인스턴스가 생성된다.")
    void create() {
        int x = 1;
        int y = 2;
        Position position = new Position(x, y);
        assertThat(position).isInstanceOf(Position.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("x를 -1이랑 8로 넣으면, 예외가 발생한다.")
    void invalidRangeX(final int x) {
        int y = 2;

        assertThatThrownBy(() -> new Position(x, y)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("y를 -1이랑 8로 넣으면, 예외가 발생한다.")
    void invalidRangeY(final int y) {
        int x = 2;

        assertThatThrownBy(() -> new Position(x, y)).isInstanceOf(IllegalArgumentException.class);
    }
}