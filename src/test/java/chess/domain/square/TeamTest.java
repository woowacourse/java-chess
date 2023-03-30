package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamTest {
    @Test
    @DisplayName("진영 색을 입력 받아 생성한다.")
    void create() {
        // given
        Color color = Color.BLACK;

        // when
        Team team = Team.from(color);

        // expected
        assertThat(team).isNotNull();
    }

    @Test
    @DisplayName("현재 진영의 반대 진영을 찾는다.")
    void findOpponent() {
        // given
        Team whiteTeam = Team.from(Color.WHITE);
        Team blackTeam = Team.from(Color.BLACK);

        // expected
        assertThat(whiteTeam.findOpponent()).isEqualTo(Team.from(Color.BLACK));
        assertThat(blackTeam.findOpponent()).isEqualTo(Team.from(Color.WHITE));
    }
}
