package chess.service;

import chess.dao.PieceDaoInterface;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.dto.piece.PieceDto;
import java.util.List;
import java.util.stream.Collectors;

public class PieceService {

    private final PieceDaoInterface pieceDao;

    public PieceService(final PieceDaoInterface pieceDao) {
        this.pieceDao = pieceDao;
    }

    public void initPieces(final long gameId) {
        final List<Piece> pieces = Board.createWithInitialLocation().toList();
        pieceDao.insertBatch(gameId, pieces);
    }

    public List<PieceDto> findAll(final long gameId) {
        final List<Piece> pieces = pieceDao.selectAll(gameId);
        return pieces.stream()
            .map(PieceDto::from)
            .collect(Collectors.toList());
    }

    public void updateLocation(final long gameId, final int sourceX, final int sourceY,
        final int targetX, final int targetY) {
        pieceDao.updateByLocation(gameId, sourceX, sourceY, targetX, targetY);
    }

    public void deleteByLocation(final long gameId, final int x, final int y) {
        pieceDao.deleteByLocation(gameId, x, y);
    }
}
