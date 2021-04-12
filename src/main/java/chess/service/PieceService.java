package chess.service;

import chess.dao.PieceDaoInterface;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.dto.piece.PieceDto;
import chess.entity.PieceEntity;
import chess.service.exception.DataNotFoundException;
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

    public void updateLocation(final long gameId, final int sourceX, final int sourceY,
        final int targetX, final int targetY) {

        final PieceEntity pieceEntity = pieceDao.selectByLocation(gameId, sourceX, sourceY)
            .orElseThrow(() -> new DataNotFoundException(PieceEntity.class));

        pieceDao.update(new PieceEntity(
            pieceEntity.getId(),
            pieceEntity.getGameId(),
            pieceEntity.getPieceType(),
            pieceEntity.getTeam(),
            targetX,
            targetY
        ));
    }

    public void deleteByLocation(final long gameId, final int x, final int y) {
        pieceDao.deleteByLocation(gameId, x, y);
    }
}
