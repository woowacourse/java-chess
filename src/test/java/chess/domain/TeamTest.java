package chess.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeamTest {

    @Test
    @DisplayName("같은 팀이면 true 다른 팀이면 false 를 반환한다.")
    void isSameTeam() {
        Team team = Team.WHITE;
        assertAll(
                () -> Assertions.assertThat(team.isSameTeam(Team.BLACK)).isFalse(),
                () -> Assertions.assertThat(team.isSameTeam(Team.WHITE)).isTrue()
        );
    }
}
