package chess.domain;

import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest extends AbstractTestFixture {

    static class PieceImplement extends Piece {
        public PieceImplement(boolean isWhite, boolean isFinite, List<Movement> movements) {
            super(isWhite, isFinite, movements);
        }
    }

    private Piece piece;

    @BeforeEach
    void setUpPiece() {
        piece = createPiece(true, true);
    }

    @DisplayName("색을 구별할 수 있다")
    @Test
    void isSameColor() {
        Piece otherPiece = createPiece(false, true);

        assertThat(piece.hasSameColor(otherPiece)).isFalse();
    }

    @DisplayName("가능한 움직임인지 판단한다(유한)")
    @Test
    void isValidMovementFinite() {
        Movement movement = createMovement(UP, UP, RIGHT);
        Movement movement2 = createMovement(RIGHT, RIGHT, UP);

        Piece piece = createPiece(true, true, movement, movement2);
        assertThat(piece.hasMovement(movement)).isTrue();
    }

    @DisplayName("가능한 움직임인지 판단한다(무한)")
    @Test
    void isValidMovementInfinite() {
        Movement movement = createMovement(UP, RIGHT);

        Piece piece = createPiece(true, false, movement);
        assertThat(piece.hasMovement(createMovement(UP, RIGHT, UP, RIGHT))).isTrue();
    }

    @DisplayName("가능하지 않은 움직임인지 판단한다(기물:유한, 움직임:무한)")
    @Test
    void isInvalidMovementFinite() {
        Movement movement = createMovement(UP, RIGHT);

        Piece piece = createPiece(true, true, movement);
        assertThat(piece.hasMovement(createMovement(UP, RIGHT, UP, RIGHT))).isFalse();
    }
}
