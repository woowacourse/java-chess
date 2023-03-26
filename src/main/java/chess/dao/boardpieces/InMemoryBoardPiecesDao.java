package chess.dao.boardpieces;

import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryBoardPiecesDao implements BoardPiecesDao {

    private final Map<Integer, Map<Position, Piece>> repository = new HashMap<>();

    @Override
    public Optional<Map<Position, Piece>> find(final int boardId) {
        return Optional.ofNullable(repository.get(boardId));
    }

    @Override
    public void insert(final int boardId, final Map<Position, Piece> piecesByPosition) {
        repository.put(boardId, piecesByPosition);
    }

    @Override
    public void delete(final int boardId) {
        repository.remove(boardId);
    }

}
