package domain.piece;

import domain.piece.move.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class EmptyPieceTest {

    @Test
    @DisplayName("기본 상태를 가진다")
    void constructorTest() {
        Piece emptyPiece = new EmptyPiece();

        assertThat(emptyPiece.canReap()).isFalse();
        assertThat(emptyPiece.isKing()).isFalse();
        assertThat(emptyPiece.isPawn()).isFalse();
    }

    @Test
    @DisplayName("예외 흐름을 체크할 수 있다")
    void isReachableByRuleWhenExceptionalCase() {
        Piece emptyPiece = new EmptyPiece();

        assertThatThrownBy(() -> emptyPiece.isReachableByRule(
                new Coordinate(0, 0),
                new Coordinate(1, 0)
        )).isInstanceOf(UnsupportedOperationException.class);
    }
}
