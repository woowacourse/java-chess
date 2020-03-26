package chess.domain.chessPiece.pieceType;

import chess.domain.MovableStrategy.KingMovableStrategy;
import chess.domain.chessPiece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @Test
    void King_PieceColor_GenerateInstance() {
        assertThat(new King(PieceColor.BLACK, new KingMovableStrategy())).isInstanceOf(King.class);
    }

    @Test
    void getName_ReturnName() {
        assertThat(new King(PieceColor.BLACK, new KingMovableStrategy()).getName()).isEqualTo("K");
        assertThat(new King(PieceColor.WHITE, new KingMovableStrategy()).getName()).isEqualTo("k");
    }
}