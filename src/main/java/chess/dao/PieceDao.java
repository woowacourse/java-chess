package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.sql.SQLException;

public interface PieceDao {
    int countSavedInfo(String user_id) throws SQLException;

    void addPiece(String user_id, Position position, Piece piece) throws SQLException;

    String findPieceNameByPosition(String user_id, Position position) throws SQLException;

    void updatePiece(String user_id, Position position, Piece piece) throws SQLException;

    void deletePiece(String user_id, Position position) throws SQLException;
}
