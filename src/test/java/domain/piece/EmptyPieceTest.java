package domain.piece;

import domain.piece.move.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmptyPieceTest {

    @Test
    @DisplayName("기본 상태를 가진다")
    void constructorTest() {
        Piece emptyPiece = new EmptyPiece();

        assertThat(emptyPiece.canJump()).isFalse();
        assertThat(emptyPiece.isKing()).isFalse();
        assertThat(emptyPiece.isPawn()).isFalse();
        assertThat(emptyPiece.getColor() == Color.NEUTRAL).isTrue();
        assertThat(emptyPiece.getPoint()).isEqualTo(0);
    }

    @Test
    @DisplayName("이동이 불가능하다")
    void isNotMovable() {
        Piece emptyPiece = new EmptyPiece();

        assertThat(emptyPiece.isMovable(
                new Coordinate(0, 0),
                new Coordinate(1, 0)
        )).isFalse();
    }

    @Test
    @DisplayName("이동이 불가능하다")
    void isReachableByRuleWhenExceptionalCase() {
        Piece emptyPiece = new EmptyPiece();

        assertThat(emptyPiece.isMovable(
                new Coordinate(0, 0),
                new Coordinate(1, 0)
        )).isFalse();
    }
}
