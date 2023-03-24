package chess.domain.pieces;

import chess.domain.pieces.component.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    @Test
    @DisplayName("나이트의 팀을 검증한다.")
    void validateTeamTest_exception() {
        Team team = Team.NEUTRALITY;

        assertThatThrownBy(() -> new Knight(team));
    }
}
