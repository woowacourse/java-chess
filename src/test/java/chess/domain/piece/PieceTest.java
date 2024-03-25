package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("Piece가 같은 색인지 구분할 수 있다")
    void should_distinguish_same_color() {
        Piece piece1 = new TestPiece(Color.BLACK);
        Piece piece2 = new TestPiece(Color.BLACK);

        assertThat(piece1.isSameColor(piece2)).isTrue();
    }

    @Test
    @DisplayName("Piece가 다른 색인지 구분할 수 있다")
    void should_distinguish_different_color() {
        Piece piece1 = new TestPiece(Color.BLACK);
        Piece piece2 = new TestPiece(Color.WHITE);

        assertThat(piece1.isSameColor(piece2)).isFalse();
    }

    private static class TestPiece extends Piece {

        public TestPiece(Color color) {
            super(color, ((fileDifference, rankDifference) -> false));
        }

        @Override
        public boolean isMovable(Position from, Position to) {
            return false;
        }

        @Override
        public boolean isCatchable(Position from, Position to) {
            return false;
        }
    }
}
