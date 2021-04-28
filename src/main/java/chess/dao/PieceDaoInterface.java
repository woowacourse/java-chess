package chess.dao;

import chess.domain.piece.Piece;
import java.util.List;
import java.util.Optional;

public interface PieceDaoInterface {

    void insertBatch(long gameId, List<Piece> pieces);

    List<Piece> selectAll(long gameId);

    Optional<Piece> selectByLocation(long gameId, int x, int y);

    void updateByLocation(final long gameId, final int befX, final int befY,
        final int afterX, final int afterY);

    void deleteByLocation(final long gameId, final int x, final int y);
}
