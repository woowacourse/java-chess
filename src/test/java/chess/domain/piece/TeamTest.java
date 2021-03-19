package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeamTest {
    @Test
    @DisplayName("상대 팀 이름 확인 기능")
    void oppositeName() {
        assertThat(Team.BLACK.oppositeTeamName()).isEqualTo("white");
        assertThat(Team.WHITE.oppositeTeamName()).isEqualTo("black");
        assertThat(Team.NOTHING.oppositeTeamName()).isEqualTo("");
    }
}