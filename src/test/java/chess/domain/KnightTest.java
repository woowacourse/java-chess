package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Knight 클래스")
public class KnightTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("진영이 주어지면")
        class given_team {
            @Test
            @DisplayName("해당 진영의 Knight를 8개 생성한다")
            void it_returns_knights() {
                assertThat(Knight.of(Team.BLACK)).hasSize(2);
            }
        }
    }
}
