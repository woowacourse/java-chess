package chess.db.dao;

import java.sql.ResultSet;

public interface PieceDAO {
    void deleteTable();

    void insertPiece(String piece);

    ResultSet selectBoard();

    void updatePiece(String SourcePosition, String TargetPosition);

    void deleteCaughtPiece(String position);
}
