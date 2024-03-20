import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Piece;
import domain.piece.Rook;
import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import domain.piece.info.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {
    @Test
    @DisplayName("기물은 자신의 색상 정보를 제공한다.")
    void pieceColor() {
        final Piece piece = new Rook(Color.WHITE, Type.ROOK);
        assertThat(piece.color()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("기물은 자신의 모양 정보를 제공한다.")
    void pieceShape() {
        final Piece piece = new Rook(Color.WHITE, Type.ROOK);
        assertThat(piece.type()).isEqualTo(Type.ROOK);
    }
}
