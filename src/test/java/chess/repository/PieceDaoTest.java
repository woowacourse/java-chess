package chess.repository;

import static chess.domain.InitialPiece.BLACK_KING;
import static chess.domain.InitialPiece.WHITE_KING;
import static chess.domain.InitialPiece.WHITE_PAWN;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PieceDaoTest {

    private final PieceDao pieceDao = new PieceDao();

    @DisplayName("새 게임을 저장할 때 piece 를 함께 저장한다.")
    @Test
    void 게임_체스말_상태_저장() {
        Map<Position, Piece> piecesByPosition = Map.of(Position.from("B2"), WHITE_PAWN.getPiece(),
            Position.from("E8"), BLACK_KING.getPiece(),
            Position.from("E1"), WHITE_KING.getPiece());

        long gameId = 1L;

        assertDoesNotThrow(() -> pieceDao.save(piecesByPosition, gameId));
    }

}