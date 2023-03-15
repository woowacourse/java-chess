package chess.domain.piece;

import chess.domain.piece.position.PiecePosition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Piece 은")
class PieceTest {

    @Test
    void 위치와_색상을_가지고_생성된다() {
        // when & then
        assertDoesNotThrow(() -> new Piece(Color.WHITE, PiecePosition.of(1, 'a')) {
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
    }
}
