package chess.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("기물 생성 테스트")
    @Test
    void construct() {
        assertDoesNotThrow(() -> new Pawn(Team.BLACK));
    }
}
