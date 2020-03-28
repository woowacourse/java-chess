package chess.domain.piece.factory;

import chess.domain.piece.pawn.InitializedPawn;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PieceTypeTest {

    @Test
    void valueOf() {
        PieceType pieceType = PieceType.valueOf(InitializedPawn.class);
        assertThat(pieceType).isEqualTo(PieceType.INITIALIZED_PAWN);
    }
}