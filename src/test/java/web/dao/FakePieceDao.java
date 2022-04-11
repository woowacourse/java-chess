package web.dao;

import chess.domain.piece.Piece;
import chess.domain.position.ChessBoardPosition;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class FakePieceDao implements PieceDao {

    private Map<Long, Map<Position, Piece>> store = new HashMap<>();

    @Override
    public void savePiece(String position, Piece piece, Long gameId) {
        Position targetPosition = ChessBoardPosition.from(position);
        if (store.get(gameId) == null) {
            store.put(gameId, Map.of(ChessBoardPosition.from(position), piece));
            return;
        }
        Map<Position, Piece> positionPieceMap = store.get(gameId);
        Map<Position, Piece> pieces = new HashMap<>(positionPieceMap);
        pieces.put(targetPosition, piece);
        store.put(gameId, pieces);
    }

    @Override
    public Map<Position, Piece> findPieces(Long gameId) {
        return store.get(gameId);
    }

    @Override
    public void deletePiece(String position, Long id) {
        Map<Position, Piece> positionPieceMap = store.get(id);
        Map<Position, Piece> pieces = new HashMap<>(positionPieceMap);
        pieces.remove(ChessBoardPosition.from(position));
        store.put(id, pieces);
    }
}
