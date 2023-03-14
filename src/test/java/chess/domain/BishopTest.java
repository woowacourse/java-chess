package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Bishop 클래스")
public class BishopTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("진영이 주어지면")
        class givenTeam {
            @Test
            @DisplayName("해당 진영의 Bishop을 2개 생성한다")
            void it_returns_bishops() {
                assertThat(Bishop.of(Team.BLACK)).hasSize(2);
            }
        }
    }
}
