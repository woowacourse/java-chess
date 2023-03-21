package domain.square;

import domain.piece.sliding.Bishop;
import domain.piece.pawn.BlackPawn;
import domain.piece.move.Coordinate;
import domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SquareTest {

    @Test
    @DisplayName("칸은 기본 상태를 가진다")
    void propertyTest() {
        Square square = new Square(new Bishop(), Camp.BLACK);

        assertThat(square.canReap()).isFalse();
        assertThat(square.getCamp()).isEqualTo(Camp.BLACK);
        assertThat(square.getPieceType()).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("이미 이동했다면 한 칸만 움직일 수 있다")
    void isMovable() {
        Piece pawn = new BlackPawn();
        Square square = new Square(pawn, Camp.BLACK);

        square.checkMoved();

        assertThat(square.isMovable(
                new Coordinate(2, 0),
                new Coordinate(1, 0)
        )).isTrue();
    }

    @Test
    @DisplayName("이미 이동했다면 두 칸을 움직일 수 없다")
    void isNotMovableWhenChecked() {
        Piece pawn = new BlackPawn();
        Square square = new Square(pawn, Camp.WHITE);

        square.checkMoved();

        assertThat(square.isMovable(
                new Coordinate(3, 0),
                new Coordinate(1, 0)
        )).isFalse();
    }
}
