package util;

import chess.util.NullValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NullValidatorTest {

    @ParameterizedTest
    @DisplayName("null이 입력되면 예외가 발생해야 함")
    @NullSource
    void inputNullThenThrowException(String input) {
        assertThatThrownBy(() -> NullValidator.validateNull(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null이 들어왔습니다.");
    }
}
