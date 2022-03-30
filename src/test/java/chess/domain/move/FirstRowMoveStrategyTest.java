package chess.domain.move;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Test;

class FirstRowMoveStrategyTest {

    @Test
    void isMovableToTarget() {
        Piece sourcePiece = new Pawn(Color.WHITE);
        Piece targetPiece = new Pawn(Color.BLACK);

        assertThat(sourcePiece.getMoveStrategy())
    }
}