package chess.db;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPieceDao implements PieceDao {
    private final Map<String, Map<String, String>> testDB;

    TestPieceDao() {
        this.testDB = new HashMap<>();
    }

    public TestPieceDao(String gameId, Map<Position, Piece> boardStatus) {
        this.testDB = new HashMap<>();
        Map<String, String> initializedBoard = new HashMap<>();

        for (Position position : boardStatus.keySet()) {
            initializedBoard.put(position.name(), boardStatus.get(position).name());
        }

        testDB.put(gameId, initializedBoard);
    }

    @Override
    public int countSavedPieces(String gameId) {
        if (!testDB.containsKey(gameId)) {
            return 0;
        }

        Map<String, String> boardStatus = testDB.get(gameId);
        return boardStatus.size();
    }

    @Override
    public void addInitialPieces(List<ChessPiece> chessPieces) {
        for (ChessPiece piece : chessPieces) {
            addPiece(piece);
        }
    }

    @Override
    public void addPiece(ChessPiece piece) {
        if (!testDB.containsKey(piece.getGameId())) {
            testDB.put(piece.getGameId(), new HashMap<>());
        }
        Map<String, String> boardStatus = testDB.get(piece.getGameId());
        boardStatus.put(piece.getPosition(), piece.getPiece());
    }

    @Override
    public String findPieceNameByPosition(String gameId, Position position) {
        if (!testDB.containsKey(gameId)) {
            return null;
        }

        Map<String, String> boardStatus = testDB.get(gameId);
        if (!boardStatus.containsKey(position.name())) {
            return null;
        }

        return boardStatus.get(position.name());
    }

    @Override
    public void updatePiece(String gameId, Position position, Piece piece) {
        if (!testDB.containsKey(gameId)) {
            return;
        }
        Map<String, String> boardStatus = testDB.get(gameId);
        boardStatus.put(position.name(), piece.name());
    }

    @Override
    public void deleteBoardStatus(String gameId) {
        testDB.remove(gameId);
    }
}
