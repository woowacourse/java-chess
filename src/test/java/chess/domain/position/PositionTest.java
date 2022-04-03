package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @DisplayName("동일한 위치 정보로 위치 인스턴스를 생성하는 경우, 캐쉬된 위치 인스턴스가 반환된다.")
    @Test
    void of_returnsCache() {
        Position actual = Position.of("a1");
        Position expected = Position.of("a1");

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("범위를 벗어한 파일 정보를 입력하는 경우 예외가 발생한다.")
    @Test
    void of_exceptionOnInvalidRange() {
        assertThatCode(() -> Position.of("z0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 파일입니다. (파일은 a~h까지 입력 가능합니다.)");
    }

}
