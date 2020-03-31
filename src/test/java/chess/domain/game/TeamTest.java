package chess.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.game.Team.BLACK;
import static chess.domain.game.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

public class TeamTest {
    @Test
    @DisplayName("getOpponentTeam 테스트")
    void getOpponentTeam() {
        assertThat(Team.getOpponentTeam(BLACK)).isEqualTo(WHITE);
    }

    @Test
    @DisplayName("getTeamName 테스트")
    void getTeamName() {
        assertThat(BLACK.getTeamName()).isEqualTo("Black Team");
    }

}
