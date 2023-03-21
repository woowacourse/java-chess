package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("킹의 팀을 검증한다.")
    void validateTeamTest_exception() {
        Team team = Team.NEUTRALITY;

        assertThatIllegalArgumentException().isThrownBy(
                () -> new King(team)
        ).withMessage("킹은 중립일 수 없습니다.");
    }
}
