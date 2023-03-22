package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PositionTest {

    @ParameterizedTest
    @DisplayName("위치가 (0,0)에서 (7,7) 사이면 정상적으로 생성된다.")
    @CsvSource({"0,0", "0,7", "7,0", "7,7"})
    void validPositionTest(int column, int row) {
        assertDoesNotThrow(() -> new Position(column, row));
    }

    @ParameterizedTest
    @DisplayName("위치가 (0,0)에서 (7,7) 사이가 아니면 예외처리한다.")
    @CsvSource({"-1,0", "0,-1", "8,0", "7,8"})
    void invalidPositionTest(int column, int row) {
        assertThrows(IllegalArgumentException.class, () -> new Position(column, row));
    }

    @Test
    @DisplayName("(1, 5)을 (3, -2) 방향으로 이동시키면 (4, 3)이다.")
    void moveTest() {
        Position position = new Position(1, 5);
        assertThat(position.move(new RelativePosition(3, -2))).isEqualTo(new Position(4, 3));
    }
}
