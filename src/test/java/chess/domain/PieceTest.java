package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    void 해당_기물의_색상을_반환한다() {
        // given
        final Piece piece = new Piece(Color.WHITE, PieceType.PAWN) {
        };

        // when
        final Color color = piece.color();

        // then
        assertThat(color).isEqualTo(Color.WHITE);
    }

    @Test
    void 해당_기물의_타입을_반환한다() {
        // given
        final Piece piece = new Piece(Color.WHITE, PieceType.PAWN) {
        };

        // when
        final PieceType type = piece.type();

        // then
        assertThat(type).isEqualTo(PieceType.PAWN);
    }
}
