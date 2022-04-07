package chess.dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

class PieceCacheTest {

    @Test
    @DisplayName("이름과 색깔로 말을 만든다.")
    public void createPieceByNameAndColor() {
        // given
        String name = "PAWN";
        Color color = Color.of("WHITE");
        // when
        Piece piece = PieceCache.getPiece(name, color);
        // then
        assertThat(piece).isInstanceOf(Pawn.class);
    }
}