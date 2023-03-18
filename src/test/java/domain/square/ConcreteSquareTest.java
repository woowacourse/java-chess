package domain.square;

import domain.piece.BlackPawn;
import domain.piece.Coordinate;
import domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConcreteSquareTest {

    @Test
    @DisplayName("이미 이동했다면 한 칸만 움직일 수 있다")
    void isMovable() {
        Piece pawn = new BlackPawn();
        Square square = new ConcreteSquare(pawn, Camp.BLACK);

        square.checkMoved();

        assertThat(square.isMovable(
                new Coordinate(1, 0),
                new Coordinate(2, 0)
        )).isTrue();
    }

    @Test
    @DisplayName("이미 이동했다면 두 칸을 움직일 수 없다")
    void isNotMovableWhenChecked() {
        Piece pawn = new BlackPawn();
        Square square = new ConcreteSquare(pawn, Camp.BLACK);

        square.checkMoved();

        assertThat(square.isMovable(
                new Coordinate(1, 0),
                new Coordinate(3, 0)
        )).isFalse();
    }
}
