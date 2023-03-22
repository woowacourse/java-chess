package chess.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionCacheTest {

    @ParameterizedTest
    @CsvSource(value = {"a1, 1, 1", "a8, 1, 8", "h1, 8, 1", "h8, 8, 8", "c7, 3, 7", "d4, 4, 4", "e3, 5, 3"})
    @DisplayName("입력값에 맞는 위치 상태를 반환한다.")
    void returnPositionByCommand(String command, int horizontal, int vertical) {
        assertThat(PositionCache.findPosition(command))
                .isEqualTo(Position.initPosition(horizontal, vertical));
    }
}
