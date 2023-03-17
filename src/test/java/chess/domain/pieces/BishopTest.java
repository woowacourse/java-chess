package chess.domain.pieces;

import static chess.domain.pieces.Piece.INVALID_TEAM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.Pattern;
import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("비숍의 패턴을 검증한다.")
    void hasPattern() {
        var bishop = new Bishop(Team.BLACK);

        assertThat(bishop.hasPattern(Pattern.DIAGONAL)).isTrue();
    }

    @Test
    @DisplayName("비숍의 팀을 검증한다.")
    void validateTeamTest_exception() {
        Team team = Team.NEUTRALITY;

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Bishop(team)
        ).withMessage(INVALID_TEAM);
    }
}
