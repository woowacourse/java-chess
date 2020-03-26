package chess.domain.chessPiece.pieceType;

import chess.domain.MovableStrategy.RookMovableStrategy;
import chess.domain.chessPiece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    void Rook_PieceColor_GenerateInstance() {
        assertThat(new Rook(PieceColor.BLACK, new RookMovableStrategy())).isInstanceOf(Rook.class);
    }


    @Test
    void getName_ReturnName() {
        assertThat(new Rook(PieceColor.BLACK, new RookMovableStrategy()).getName()).isEqualTo("R");
        assertThat(new Rook(PieceColor.WHITE, new RookMovableStrategy()).getName()).isEqualTo("r");
    }
}