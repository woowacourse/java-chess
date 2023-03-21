package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    void 해당_기물의_색상을_반환한다() {
        // given
        final Piece piece = generatePiece(Color.WHITE, PieceType.PAWN);

        // when
        final Color color = piece.color();

        // then
        assertThat(color).isEqualTo(Color.WHITE);
    }

    private static Piece generatePiece(final Color color, final PieceType type) {
        final Piece piece = new Piece(color, type) {
            @Override
            protected boolean isValidMove(final Position start, final Position end) {
                return false;
            }

            @Override
            protected boolean isValidTarget(final Piece target) {
                return false;
            }
        };
        return piece;
    }

    @Test
    void 해당_기물의_타입을_반환한다() {
        // given
        final Piece piece = generatePiece(Color.WHITE, PieceType.PAWN);

        // when
        final PieceType type = piece.type();

        // then
        assertThat(type).isEqualTo(PieceType.PAWN);
    }
}
