package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.util.HashMap;
import java.util.Map;

public class TestPieceDao implements PieceDao {
    private final Map<String, Map<Position, Piece>> testDB;

    TestPieceDao() {
        this.testDB = new HashMap<>();
    }

    public TestPieceDao(String gameId, Map<Position, Piece> boardStatus) {
        this.testDB = new HashMap<>();
        testDB.put(gameId, boardStatus);
    }

    @Override
    public int countSavedPieces(String gameId) {
        if (!testDB.containsKey(gameId)) {
            return 0;
        }

        Map<Position, Piece> boardStatus = testDB.get(gameId);
        return boardStatus.size();
    }

    @Override
    public void addPiece(String gameId, Position position, Piece piece) {
        if (!testDB.containsKey(gameId)) {
            testDB.put(gameId, new HashMap<>());
        }
        Map<Position, Piece> boardStatus = testDB.get(gameId);
        boardStatus.put(position, piece);
    }

    @Override
    public String findPieceNameByPosition(String gameId, Position position) {
        if (!testDB.containsKey(gameId)) {
            return null;
        }

        Map<Position, Piece> boardStatus = testDB.get(gameId);
        if (!boardStatus.containsKey(position)) {
            return null;
        }

        Piece piece = boardStatus.get(position);
        return piece.name();
    }

    @Override
    public void updatePiece(String gameId, Position position, Piece piece) {
        if (!testDB.containsKey(gameId)) {
            return;
        }
        Map<Position, Piece> boardStatus = testDB.get(gameId);
        boardStatus.put(position, piece);
    }

    @Override
    public void deleteBoardStatus(String gameId) {
        testDB.remove(gameId);
    }
}
