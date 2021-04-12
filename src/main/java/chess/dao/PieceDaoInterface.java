package chess.dao;

import chess.entity.PieceEntity;
import java.util.List;

public interface PieceDaoInterface {

    void insertBatch(long gameId, List<PieceEntity> pieceEntities);
}
