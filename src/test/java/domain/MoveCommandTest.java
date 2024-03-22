package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoveCommandTest {
    @DisplayName("출발지와 목적지 입력이 올바른지 검증한다.")
    @ParameterizedTest
    @ValueSource(strings = {"b0", "i5", "h9"})
    void moveCommand(final String input) {
        //then & when
        Assertions.assertThatCode(() -> MoveCommand.fromInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
