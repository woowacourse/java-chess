package chess.piece;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import chess.Color;
import chess.Fixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {
    @DisplayName("비숍은 대각선 상으로만 이동할 수 있다")
    @Test
    void bishopMoveTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);

        // when
        boolean actual = bishop.isAbleToMove(
                Fixtures.A1, Fixtures.C3, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }

    @DisplayName("비숍은 대각선이 아닌 경로로 이동 불가능하다")
    @Test
    void bishopNotMoveTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);

        // when
        boolean actual = bishop.isAbleToMove(
                Fixtures.A1, Fixtures.C4, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("비숍은 이동 중 충돌 시 이동 불가능하다")
    @Test
    void bishopCrashTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);

        // when
        boolean actual = bishop.isAbleToMove(
                Fixtures.A1, Fixtures.C3, Map.of(Fixtures.B2, new Pawn(Color.WHITE))
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("비숍은 모든 4방향에 대해 대각선 이동 가능하다")
    @Test
    void bishopVariousMoveTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);

        // when
        boolean rightUp = bishop.isAbleToMove(
                Fixtures.A1, Fixtures.C3, new HashMap<>()
        );
        boolean rightDown = bishop.isAbleToMove(
                Fixtures.C3, Fixtures.A1, new HashMap<>()
        );
        boolean leftUp = bishop.isAbleToMove(
                Fixtures.C1, Fixtures.A3, new HashMap<>()
        );
        boolean leftDown = bishop.isAbleToMove(
                Fixtures.C3, Fixtures.A1, new HashMap<>()
        );

        // then
        Assertions.assertThat(rightUp && rightDown && leftUp && leftDown).isTrue();
    }

    @DisplayName("비숍은 모든 방향에 대해 대각선이 아니면 이동 불가능하다")
    @Test
    void bishopVariousNotMoveTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);

        // when
        boolean rightUp = bishop.isAbleToMove(
                Fixtures.A1, Fixtures.C4, new HashMap<>()
        );
        boolean rightDown = bishop.isAbleToMove(
                Fixtures.C3, Fixtures.B1, new HashMap<>()
        );
        boolean leftUp = bishop.isAbleToMove(
                Fixtures.C1, Fixtures.F3, new HashMap<>()
        );
        boolean leftDown = bishop.isAbleToMove(
                Fixtures.C4, Fixtures.A1, new HashMap<>()
        );

        // then
        Assertions.assertThat(rightUp || rightDown || leftUp || leftDown).isFalse();
    }

    @DisplayName("비숍의 도착 지점에 아군 기물이 있다면 이동 불가능하다")
    @Test
    void bishopWillNotKillOurTeam() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);

        // when
        boolean actual = bishop.isAbleToMove(
                Fixtures.A1, Fixtures.C3, Map.of(Fixtures.C3, new Pawn(Color.WHITE))
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("비숍의 도착 지점에 적군 기물이 있다면 이동 불가능하다")
    @Test
    void bishopWillKillEnemyTeam() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);

        // when
        boolean actual = bishop.isAbleToMove(
                Fixtures.A1, Fixtures.C3, Map.of(Fixtures.C3, new Pawn(Color.BLACK))
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }
}
