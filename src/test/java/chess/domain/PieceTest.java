package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {
    
    static class ChildPiece extends Piece {
        
        ChildPiece(Color color) {
            super(color, PieceType.EMPTY);
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