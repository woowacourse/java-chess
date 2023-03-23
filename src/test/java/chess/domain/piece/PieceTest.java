package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.BLACK_PAWN;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.WHITE_PAWN;
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
        final Piece piece = new Piece(BLACK, BLACK_PAWN);

        assertThat(piece.isBlack()).isTrue();
    }

    @Test
    void 흰색인지_확인한다() {
        final Piece piece = new Piece(WHITE, WHITE_PAWN);

        assertThat(piece.isBlack()).isFalse();
    }

    @Nested
    class hasSameColor_메서드는 {

        @Test
        void 같은_색이라면_true_반환한다() {
            final Piece piece = new Piece(BLACK, QUEEN);

            assertThat(piece.hasSameColor(new Piece(BLACK, KNIGHT))).isTrue();
        }

        @Test
        void 다른_색이라면_false_반환한다() {
            final Piece piece = new Piece(WHITE, QUEEN);

            assertThat(piece.hasSameColor(new Piece(BLACK, KNIGHT))).isFalse();
        }
    }

    @Nested
    class isSameColor_메서드는 {

        @Test
        void 같은_색이라면_true_반환한다() {
            final Piece piece = new Piece(BLACK, QUEEN);

            assertThat(piece.isSameColor(BLACK)).isTrue();
        }

        @Test
        void 다른_색이라면_false_반환한다() {
            final Piece piece = new Piece(BLACK, QUEEN);

            assertThat(piece.isSameColor(WHITE)).isFalse();
        }
    }

    @Nested
    class isPawn_메서드는 {

        @Test
        void 검은색_폰이라면_true_반환한다() {
            final Piece piece = new Piece(BLACK, BLACK_PAWN);

            assertThat(piece.isPawn()).isTrue();
        }

        @Test
        void 흰색_폰이라면_true_반환한다() {
            final Piece piece = new Piece(WHITE, WHITE_PAWN);

            assertThat(piece.isPawn()).isTrue();
        }

        @Test
        void 폰이_아니라면_false_반환한다() {
            final Piece piece = new Piece(BLACK, QUEEN);

            assertThat(piece.isPawn()).isFalse();
        }
    }
}
