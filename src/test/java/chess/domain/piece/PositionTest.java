package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PositionTest {
    @Test
    @DisplayName("포지션 객체가 정상적으로 생성됐는 지 예외를 안 던지는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            new Position('a', '1');
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("체스 말의 위치가 Grid 범위 내에 있을 때에는 정상적으로 생성하는 지 테스트")
    @CsvSource(value = {"a:1", "a:2", "g:3", "h:7", "h:8"}, delimiter = ':')
    public void validatePosition(char x, char y) {
        assertThatCode(() -> {
            new Position(x, y);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("체스 말의 위치가 Grid 범위를 벗어나면 예외 출력")
    @CsvSource(value = {"a:9", "i:2"}, delimiter = ':')
    public void validatePosition_ThrowsException(char x, char y) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Position(x, y);
        }).withMessage("체스 말의 위치가 Grid 범위를 벗어났습니다.");
    }
}
