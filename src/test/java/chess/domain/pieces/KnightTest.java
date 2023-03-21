package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("나이트의 팀을 검증한다.")
    void validateTeamTest_exception() {
        Team team = Team.NEUTRALITY;

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Knight(team)
        ).withMessage("나이트는 중립일 수 없습니다.");
    }
}
