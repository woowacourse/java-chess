package chess.domain.piece;

import chess.domain.piece.pawn.WhiteFirstPawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTypeTest {

    @Test
    @DisplayName("PieceType에 해당하는 Piece를 만든다")
    void createPiece() {
        Piece piece = PieceType.WHITE_FIRST_PAWN.getPiece();

        Assertions.assertThat(piece).isInstanceOf(WhiteFirstPawn.class);
    }
}
