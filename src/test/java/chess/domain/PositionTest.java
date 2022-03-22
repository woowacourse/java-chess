package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

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
}
