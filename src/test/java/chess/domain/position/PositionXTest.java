package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionXTest {

    @ParameterizedTest
    @ValueSource(strings = {"9", "k"})
    @DisplayName("존재하지 않는 이름으로 탐색할 경우 예외를 처리하는지")
    void invalidNameException(String name) {
        assertThatThrownBy(() -> PositionX.of(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
