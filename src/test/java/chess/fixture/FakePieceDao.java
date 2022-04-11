package chess.fixture;

import chess.domain.board.Piece;
import chess.domain.position.Position;
import chess.dao.PieceDao;
import java.util.HashMap;
import java.util.Map;

public class FakePieceDao implements PieceDao {

    private final Map<Position, Piece> store = new HashMap<>();

    @Override
    public void removeAll() {
        store.clear();
    }

    @Override
    public void savePieces(final Map<Position, Piece> board) {
        store.putAll(board);
    }

    @Override
    public void deletePiece(final Position position) {
        store.remove(position);
    }

    @Override
    public void updatePosition(final Position originPosition, final Position newPosition) {
        Piece piece = store.get(originPosition);
        store.remove(originPosition);
        store.put(newPosition, piece);
    }

    @Override
    public Map<Position, Piece> findAll() {
        return store;
    }
}
