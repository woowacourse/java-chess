package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnTest {
    @Nested
    @DisplayName("changeTurn 메서드는 호출되면")
    class changeTurn {
        @ParameterizedTest(name = "{0}의 반대 진영인 {1}을 반환한다")
        @CsvSource({"WHITE,BLACK", "BLACK,WHITE"})
        void it_returns_opposite_turn(Turn turn1, Turn turn2) {
            assertThat(turn1.changeTurn()).isEqualTo(turn2);
        }
    }
}
