package chess.domain.piece;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {
    @DisplayName("흰색 폰이 시작 위치에서 두 칸 이동")
    @Test
    void whitePawnMovableOnInitialPositionTwoTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.WHITE);
        Position initialSource = Position.of("a2");
        Position directTwo = Position.of("a4");

        Assertions.assertThat(whitePawn.movable(initialSource, directTwo)).isTrue();
    }

    @DisplayName("흰색 폰이 시작 위치에서 한 칸 이동")
    @Test
    void whitePawnMovableOnInitialPositionOneTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.WHITE);
        Position initialSource = Position.of("a2");
        Position directOne = Position.of("a3");

        Assertions.assertThat(whitePawn.movable(initialSource, directOne)).isTrue();
    }

    @DisplayName("흰색 폰이 시작 위치에서 갈 수 없는 곳으로 이동")
    @Test
    void whitePawnNonMovableOnInitialPositionTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.WHITE);
        Position initialSource = Position.of("a2");
        Position nonMovableInitial = Position.of("a5");

        Assertions.assertThat(whitePawn.movable(initialSource, nonMovableInitial)).isFalse();
    }

    @DisplayName("흰색 폰이 시작 위치가 아닌 곳에서 대각으로 공격")
    @Test
    void whitePawnDiagonalMovableTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.WHITE);
        Position nonInitialSource = Position.of("b3");
        Position diagonalTarget = Position.of("c4");

        Assertions.assertThat(whitePawn.movable(nonInitialSource, diagonalTarget)).isTrue();
    }

    @DisplayName("흰색 폰이 시작 위치가 아닌 곳에서 직진 이동")
    @Test
    void whitePawnDirectMovableTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.WHITE);
        Position nonInitialSource = Position.of("b3");
        Position directTarget = Position.of("b4");

        Assertions.assertThat(whitePawn.movable(nonInitialSource, directTarget)).isTrue();
    }

    @DisplayName("흰색 폰이 시작 위치가 아닌 곳에서 갈수 없는 곳으로 이동")
    @Test
    void whitePawnNonMovableTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.WHITE);
        Position nonInitialSource = Position.of("b3");
        Position nonMovableTarget = Position.of("b5");

        Assertions.assertThat(whitePawn.movable(nonInitialSource, nonMovableTarget)).isFalse();
    }

    @DisplayName("검은색 폰이 시작 위치에서 두 칸 이동")
    @Test
    void blackPawnMovableOnInitialPositionTwoTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.BLACK);
        Position initialSource = Position.of("a7");
        Position directTwo = Position.of("a5");

        Assertions.assertThat(whitePawn.movable(initialSource, directTwo)).isTrue();
    }

    @DisplayName("검은색 폰이 시작 위치에서 한 칸 이동")
    @Test
    void blackPawnMovableOnInitialPositionOneTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.BLACK);
        Position initialSource = Position.of("a7");
        Position directOne = Position.of("a6");

        Assertions.assertThat(whitePawn.movable(initialSource, directOne)).isTrue();
    }

    @DisplayName("검은색 폰이 시작 위치에서 갈 수 없는 곳으로 이동")
    @Test
    void blackPawnNonMovableOnInitialPositionTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.BLACK);
        Position initialSource = Position.of("a7");
        Position nonMovableInitial = Position.of("a4");

        Assertions.assertThat(whitePawn.movable(initialSource, nonMovableInitial)).isFalse();
    }

    @DisplayName("검은색 폰이 시작 위치가 아닌 곳에서 대각으로 공격")
    @Test
    void blackPawnDiagonalMovableTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.BLACK);
        Position nonInitialSource = Position.of("b6");
        Position diagonalTarget = Position.of("c5");

        Assertions.assertThat(whitePawn.movable(nonInitialSource, diagonalTarget)).isTrue();
    }

    @DisplayName("검은색 폰이 시작 위치가 아닌 곳에서 직진 이동")
    @Test
    void blackPawnDirectMovableTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.BLACK);
        Position nonInitialSource = Position.of("b6");
        Position directTarget = Position.of("b5");

        Assertions.assertThat(whitePawn.movable(nonInitialSource, directTarget)).isTrue();
    }

    @DisplayName("검은색 폰이 시작 위치가 아닌 곳에서 갈수 없는 곳으로 이동")
    @Test
    void blackPawnNonMovableTest() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.BLACK);
        Position nonInitialSource = Position.of("b6");
        Position nonMovableTarget = Position.of("b4");

        Assertions.assertThat(whitePawn.movable(nonInitialSource, nonMovableTarget)).isFalse();
    }
}
