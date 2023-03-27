package chess.domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static chess.PositionFixture.B_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PieceTypeTest {

    @Test
    void 체스_말을_생성할_수_있다() {
        Piece piece = PieceType.PAWN.of(Team.WHITE, B_2);

        assertThat(piece.getPieceType()).isEqualTo(PieceType.PAWN);
    }
}
