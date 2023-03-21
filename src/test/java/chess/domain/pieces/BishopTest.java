package chess.domain.pieces;

import static chess.domain.math.Direction.DOWN_LEFT;
import static chess.domain.math.Direction.DOWN_RIGHT;
import static chess.domain.math.Direction.UP;
import static chess.domain.math.Direction.UP_LEFT;
import static chess.domain.math.Direction.UP_RIGHT;
import static chess.domain.pieces.Piece.INVALID_TEAM;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.Team;
import chess.domain.math.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class BishopTest {

    @Test
    @DisplayName("비숍의 방향을 검증한다.")
    void hasPattern_success() {
        Bishop bishop = new Bishop(Team.BLACK);
        List<Direction> directions = List.of(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);

        for (Direction direction : directions) {
            assertThatNoException().isThrownBy(
                    () -> bishop.validateDirection(direction)
            );
        }
    }

    @Test
    @DisplayName("비숍의 방향을 검증한다.")
    void hasPattern_fail() {
        Bishop bishop = new Bishop(Team.BLACK);

        assertThatIllegalArgumentException().isThrownBy(
                () -> bishop.validateDirection(UP)
        );
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
