package chess.piece;

import java.util.HashMap;
import java.util.Map;
import chess.Color;
import chess.Fixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("룩은 일자로 쭉 이동할 수 있다")
    @Test
    void rookMoveTest() {
        // given
        Rook rook = new Rook(Color.WHITE);

        // when
        boolean actual = rook.isAbleToMove(
                Fixtures.A1, Fixtures.A8, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }

    @DisplayName("룩은 이동 중에 기물과 부딪히면 이동하지 못한다.")
    @Test
    void rookMoveCrashTest() {
        // given
        Rook rook = new Rook(Color.WHITE);

        // when
        boolean actual = rook.isAbleToMove(
                Fixtures.A1, Fixtures.A8, Map.of(Fixtures.A5, new Pawn(Color.WHITE))
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("룩은 불가능한 위치로 이동하지 못한다")
    @Test
    void rookNotMoveTest() {
        // given
        Rook rook = new Rook(Color.WHITE);

        // when
        boolean actual = rook.isAbleToMove(
                Fixtures.A1, Fixtures.B8, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("룩의 도착지점에 아군이 있으면 이동하지 못한다")
    @Test
    void rookDestinationHasOurTeamPieceTest() {
        // given
        Rook rook = new Rook(Color.WHITE);

        // when
        boolean actual = rook.isAbleToMove(
                Fixtures.A1, Fixtures.B8, Map.of(Fixtures.B8, new Pawn(Color.WHITE))
        );

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("룩의 도착지점에 적군이 있으면 이동 가능하다")
    @Test
    void rookDestinationHasEnemyTeamPieceTest() {
        // given
        Rook rook = new Rook(Color.WHITE);

        // when
        boolean actual = rook.isAbleToMove(
                Fixtures.A1, Fixtures.A8, Map.of(Fixtures.A8, new Pawn(Color.BLACK))
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }

    @DisplayName("직진이 아니라 옆으로도 같은 라인이라면 이동 가능하다")
    @Test
    void anotherMovingTest() {
        // given
        Rook rook = new Rook(Color.WHITE);

        // when
        boolean actual = rook.isAbleToMove(
                Fixtures.A1, Fixtures.G1, new HashMap<>()
        );

        // then
        Assertions.assertThat(actual).isTrue();
    }
}
