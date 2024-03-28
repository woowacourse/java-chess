package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeamTest {

    @Test
    @DisplayName("다음 팀을 찾을 수 있다.")
    void findNextTeamTest() {
        assertAll(
                () -> assertThat(Team.BLACK.next()).isEqualTo(Team.WHITE),
                () -> assertThat(Team.WHITE.next()).isEqualTo(Team.BLACK)
        );
    }
}
