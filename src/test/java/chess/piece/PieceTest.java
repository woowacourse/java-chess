package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("Piece가 같은 색인지 피스로 구분할 수 있다")
    void should_distinguish_same_color_from_piece() {
        Piece piece1 = new TestPiece(Color.BLACK);
        Piece piece2 = new TestPiece(Color.BLACK);

        assertThat(piece1.isSameColor(piece2)).isTrue();
    }

    @Test
    @DisplayName("Piece가 다른 색인지 피스로 구분할 수 있다")
    void should_distinguish_different_color_from_piece() {
        Piece piece1 = new TestPiece(Color.BLACK);
        Piece piece2 = new TestPiece(Color.WHITE);

        assertThat(piece1.isSameColor(piece2)).isFalse();
    }

    @Test
    @DisplayName("Piece가 같은 색인지 색으로 구분할 수 있다.")
    void should_distinguish_same_color_from_color() {
        Piece piece1 = new TestPiece(Color.WHITE);

        assertThat(piece1.isSameColor(Color.WHITE)).isTrue();
    }

    @Test
    @DisplayName("Piece가 다른 색인지 색으로 구분할 수 있다.")
    void should_distinguish_different_color_from_color() {
        Piece piece1 = new TestPiece(Color.WHITE);

        assertThat(piece1.isSameColor(Color.EMPTY)).isFalse();
    }

    @Test
    @DisplayName("Piece가 서로 다른 색인지 색으로 구분할 수 있다.")
    void should_distinguish_opposite_color() {
        Piece piece1 = new TestPiece(Color.WHITE);
        Piece piece2 = new TestPiece(Color.BLACK);

        assertThat(piece1.isOppositeColor(piece2)).isTrue();
    }
}
