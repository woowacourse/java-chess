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
        int row = 1;
        int col = 2;
        Position position = new Position(row, col);
        assertThat(position).isInstanceOf(Position.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1})
    @DisplayName("row를 -1로 넣으면, 예외가 발생한다.")
    void invalidRangeX(final int row) {
        int col = 2;

        assertThatThrownBy(() -> new Position(row, col)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1})
    @DisplayName("col를 -1로 넣으면, 예외가 발생한다.")
    void invalidRangeY(final int col) {
        int row = 2;

        assertThatThrownBy(() -> new Position(row, col)).isInstanceOf(IllegalArgumentException.class);
    }
}
