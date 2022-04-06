package chess.dao;

import chess.dao.fixture.MockPieceDao;
import chess.dto.PieceDto;
import chess.piece.Bishop;
import chess.piece.detail.Color;
import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    @Test
    @DisplayName("피스들을 저장한다.")
    void saveBoardTurn() {
        final PieceDao pieceDao = new MockPieceDao();
        final Bishop bishop = new Bishop(Color.WHITE);
        final Position position = Position.of("a", 2);
        final PieceDto pieceDto = PieceDto.toDto(bishop, position);

        pieceDao.save(1, pieceDto);
    }
}
