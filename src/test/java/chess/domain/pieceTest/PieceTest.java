package chess.domain.pieceTest;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @Test
    @DisplayName("체스 말이 블랙인지 검증하는 테스트")
    void isBlack() {
        Piece pawn = Pawn.of(Color.BLACK);
        Piece king = King.of(Color.WHITE);
        assertThat(pawn.isBlack()).isTrue();
        assertThat(king.isBlack()).isFalse();
    }
}
