package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.position.PiecePosition.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Piece 은")
class PieceTest {

    @Test
    void 위치와_색상_전략을_가지고_생성된다() {
        // when & then
        assertDoesNotThrow(() -> new Piece(Color.WHITE, of(1, 'a'), path -> {
        }) {

            @Override
            protected void validatePath(final Path path) {
            }
        });
    }

    static class MyPiece extends Piece {
        public MyPiece(final Color color, final PiecePosition piecePosition) {
            super(color, piecePosition, null);
        }

        @Override
        protected void validatePath(final Path path) {
        }
    }

    @Test
    void 경유지탐색_시_같은_위치면_예외() {
        // given
        Piece myPiece = new MyPiece(Color.BLACK, of(1, 'a'));
        // when & then
        assertThatThrownBy(() -> myPiece.waypoints(of(1, 'a')))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 경유지탐색_시_도달불가능하면_오류() {
        // given
        Piece myPiece = new Piece(Color.BLACK, of(1, 'a'), path -> {
            throw new IllegalArgumentException();
        });

        // when & then
        assertThatThrownBy(() -> myPiece.waypoints(of(1, 'b')))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 단순_이동할_수_있다() {
        // given
        final Piece pawn = new MyPiece(Color.BLACK, of("b6"));

        // when
        final Piece next = pawn.move(of("b5"), null);

        // then
        assertThat(next.piecePosition()).isEqualTo(of("b5"));
    }

    @Test
    void 이동할_수_없는_경로로_이동하면_오류() {
        // given
        final Piece pawn = new Piece(Color.BLACK, of("b6"), new MoveStrategy() {
            @Override
            public void validatePath(final Path path) {
                throw new IllegalArgumentException();
            }
        });

        // when & then
        assertThatThrownBy(() -> pawn.move(of("b5"), null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 죽이기_위해_이동할_수_있따() {
        // given
        final Piece pawn = new MyPiece(Color.BLACK, of("b6"));
        final Piece enemy = new MyPiece(Color.WHITE, of("b7"));

        // when
        final Piece next = pawn.move(enemy.piecePosition, enemy);

        // then
        assertThat(next.piecePosition()).isEqualTo(of("b7"));
    }

    @Test
    void 아군은_죽일_수_없다() {
        // given
        final Piece pawn = new MyPiece(Color.BLACK, of("b6"));
        final Piece ally = new MyPiece(Color.BLACK, of("b7"));

        // when & then
        assertThatThrownBy(() -> pawn.move(ally.piecePosition, ally))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
