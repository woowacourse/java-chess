package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RowTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("범위를 벗어난 좌표에 대해 예외를 처리하는지")
    void outOfRangeException(int rank) {
        assertThatThrownBy(() -> Row.of(rank))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"9", "a"})
    @DisplayName("존재하지 않는 이름으로 탐색할 경우 예외를 처리하는지")
    void invalidNameException(String name) {
        assertThatThrownBy(() -> Row.of(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
