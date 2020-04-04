package chess.domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeamTest {
    @DisplayName("팀 순서 변경 테스트")
    @Test
    void changeTeam() {
        Team blackTeam = Team.BLACK;
        Team whiteTeam = Team.WHITE;

        Team blackExpected = blackTeam.changeTeam();
        Team whiteExpected = whiteTeam.changeTeam();

        Assertions.assertThat(blackExpected).isEqualTo(Team.WHITE);
        Assertions.assertThat(whiteExpected).isEqualTo(Team.BLACK);
    }
}