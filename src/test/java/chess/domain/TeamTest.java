package chess.domain;

import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TeamTest {

    @Test
    @DisplayName("흰 팀의 색깔을 올바르게 판단하는 지 확인한다.")
    void whiteTeamTest() {
        assertThat(Team.WHITE.isWhite()).isTrue();
        assertThat(Team.WHITE.isBlack()).isFalse();
    }

    @Test
    @DisplayName("검은 팀의 색깔을 올바르게 판단하는 지 확인한다.")
    void blackTeamTest() {
        assertThat(Team.BLACK.isWhite()).isFalse();
        assertThat(Team.BLACK.isBlack()).isTrue();
    }
}
