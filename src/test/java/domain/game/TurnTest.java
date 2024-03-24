package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {
    @Test
    @DisplayName("초기 턴은 흰색 팀부터 시작한다.")
    void initialTurnTest() {
        Turn turn = new Turn();
        assertThat(turn.current()).isEqualTo(TeamColor.WHITE);
    }

    @Test
    @DisplayName("턴을 토글할 수 있다.")
    void nextTurnTest() {
        // Given
        Turn turn = new Turn();

        // When
        turn.next();

        // Then
        assertThat(turn.current()).isEqualTo(TeamColor.BLACK);
    }
}
