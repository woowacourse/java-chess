package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceFactoryTest {
    
    @Test
    @DisplayName("초기 흰색 폰 피스들의 리스트를 반환한다.")
    void create_white_pawns() {
        List<Piece> pawns = PieceFactory.createWhitePawns();
        assertThat(pawns.size()).isEqualTo(8);
        for (Piece piece : pawns) {
            assertDoesNotThrow(() -> {
                Pawn pawn = (Pawn) piece;
            });
            assertThat(piece.isWhite()).isTrue();
        }
    }
    
    @Test
    @DisplayName("초기 흰색 장군 피스들의 리스트를 반환한다.")
    void create_white_generals() {
        List<Piece> generals = PieceFactory.createWhiteGenerals();
        Color color = Color.WHITE;
        assertThat(generals.size()).isEqualTo(8);
        assertThat(generals).containsExactly(
                Rook.create(color),
                Knight.create(color),
                Bishop.create(color),
                Queen.create(color),
                King.create(color),
                Bishop.create(color),
                Knight.create(color),
                Rook.create(color)
        );
    }
}