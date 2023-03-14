package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.util.TestPiece;
import org.junit.jupiter.api.Test;

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
}
