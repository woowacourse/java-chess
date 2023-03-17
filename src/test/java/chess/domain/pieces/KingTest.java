package chess.domain.pieces;

import static chess.domain.pieces.Piece.INVALID_TEAM;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("룩의 팀을 검증한다.")
    void validateTeamTest_exception() {
        Team team = Team.NEUTRALITY;

        assertThatIllegalArgumentException().isThrownBy(
                () -> new King(team)
        ).withMessage(INVALID_TEAM);
    }
}
