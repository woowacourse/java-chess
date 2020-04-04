package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.util.HashMap;
import java.util.Map;

public class TestPieceDao implements PieceDao {
    private final Map<String, Map<Position, Piece>> testDB;

    public TestPieceDao() {
        this.testDB = new HashMap<>();
    }

    public TestPieceDao(String user_id, Map<Position, Piece> saved) {
        this.testDB = new HashMap<>();
        testDB.put(user_id, saved);
    }

    @Override
    public int countSavedInfo(String user_id) {
        if (!testDB.containsKey(user_id)) {
            return 0;
        }

        Map<Position, Piece> saved = testDB.get(user_id);
        return saved.size();
    }

    @Override
    public void addPiece(String user_id, Position position, Piece piece) {
        if (!testDB.containsKey(user_id)) {
            testDB.put(user_id, new HashMap<>());
        }
        Map<Position, Piece> saved = testDB.get(user_id);
        saved.put(position, piece);
    }

    @Override
    public String findPieceNameByPosition(String user_id, Position position) {
        if (!testDB.containsKey(user_id)) {
            return null;
        }

        Map<Position, Piece> saved = testDB.get(user_id);
        if (!saved.containsKey(position)) {
            return null;
        }

        Piece piece = saved.get(position);
        return piece.name();
    }

    @Override
    public void updatePiece(String user_id, Position position, Piece piece) {
        if (!testDB.containsKey(user_id)) {
            return;
        }
        Map<Position, Piece> saved = testDB.get(user_id);
        saved.put(position, piece);
    }

    @Override
    public void deletePiece(String user_id, Position position) {
        if (!testDB.containsKey(user_id)) {
            return;
        }

        Map<Position, Piece> saved = testDB.get(user_id);
        saved.remove(position);
    }

    @Override
    public void deleteSaved(String user_id) {
        testDB.remove(user_id);
    }
}
