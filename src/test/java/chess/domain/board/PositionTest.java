package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @Test
    @DisplayName("정상적인 위치면 예외를 발생시키지 않는다.")
    void throws_not_exception_when_case_is_normally() {
        // when & then
        assertDoesNotThrow(
                () -> Position.from("b2")
        );
    }

    @ParameterizedTest(name = "{0}과 같이 {displayName}")
    @ValueSource(strings = {"Z2", "z2", "m0", "a0", "z1"})
    @DisplayName("잘못된 위치면 예외를 발생시킨다.")
    void throws_exception_when_case_is_invalid(final String input) {
        // when & then
        assertThatThrownBy(
                () -> Position.from(input)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
