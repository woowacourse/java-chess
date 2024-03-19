package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Coordinate(1, 'a'))
                .doesNotThrowAnyException();
    }
}
