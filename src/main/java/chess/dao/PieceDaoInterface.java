package chess.dao;

import chess.entity.PieceEntity;
import java.util.List;
import java.util.Optional;

public interface PieceDaoInterface {

    void insertBatch(long gameId, List<PieceEntity> pieceEntities);

    List<PieceEntity> selectAll(long gameId);

    Optional<PieceEntity> selectByLocation(long gameId, int x, int y);

    void update(PieceEntity pieceEntity);

    void deleteByLocation(final long gameId, final int x, final int y);
}
