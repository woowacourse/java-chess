package chess.piece;

import static chess.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.Position;
import chess.TeamColor;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("퀸은 대각선으로 이동 가능")
    @Test
    void Queen_canMove_diagonal() {
        // given
        Queen queen = new Queen(TeamColor.BLACK);

        // when
        boolean canMove = queen.availablePath(A1, C3);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸은 직선으로 이동 가능")
    @Test
    void Queen_canMove_Straight() {
        // given
        Queen queen = new Queen(TeamColor.BLACK);

        // when
        boolean canMove = queen.availablePath(A1, A3);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸은 직선이나 대각선이 아닌 곳으로는 이동 불가능")
    @Test
    void Queen_cannotMove() {
        // given
        Queen queen = new Queen(TeamColor.BLACK);

        // when
        boolean canMove = queen.availablePath(A1, B3);

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("퀸의 이동경로에 있는 모든 좌표 반환")
    @Test
    void Queen_findAllRoute() {
        // given
        Queen queen = new Queen(TeamColor.BLACK);

        // when
        List<Position> routes = queen.findAllRouteToTarget(D4, G7);

        // then
        assertThat(routes).hasSize(3);
    }

}