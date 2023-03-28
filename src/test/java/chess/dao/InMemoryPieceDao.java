package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPieceDao implements PieceDao {

    private final HashMap<String, Map<Position, Piece>> gameIdPieceMap;

    public InMemoryPieceDao() {
        this.gameIdPieceMap = new HashMap<>();
    }

    @Override
    public void createPiece(final Position position, final Piece piece, final String gameId) {
        if (!gameIdPieceMap.containsKey(gameId)) {
            this.gameIdPieceMap.put(gameId, new HashMap<>(Map.of(position, piece)));
            return;
        }
        Map<Position, Piece> currentGame = gameIdPieceMap.get(gameId);
        currentGame.put(position, piece);
    }

    @Override
    public Map<Position, Piece> read(final String gameId) {
        return gameIdPieceMap.get(gameId);
    }

    @Override
    public void update(final Position from, final Position to, final String gameId) {
        final Map<Position, Piece> currentGame = this.gameIdPieceMap.get(gameId);
        final Piece fromPiece = currentGame.remove(from);
        currentGame.remove(to);
        currentGame.put(to, fromPiece);
    }

    @Override
    public void deleteAll(final String gameId) {
        final Map<Position, Piece> currentGame = this.gameIdPieceMap.get(gameId);
        currentGame.clear();
    }
}
