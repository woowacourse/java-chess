package chess.piece;

import java.util.HashMap;
import java.util.Map;
import chess.Color;
import chess.Fixtures;
import chess.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    @DisplayName("폰은 한칸 전진하거나 두칸 전진할 수 있다")
    @Test
    void pawnStraightMoveTest() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        // when
        boolean actual1 = pawn.isAbleToMove(Fixtures.A2, Fixtures.A3, new HashMap<>());
        boolean actual2 = pawn.isAbleToMove(Fixtures.A2, Fixtures.A4, new HashMap<>());

        // then
        Assertions.assertThat(actual1).isTrue();
        Assertions.assertThat(actual2).isTrue();
    }

    @DisplayName("폰은 이동할 수 없는 위치일 경우 false를 반환한다")
    @Test
    void pawnCantMoveExceptionTest() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        // when
        boolean actual1 = pawn.isAbleToMove(Fixtures.A2, Fixtures.A5, new HashMap<>());
        boolean actual2 = pawn.isAbleToMove(Fixtures.A2, Fixtures.B3, new HashMap<>());

        // then
        Assertions.assertThat(actual1).isFalse();
        Assertions.assertThat(actual2).isFalse();
    }

    @DisplayName("흑팀일 경우 폰은 아래로 전진한다.")
    @Test
    void pawnBlackTest() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        // when
        boolean actual1 = pawn.isAbleToMove(Fixtures.A7, Fixtures.A6, new HashMap<>());
        boolean actual2 = pawn.isAbleToMove(Fixtures.A7, Fixtures.A5, new HashMap<>());

        // then
        Assertions.assertThat(actual1).isTrue();
        Assertions.assertThat(actual2).isTrue();
    }

    @DisplayName("폰이 흑팀일때 이동할 수 없는 위치일 경우 false를 반환한다.")
    @Test
    void pawnBlackWrongPosTest() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        // when
        boolean actual1 = pawn.isAbleToMove(Fixtures.A2, Fixtures.A3, new HashMap<>());
        boolean actual2 = pawn.isAbleToMove(Fixtures.A2, Fixtures.A4, new HashMap<>());

        // then
        Assertions.assertThat(actual1).isFalse();
        Assertions.assertThat(actual2).isFalse();
    }

    @DisplayName("이동하고자 하는 위치에 기물이 있을 경우, 이동하지 못한다")
    @Test
    void testWhenDestPositionHasPiece() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);
        Map<Position, Piece> fakeBoard = Map.of(
                Fixtures.A3, new Pawn(Color.BLACK)
        );

        // when
        boolean actual = pawn.isAbleToMove(Fixtures.A2, Fixtures.A3, fakeBoard);

        // then
        Assertions.assertThat(actual).isFalse();
    }

    @DisplayName("대각선 방향으로 한칸 전진은, 해당 위치에 적 기물이 있을때만 허용된다.")
    @Test
    void diagonalMoveTest() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);
        Map<Position, Piece> fakeBoard = Map.of(
                Fixtures.B3, new Pawn(Color.BLACK)
        );

        // when
        boolean actual1 = pawn.isAbleToMove(Fixtures.A2, Fixtures.B3, fakeBoard);
        boolean actual2 = pawn.isAbleToMove(Fixtures.A2, Fixtures.B3, new HashMap<>());


        // then
        Assertions.assertThat(actual1).isTrue();
        Assertions.assertThat(actual2).isFalse();
    }

    @DisplayName("대각선 방향 한칸 전진에 대해, 흑팀은 아래로 전진한다.")
    @Test
    void diagonalMoveTestForBlack() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);
        Map<Position, Piece> fakeBoard = Map.of(
                Fixtures.B6, new Pawn(Color.WHITE)
        );

        // when
        boolean actual1 = pawn.isAbleToMove(Fixtures.A7, Fixtures.B6, fakeBoard);
        boolean actual2 = pawn.isAbleToMove(Fixtures.A7, Fixtures.B6, new HashMap<>());


        // then
        Assertions.assertThat(actual1).isTrue();
        Assertions.assertThat(actual2).isFalse();
    }

    @DisplayName("대각선 방향 전진에서, 먹고자 하는 기물이 아군일 경우 이동하지 못한다.")
    @Test
    void diagonalMoveToOurTeamTest() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);
        Map<Position, Piece> fakeBoard = Map.of(
                Fixtures.B6, new Pawn(Color.BLACK)
        );

        // when
        boolean actual1 = pawn.isAbleToMove(Fixtures.A7, Fixtures.B6, fakeBoard);


        // then
        Assertions.assertThat(actual1).isFalse();
    }
}
