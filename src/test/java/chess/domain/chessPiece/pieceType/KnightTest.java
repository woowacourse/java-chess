package chess.domain.chessPiece.pieceType;

import chess.domain.MovableStrategy.KnightMovableStrategy;
import chess.domain.chessPiece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    void Knight_PieceColor_GenerateInstance() {
        assertThat(new Knight(PieceColor.BLACK, new KnightMovableStrategy())).isInstanceOf(Knight.class);
    }

    @Test
    void getName_ReturnName() {
        assertThat(new Knight(PieceColor.BLACK, new KnightMovableStrategy()).getName()).isEqualTo("N");
        assertThat(new Knight(PieceColor.WHITE, new KnightMovableStrategy()).getName()).isEqualTo("n");
    }
}