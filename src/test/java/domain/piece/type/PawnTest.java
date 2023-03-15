package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.Square;
import domain.piece.Camp;

class PawnTest {

    @Test
    @DisplayName("pawn이 직선으로 움직일때 첫번째 이동이면, 두칸갈수 있다.")
    void fetchMovableCoordinate() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square currentSquare = new Square(2, 2);
        Square targetSquare = new Square(2, 3);

        List<Square> squares = pawn.fetchMovableCoordinate(currentSquare, targetSquare);

        assertThat(squares).contains(new Square(2,3), new Square(2,4));
    }

    @Test
    @DisplayName("pawn이 직선으로 움직일때 첫번째 이동이 아니면, 한칸만 갈 수 있다.")
    void fetchMovableCoordinateNotFirst() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square currentSquare = new Square(2, 2);
        Square targetSquare = new Square(2, 3);
        Square nextTargetSquare = new Square(2, 4);

        pawn.fetchMovableCoordinate(currentSquare, targetSquare);
        List<Square> squares = pawn.fetchMovableCoordinate(targetSquare, nextTargetSquare);

        assertThat(squares).containsExactly(new Square(2,4));
    }

    @Test
    @DisplayName("pawn이 대각선으로 움직이려하면, 대각선으로 갈수 있는 모든 경로를 반환하다.")
    void fetchMovableCoordinateDiagonal() {
        Pawn pawn = new Pawn(Camp.WHITE);
        Square currentSquare = new Square(2, 2);
        Square targetSquare = new Square(3, 3);

        List<Square> squares = pawn.fetchMovableCoordinate(currentSquare, targetSquare);

        assertThat(squares).contains(new Square(1,3), new Square(3,3));
    }
}
