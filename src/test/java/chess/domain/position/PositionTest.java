package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @DisplayName("fileIdx와 rankIdx를 받는 of 메서드는 대응되는 문자열로 받는 of 메서드와 동일한 인스턴스를 반환한다.")
    @Test
    void of() {
        Position actual = Position.of(2, 0);

        Position expected = Position.of("c1");

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("동일한 위치 정보로 위치 인스턴스를 생성하는 경우, 캐쉬된 위치 인스턴스가 반환된다.")
    @Test
    void of_returnsCache() {
        Position actual = Position.of("a1");
        Position expected = Position.of("a1");

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("범위를 벗어한 위치 정보를 입력하는 경우 예외가 발생한다.")
    @Test
    void of_exceptionOnInvalidRange() {
        assertThatCode(() -> Position.of("z0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 포지션입니다. (a1~h8)");
    }
}
