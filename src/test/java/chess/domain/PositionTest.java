package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("위치")
public class PositionTest {

    @DisplayName("위치를 생성한다.")
    @Test
    void createPosition() {
        // when & then
        assertThatCode(() -> new Position('a', 3)).doesNotThrowAnyException();
    }
}
