package chess.domain.move;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinateTest {
    @Test
    @DisplayName("Coordinate 생성")
    void create() {
        assertThat(Coordinate.of(1)).isInstanceOf(Coordinate.class);
    }

    @Test
    @DisplayName("getCoordinate 테스트")
    void getCoordinate() {
        assertThat(Coordinate.of(1).getCoordinate()).isEqualTo(1);
    }
}
