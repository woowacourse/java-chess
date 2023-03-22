package chessgame.domain.piece;

import chessgame.domain.piecetype.BlackPawn;
import chessgame.domain.piecetype.Coordinate;
import chessgame.domain.piecetype.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConcretePieceTest {

    @Test
    @DisplayName("이미 이동했다면 한 칸만 움직일 수 있다")
    void isMovable() {
        PieceType pawn = new BlackPawn();
        Piece piece = new ConcretePiece(pawn, Camp.BLACK);

        piece.checkMoved();

        assertThat(piece.isMovable(
                Coordinate.fromOnBoard(1, 0),
                Coordinate.fromOnBoard(0, 0)
        )).isTrue();
    }

    @Test
    @DisplayName("이미 이동했다면 두 칸을 움직일 수 없다")
    void isNotMovableWhenChecked() {
        PieceType pawn = new BlackPawn();
        Piece piece = new ConcretePiece(pawn, Camp.BLACK);

        piece.checkMoved();

        assertThat(piece.isMovable(
                Coordinate.fromOnBoard(2, 0),
                Coordinate.fromOnBoard(0, 0)
        )).isFalse();
    }
}
