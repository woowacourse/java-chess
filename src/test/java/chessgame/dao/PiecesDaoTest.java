package chessgame.dao;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.Queen;
import chessgame.dto.PieceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @DisplayName("특정 게임 아이디의 기물들을 모두 불러올 수 있다")
    void findPiecesByRoomId() {
        long roomId = 1;

        List<PieceDto> pieces = piecesDao.findPiecesByRoomId(roomId);

        assertThat(pieces).hasSizeGreaterThanOrEqualTo(1);
    }
}
