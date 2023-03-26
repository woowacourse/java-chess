package chess.dao.chess;

import chess.entity.PieceEntity;

import java.util.List;

public class MockPieceDao implements PieceDao {

    private final List<PieceEntity> mockPieceEntites;

    public MockPieceDao() {
        this.mockPieceEntites = PieceEntityHelper.createPieceEntities();

    }

    @Override
    public List<PieceEntity> findByChessGameId(final Long chessGameId) {
        return List.copyOf(mockPieceEntites);
    }

    @Override
    public Long save(final PieceEntity pieceEntity, final Long chessGameId) {
        return 1L;
    }
}
