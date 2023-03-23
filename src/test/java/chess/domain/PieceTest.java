package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {
    
    static class ChildPiece extends Piece {
        
        ChildPiece(Color color) {
            super(color, PieceType.EMPTY, List.of());
        }
        
        @Override
        public void canMove(final Position start, final Position end) {
        
        }
    }
    
    @Test
    @DisplayName("피스 색상 확인 테스트")
    void color() {
        ChildPiece black = new ChildPiece(Color.BLACK);
        Assertions.assertThat(black.isWhite()).isFalse();
        ChildPiece white = new ChildPiece(Color.WHITE);
        Assertions.assertThat(white.isWhite()).isTrue();
    }
}
