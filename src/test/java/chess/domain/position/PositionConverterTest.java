package chess.domain.position;

import chess.domain.position.Position;
import chess.domain.position.PositionConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PositionConverterTest {
    private PositionConverter positionConverter;

    @BeforeEach
    void setUp() {
        positionConverter = new PositionConverter();
    }

    @Test
    @DisplayName("입력 받은 위치를 Position 으로 변환한다.")
    void convert() {
        positionConverter = new PositionConverter();
        assertThat(positionConverter.convert("a7")).isEqualTo(new Position(6, 0));
    }

    @ParameterizedTest(name = "잘못된 길이의 위치 정보가 주어지면, 예외가 발생한다.")
    @ValueSource(strings = {"", "a", "a23", "a234"})
    void convertLengthFail(final String input) {
        assertThatThrownBy(() -> positionConverter.convert(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다.");
    }

    @ParameterizedTest(name = "잘못된 열의 위치 정보가 주어지면, 예외가 발생한다.")
    @ValueSource(strings = {"i1", "i2", "I1", "I2", "00"})
    void convertColumnRangeFail(final String input) {
        assertThatThrownBy(() -> positionConverter.convert(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 위치 열은 a~h 사이여야 합니다.");
    }

    @ParameterizedTest(name = "잘못된 행의 위치 정보가 주어지면, 예외가 발생한다.")
    @ValueSource(strings = {"a0", "a9"})
    void convertRowRangeFail(final String input) {
        assertThatThrownBy(() -> positionConverter.convert(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 위치 행은 1~8 사이여야 합니다.");
    }
}
