package domain.square;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.move.Situation;
import domain.piece.move.Coordinate;
import domain.piece.pawn.BlackPawn;
import domain.piece.sliding.Bishop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PieceTest {

    @Test
    @DisplayName("칸은 기본 상태를 가진다")
    void propertyTest() {
        Piece piece = new Bishop(Color.BLACK);

        assertThat(piece.hasSameColorWith(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("이미 이동했다면 한 칸만 움직일 수 있다")
    void isMovable() {
        Piece pawn = new BlackPawn(Color.BLACK);

        assertThat(pawn.isMovable(
                new Coordinate(2, 0),
                new Coordinate(1, 0),
                Situation.NEUTRAL
        )).isTrue();
    }

    @Test
    @DisplayName("이미 이동했다면 두 칸을 움직일 수 없다")
    void isNotMovableWhenChecked() {
        Piece pawn = new BlackPawn(Color.BLACK);

        assertThat(pawn.isMovable(
                new Coordinate(3, 0),
                new Coordinate(1, 0),
                Situation.NEUTRAL
        )).isFalse();
    }
}
