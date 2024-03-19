package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("위치")
public class SquareTest {

    @DisplayName("위치를 생성한다.")
    @Test
    void createPosition() {
        // when & then
        assertThatCode(() -> Square.of(File.a, Rank.THREE)).doesNotThrowAnyException();
    }
}
