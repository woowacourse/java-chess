package chessgame.dao;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PiecesDaoTest {

    private final PiecesDao piecesDao = new PiecesDao();

    @Test
    @DisplayName("특정 기물을 저장할 수 있다")
    void addPiece() {
        long roomId = 1;
        Coordinate coordinate = Coordinate.fromOnBoard(3, 4);
        Piece piece = new Queen(Camp.WHITE);

        assertDoesNotThrow(() -> piecesDao.addPiece(roomId, coordinate, piece));
    }
}
