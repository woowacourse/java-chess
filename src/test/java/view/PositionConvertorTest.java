package view;

import static domain.Fixture.Positions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PositionConvertorTest {
    @ParameterizedTest
    @MethodSource("validInput")
    @DisplayName("유효한 위치 문자열이 입력된 경우 Position 객체로 변환한다.")
    void validInputTest(String input, Position expected) {
        Position actual = PositionConvertor.convertPosition(input);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> validInput() {
        return Stream.of(
                Arguments.of("a8", A8),
                Arguments.of("a3", A3),
                Arguments.of("f5", F5)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"hi", "hello", "A3", "F9"})
    @DisplayName("유효하지 않은 위치 문자열이 입력된 경우 예외가 발생한다.")
    void invalidInputTest(String input) {
        assertThatThrownBy(() -> PositionConvertor.convertPosition(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치입니다.");
    }
}
