package chess.domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰 기물 생성")
    void createPawn() {
        Piece pawn = new WhitePawn();
        assertThat(pawn).isInstanceOf(Pawn.class);
    }
}
