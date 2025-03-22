package chess.piece;

import java.util.HashMap;
import java.util.Map;
import chess.Color;
import chess.Fixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    @DisplayName("퀸은 일자로 쭉 이동할 수 있다")
    @Test
    void queenMoveTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean actual = queen.isAbleToMove(
                Fixtures.A1, Fixtures.A8, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }

    @DisplayName("퀸은 이동 중에 기물과 부딪히면 이동하지 못한다.")
    @Test
    void queenMoveCrashTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean actual = queen.isAbleToMove(
                Fixtures.A1, Fixtures.A8, Map.of(Fixtures.A5, new Pawn(Color.WHITE))
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("퀸은 불가능한 위치로 이동하지 못한다")
    @Test
    void queenNotMoveTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean actual = queen.isAbleToMove(
                Fixtures.A1, Fixtures.B8, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("퀸의 도착지점에 아군이 있으면 이동하지 못한다")
    @Test
    void queenDestinationHasOurTeamPieceTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean actual = queen.isAbleToMove(
                Fixtures.A1, Fixtures.B8, Map.of(Fixtures.B8, new Pawn(Color.WHITE))
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("퀸의 도착지점에 적군이 있으면 이동 가능하다")
    @Test
    void queenDestinationHasEnemyTeamPieceTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean actual = queen.isAbleToMove(
                Fixtures.A1, Fixtures.A8, Map.of(Fixtures.A8, new Pawn(Color.BLACK))
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }

    @DisplayName("직진이 아니라 옆으로도 같은 라인이라면 이동 가능하다")
    @Test
    void anotherMovingTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean actual = queen.isAbleToMove(
                Fixtures.A1, Fixtures.G1, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }


    // 대각선 테스트

    @DisplayName("퀸은 대각선 상으로만 이동할 수 있다")
    @Test
    void queenDiagonalMoveTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean actual = queen.isAbleToMove(
                Fixtures.A1, Fixtures.C3, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }

    @DisplayName("퀸은 대각선이 아닌 경로로 이동 불가능하다")
    @Test
    void queenDiagonalNotMoveTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean actual = queen.isAbleToMove(
                Fixtures.A1, Fixtures.C4, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("퀸은 이동 중 충돌 시 이동 불가능하다")
    @Test
    void queenDiagonalCrashTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean actual = queen.isAbleToMove(
                Fixtures.A1, Fixtures.C3, Map.of(Fixtures.B2, new Pawn(Color.WHITE))
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("퀸은 모든 4방향에 대해 대각선 이동 가능하다")
    @Test
    void queenVariousMoveTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean rightUp = queen.isAbleToMove(
                Fixtures.A1, Fixtures.C3, new HashMap<>()
        );
        boolean rightDown = queen.isAbleToMove(
                Fixtures.C3, Fixtures.A1, new HashMap<>()
        );
        boolean leftUp = queen.isAbleToMove(
                Fixtures.C1, Fixtures.A3, new HashMap<>()
        );
        boolean leftDown = queen.isAbleToMove(
                Fixtures.C3, Fixtures.A1, new HashMap<>()
        );

        // then
        Assertions.assertThat(rightUp && rightDown && leftUp && leftDown).isTrue();
    }

    @DisplayName("퀸은 모든 방향에 대해 대각선이 아니면 이동 불가능하다")
    @Test
    void queenVariousNotMoveTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean rightUp = queen.isAbleToMove(
                Fixtures.A1, Fixtures.C4, new HashMap<>()
        );
        boolean rightDown = queen.isAbleToMove(
                Fixtures.C3, Fixtures.B1, new HashMap<>()
        );
        boolean leftUp = queen.isAbleToMove(
                Fixtures.C1, Fixtures.F3, new HashMap<>()
        );
        boolean leftDown = queen.isAbleToMove(
                Fixtures.C4, Fixtures.A1, new HashMap<>()
        );

        // then
        Assertions.assertThat(rightUp || rightDown || leftUp || leftDown).isFalse();
    }

    @DisplayName("퀸의 도착 지점에 아군 기물이 있다면 이동 불가능하다")
    @Test
    void queenWillNotKillOurTeam() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean actual = queen.isAbleToMove(
                Fixtures.A1, Fixtures.C3, Map.of(Fixtures.C3, new Pawn(Color.WHITE))
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("퀸의 도착 지점에 적군 기물이 있다면 이동 불가능하다")
    @Test
    void queenWillKillEnemyTeam() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        boolean actual = queen.isAbleToMove(
                Fixtures.A1, Fixtures.C3, Map.of(Fixtures.C3, new Pawn(Color.BLACK))
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }
}
