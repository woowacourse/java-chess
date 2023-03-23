package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeamTest {

    @Test
    @DisplayName("팀이 중립인지 확인해준다.")
    void 팀이_중립인지_확인해준다() {
        Team team = Team.BLACK;

        assertThat(team.isNeutrality()).isFalse();
    }

    @Test
    @DisplayName("같은 팀인지 확인해준다.")
    void 같은_팀인지_확인해준다() {
        Team team = Team.WHITE;

        assertThat(team.isAlly(Team.WHITE)).isTrue();
    }
}
