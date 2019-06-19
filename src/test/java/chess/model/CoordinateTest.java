package chess.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CoordinateTest {
    @Test
    void 좌표_범위를_벗어나는지_확인() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Coordinate.valueOf(9));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Coordinate.valueOf(0));
    }

    @Test
    void 제대로_인스턴스_가져오는지_확인() {
        // TODO: 2019-06-18 더 좋은 테스트 방법있는지 확인
        assertThat(Coordinate.valueOf(1)).isEqualTo(Coordinate.valueOf(1));
    }
}
