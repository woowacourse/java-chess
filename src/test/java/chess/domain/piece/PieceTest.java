package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceTest {

    @Test
    void 검은색인지_확인한다() {
        final Piece piece = new Pawn(BLACK);

        assertThat(piece.isBlack()).isTrue();
    }

    @Test
    void 흰색인지_확인한다() {
        final Piece piece = new Pawn(WHITE);

        assertThat(piece.isBlack()).isFalse();
    }

    @Nested
    class hasSameColor_메서드는 {

        @Test
        void 같은_색이라면_true_반환한다() {
            final Piece piece = new Pawn(BLACK);

            assertThat(piece.hasSameColor(new Pawn(BLACK))).isTrue();
        }

        @Test
        void 다른_색이라면_false_반환한다() {
            final Piece piece = new Pawn(WHITE);

            assertThat(piece.hasSameColor(new Pawn(BLACK))).isFalse();
        }
    }

    @Nested
    class isSameColor_메서드는 {

        @Test
        void 같은_색이라면_true_반환한다() {
            final Piece piece = new Pawn(BLACK);

            assertThat(piece.isSameColor(BLACK)).isTrue();
        }

        @Test
        void 다른_색이라면_false_반환한다() {
            final Piece piece = new Pawn(BLACK);

            assertThat(piece.isSameColor(WHITE)).isFalse();
        }
    }
}
