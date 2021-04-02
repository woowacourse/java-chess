package chess.domain.pieceinformations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeamColorTest {

    @Test
    @DisplayName("상대팀 반환")
    void counterpart() {
        assertThat(TeamColor.WHITE.counterpart()).isEqualTo(TeamColor.BLACK);
        assertThat(TeamColor.BLACK.counterpart()).isEqualTo(TeamColor.WHITE);
    }

}