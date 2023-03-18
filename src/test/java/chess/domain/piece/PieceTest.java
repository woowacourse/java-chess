package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.util.TestPiece;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceTest {

    @Test
    void 검은색인지_확인한다() {
        final Piece piece = new TestPiece(Color.BLACK);

        assertThat(piece.isBlack()).isTrue();
    }

    @Test
    void 흰색인지_확인한다() {
        final Piece piece = new TestPiece(Color.WHITE);

        assertThat(piece.isBlack()).isFalse();
    }

    @Nested
    class hasSameColor_메서드는 {

        @Test
        void 같은_색이라면_true_반환한다() {
            final Piece piece = new TestPiece(Color.WHITE);

            assertThat(piece.hasSameColor(new TestPiece(Color.WHITE))).isTrue();
        }

        @Test
        void 다른_색이라면_false_반환한다() {
            final Piece piece = new TestPiece(Color.WHITE);

            assertThat(piece.hasSameColor(new TestPiece(Color.BLACK))).isFalse();
        }
    }
}
