package chess.dao.boardpieces;

import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.Optional;

public interface BoardPiecesDao {

    Optional<Map<Position, Piece>> find(final int boardId);

    void insertOrUpdate(final int boardId, final Map<Position, Piece> piecesByPosition);

    void delete(final int boardId);

}
