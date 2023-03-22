package domain.piece.pawn;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.empty.Empty;

class PawnTest {

    @Test
    @DisplayName("pawn이 직선으로 움직일때 첫번째 이동이면, 두칸갈수 있다.")
    void fetchMovableCoordinate() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square currentSquare = new Square(2, 2);
        Square targetSquare = new Square(2, 3);

        List<Square> squares = pawn.fetchMovableSquares(currentSquare, targetSquare);

        assertThat(squares).contains(new Square(2, 3), new Square(2, 4));
    }

    @Test
    @DisplayName("pawn이 직선으로 움직일때 첫번째 이동이 아니면, 한칸만 갈 수 있다.")
    void fetchMovableCoordinateNotFirst() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square currentSquare = new Square(2, 2);
        Square targetSquare = new Square(2, 3);
        Square nextTargetSquare = new Square(2, 4);

        pawn.fetchMovableSquares(currentSquare, targetSquare);
        List<Square> squares = pawn.fetchMovableSquares(targetSquare, nextTargetSquare);

        assertThat(squares).containsExactly(new Square(2, 4));
    }

    @Test
    @DisplayName("pawn이 대각선으로 움직이려하면, 대각선으로 갈수 있는 모든 경로를 반환하다.")
    void fetchMovableCoordinateDiagonal() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square currentSquare = new Square(2, 2);
        Square targetSquare = new Square(3, 3);

        List<Square> squares = pawn.fetchMovableSquares(currentSquare, targetSquare);

        assertThat(squares).contains(new Square(1, 3), new Square(3, 3));
    }

    @Test
    @DisplayName("targetSquare가 갈수없는 경로이면 예외를 던진다.")
    void bishopMoveFailTest() {
        Pawn pawn = new Pawn(Camp.WHITE);
        assertThatThrownBy(() -> pawn.fetchMovableSquares(new Square(1, 3), new Square(2, 5)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Pawn이 움직일 수 없는 경로입니다.");
    }

    @Test
    @DisplayName("폰이 직선으로 두칸 이동하는 경우, 경로에 기물이 없고 targetSquare에 기물이 없으면 true를 반환한다")
    void canMoveTwoStep() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square targetSquare = new Square(7, 7);
        pawn.fetchMovableSquares(new Square(7, 5), targetSquare);
        Map<Square, Piece> pathInfo = new HashMap<>();
        pathInfo.put(targetSquare, Empty.getInstance());
        pathInfo.put(new Square(6, 7), Empty.getInstance());

        boolean result = pawn.canMove(pathInfo, targetSquare);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰이 직선으로 두칸 이동하는 경우, 경로에 기물이 있으면 false를 반환한다")
    void canMoveTwoStepFailByPath() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square targetSquare = new Square(7, 7);
        pawn.fetchMovableSquares(new Square(7, 5), targetSquare);
        Map<Square, Piece> pathInfo = new HashMap<>();
        pathInfo.put(targetSquare, Empty.getInstance());
        pathInfo.put(new Square(6, 7), new Pawn(Camp.WHITE));

        boolean result = pawn.canMove(pathInfo, targetSquare);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("폰이 직선으로 두칸 이동하는 경우, 경로에 기물이 없지만 targetSquare에 기물이 있는경우 false를 반환한다")
    void canMoveTwoStepFailByTarget() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square targetSquare = new Square(7, 7);
        pawn.fetchMovableSquares(new Square(7, 5), targetSquare);
        Map<Square, Piece> pathInfo = new HashMap<>();
        pathInfo.put(targetSquare, new Pawn(Camp.WHITE));
        pathInfo.put(new Square(6, 7), Empty.getInstance());

        boolean result = pawn.canMove(pathInfo, targetSquare);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("폰이 한칸 이동하는 경우, targetSquare에 기물이 있는경우 false를 반환한다")
    void canMoveOneStepFailByTarget() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square targetSquare = new Square(7, 7);
        pawn.fetchMovableSquares(new Square(7, 5), targetSquare);
        Map<Square, Piece> pathInfo = new HashMap<>();
        pathInfo.put(targetSquare, new Pawn(Camp.WHITE));

        boolean result = pawn.canMove(pathInfo, targetSquare);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("폰이 한칸 이동하는 경우, targetSquare에 기물이 없는경우 true 반환한다")
    void canMoveOneStep() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square targetSquare = new Square(7, 7);
        pawn.fetchMovableSquares(new Square(7, 5), targetSquare);
        Map<Square, Piece> pathInfo = new HashMap<>();
        pathInfo.put(targetSquare, Empty.getInstance());

        boolean result = pawn.canMove(pathInfo, targetSquare);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰이 대각선으로 이동하는 경우, targetSquare에 상대진영의 기물이 있는 경우 true를 반환한다.")
    void canMoveDiagonal() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square targetSquare = new Square(7, 7);
        pawn.fetchMovableSquares(new Square(6, 6), targetSquare);
        Map<Square, Piece> pathInfo = new HashMap<>();
        pathInfo.put(targetSquare, new Pawn(Camp.BLACK));

        boolean result = pawn.canMove(pathInfo, targetSquare);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰이 대각선으로 이동하는 경우, targetSquare에 같은진영의 기물이 있는 경우 false를 반환한다.")
    void canMoveDiagonalFailSameCamp() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square targetSquare = new Square(7, 7);
        pawn.fetchMovableSquares(new Square(6, 6), targetSquare);
        Map<Square, Piece> pathInfo = new HashMap<>();
        pathInfo.put(targetSquare, new Pawn(Camp.WHITE));

        boolean result = pawn.canMove(pathInfo, targetSquare);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("폰이 대각선으로 이동하는 경우, targetSquare에 같은진영의 기물이 없는경우 false를 반환한다.")
    void canMoveDiagonalFailEmpty() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square targetSquare = new Square(7, 7);
        pawn.fetchMovableSquares(new Square(6, 6), targetSquare);
        Map<Square, Piece> pathInfo = new HashMap<>();
        pathInfo.put(targetSquare, Empty.getInstance());

        boolean result = pawn.canMove(pathInfo, targetSquare);

        assertThat(result).isFalse();
    }
}
