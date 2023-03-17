package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Piece 은")
class PieceTest {

    @Test
    void 위치와_색상을_가지고_생성된다() {
        // when & then
        assertDoesNotThrow(() -> new Piece(Color.WHITE, PiecePosition.of(1, 'a')) {

            @Override
            protected void validatePath(final Path path) {
            }
        });
    }

    @Test
    void clone_할_수_있다() {
        // given
        final MyPiece myPiece = new MyPiece(Color.BLACK, PiecePosition.of(1, 'a'));

        // when
        final Piece clone = myPiece.clone();

        // then
        assertThat(clone).isExactlyInstanceOf(MyPiece.class);
    }

    static class MyPiece extends Piece {
        public MyPiece(final Color color, final PiecePosition piecePosition) {
            super(color, piecePosition);
        }

        @Override
        protected void validatePath(final Path path) {
        }
    }

    @Test
    void 경유지탐색_시_같은_위치면_예외() {
        // given
        Piece myPiece = new MyPiece(Color.BLACK, PiecePosition.of(1, 'a'));
        // when & then
        assertThatThrownBy(() -> myPiece.waypoints(PiecePosition.of(1, 'a')))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 경유지탐색_시_도달불가능하면_오류() {
        // given
        Piece myPiece = new Piece(Color.BLACK, PiecePosition.of(1, 'a')) {
            @Override
            protected void validatePath(final Path path) {
                throw new IllegalArgumentException("도달 불가");
            }
        };

        // when & then
        assertThatThrownBy(() -> myPiece.waypoints(PiecePosition.of(1, 'b')))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도달 불가");
    }

    @Test
    void 단순_이동할_수_있다() {
        // given
        final Piece pawn = new MyPiece(Color.BLACK, PiecePosition.of("b6"));

        // when
        pawn.move(PiecePosition.of("b5"));

        // then
        assertThat(pawn.piecePosition()).isEqualTo(PiecePosition.of("b5"));
    }

    @Test
    void 죽이기_위해_이동할_수_있따() {
        // given
        final Piece pawn = new MyPiece(Color.BLACK, PiecePosition.of("b6"));
        final Piece enemy = new MyPiece(Color.WHITE, PiecePosition.of("b7"));

        // when
        pawn.moveToKill(enemy);

        // then
        assertThat(pawn.piecePosition()).isEqualTo(PiecePosition.of("b7"));
    }

    @Test
    void 아군은_죽일_수_없다() {
        // given
        final Piece pawn = new MyPiece(Color.BLACK, PiecePosition.of("b6"));
        final Piece ally = new MyPiece(Color.BLACK, PiecePosition.of("b7"));

        // when & then
        assertThatThrownBy(() -> pawn.moveToKill(ally))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
