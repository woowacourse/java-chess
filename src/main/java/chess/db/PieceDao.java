package chess.db;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.sql.SQLException;
import java.util.List;

public interface PieceDao {
    int countSavedPieces(String gameId) throws SQLException;

    void addInitialPieces(List<ChessPiece> pieces) throws SQLException;

    void addPiece(ChessPiece piece) throws SQLException;

    String findPieceNameByPosition(String gameId, Position position) throws SQLException;

    void updatePiece(String gameId, Position position, Piece piece) throws SQLException;

    void deleteBoardStatus(String gameId) throws SQLException;
}
