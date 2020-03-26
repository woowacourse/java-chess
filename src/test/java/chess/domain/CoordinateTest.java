package chess.domain;

import chess.domain.move.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoordinateTest {
    @Test
    @DisplayName("Coordinate 생성 테스트")
    void create() {
        assertThat(new Coordinate(1)).isInstanceOf(Coordinate.class);
        assertThat(new Coordinate(8)).isInstanceOf(Coordinate.class);
        assertThat(new Coordinate('a')).isInstanceOf(Coordinate.class);
        assertThat(new Coordinate('h')).isInstanceOf(Coordinate.class);
    }

    @Test
    @DisplayName("Coordinate 생성 테스트 - 올바르지 않은 좌표")
    void create_InvalidInput_ThrowException() {
        assertThatThrownBy(() -> new Coordinate(9)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Coordinate('i')).isInstanceOf(IllegalArgumentException.class);
    }
}
