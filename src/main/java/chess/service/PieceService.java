package chess.service;

import chess.dao.PieceDaoInterface;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.dto.piece.PieceDto;
import chess.entity.PieceEntity;
import java.util.List;
import java.util.stream.Collectors;

public class PieceService {

    private final PieceDaoInterface pieceDao;

    public PieceService(final PieceDaoInterface pieceDao) {
        this.pieceDao = pieceDao;
    }

    public void initPieces(final long gameId) {
        final List<Piece> pieces = Board.createWithInitialLocation().toList();
        final List<PieceDto> pieceDtos = pieces.stream()
            .map(PieceDto::from)
            .collect(Collectors.toList());
        addAll(gameId, pieceDtos);
    }

    private void addAll(final long gameId, final List<PieceDto> pieceDtos) {
        final List<PieceEntity> pieceEntities = pieceDtos.stream()
            .map(pieceDto -> pieceDto.toEntity(gameId))
            .collect(Collectors.toList());
        pieceDao.insertBatch(gameId, pieceEntities);
    }

    public List<PieceDto> findAll(final long gameId) {
        final List<PieceEntity> pieceEntities = pieceDao.selectAll(gameId);
        return pieceEntities.stream()
            .map(PieceDto::from)
            .collect(Collectors.toList());
    }
}
