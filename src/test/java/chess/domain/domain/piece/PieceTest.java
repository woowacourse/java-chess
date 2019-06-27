package chess.domain.domain.piece;

import chess.domain.Aliance;
import chess.domain.PieceValue;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PieceTest {
    @Test
    void 현재_턴과_말이_팀이_다른_경우() {
        Piece piece = new Rook(Aliance.BLACK, PieceValue.ROOK);
        assertTrue(piece.isDifferentTeam(Aliance.WHITE));
    }

    @Test
    void 현재_턴과_말이_팀이_같은_경우() {
        Piece piece = new Rook(Aliance.BLACK, PieceValue.ROOK);
        assertFalse(piece.isDifferentTeam(Aliance.BLACK));
    }

    /* TODO : 폰 checkPossibleMove 테스트 */
}
